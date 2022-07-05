package com.processingtime.scraper.service;

import com.processingtime.scraper.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Value( "${mail-service.from}" )
    private String from;

    @Value( "${mail-service.to}" )
    private String to;

    @Value( "${mail-service.cc}" )
    private String cc;

    @Value( "${mail-service.subject}" )
    private String subject;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(ResultDto resultDto){
        SimpleMailMessage message =  new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setText("New Processing Time: " + resultDto.getProcessingTime() + "  -  " + " Last Updated: " + resultDto.getLastUpdated());
        message.setSubject(subject);

        mailSender.send(message);

        log.info("Mail Sent Successfully!");
    }
}
