package com.processingtime.scraper.service;

import com.processingtime.scraper.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScrapService {

    private final MailService mailService;
    private final ResultService resultService;
    private final SeleniumService seleniumService;

    public ScrapService(MailService mailService, ResultService resultService, SeleniumService seleniumService) {
        this.mailService = mailService;
        this.resultService = resultService;
        this.seleniumService = seleniumService;
    }

    @Scheduled(fixedRate = 20000)
    public void scrapWithSelenium() {
        ResultDto resultDto = seleniumService.scrap();
        checkResult(resultDto);
    }

    private void checkResult(ResultDto resultDto) {
        String lastUpdated = resultDto.getLastUpdated();
        String processingTime = resultDto.getProcessingTime();
         if (checkLastUpdated(lastUpdated) && checkProcessingTime(processingTime)) {
             if (getLastUpdatedDate() == null && getProcessingTime() == null){
                 resultService.addLatestResult(resultDto);
                 log.warn("Updating database with latest data");
             } else {
                 mailService.sendMail(resultDto);
                 resultService.addLatestResult(resultDto);
                 log.info("Result is complete - Database updated - Email sent");
             }
        } else {
            log.error("Result is not complete - No email will be sent");
        }
    }

    private boolean checkProcessingTime(String processingTime) {
        return !processingTime.equals("[PT]") && !processingTime.isEmpty();
    }

    private Boolean checkLastUpdated(String lastUpdated){
        return !lastUpdated.equals(getLastUpdatedDate()) &&
                !lastUpdated.equals("N/A") &&
                !lastUpdated.equals("Error loading date. Try again.") &&
                !lastUpdated.isEmpty();
    }

    private String getLastUpdatedDate(){
        String lastUpdatedDateFromDB = resultService.getLatestResult() == null ? null : resultService.getLatestResult().getLastUpdated();
        log.info("Last Updated Date From DB:  "+ lastUpdatedDateFromDB);
        return lastUpdatedDateFromDB;
    }

    private String getProcessingTime(){
        String processingTimeFromDB = resultService.getLatestResult() == null ? null : resultService.getLatestResult().getProcessingTime();
        log.info("Processing time From DB:  "+ processingTimeFromDB);
        return processingTimeFromDB;
    }

}




