package com.processingtime.scraper.service;

import com.processingtime.scraper.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SeleniumService {

    @Value( "${selenium-service.webdriver.name}" )
    private  String webdriverName;

    @Value( "${selenium-service.webdriver.location}" )
    private  String webdriverLocation;

    @Value( "${selenium-service.website.url}" )
    private  String websiteUrl;

    @Value( "${selenium-service.website.fields.ddl1.name}" )
    private  String ddl1Name;

    @Value( "${selenium-service.website.fields.ddl1.value}" )
    private  String ddl1Value;

    @Value( "${selenium-service.website.fields.ddl2.name}" )
    private  String ddl2Name;

    @Value( "${selenium-service.website.fields.ddl2.value}" )
    private  String ddl2Value;

    @Value( "${selenium-service.website.fields.ddl3.name}" )
    private  String ddl3Name;

    @Value( "${selenium-service.website.fields.ddl3.value}" )
    private  String ddl3Value;

    @Value( "${selenium-service.website.fields.btn1.class}" )
    private  String btn1Class;

    @Value( "${selenium-service.website.fields.btn1.text}" )
    private  String btn1Text;

    @Value( "${selenium-service.website.fields.result1.id}" )
    private  String result1Id;

    @Value( "${selenium-service.website.fields.result1.default}" )
    private  String result1Default;

    @Value( "${selenium-service.website.fields.result2.id}" )
    private  String result2Id;

    @Value( "${selenium-service.website.fields.result2.default}" )
    private  String result2Default;

    public ResultDto scrap() {
        System.setProperty(webdriverName, webdriverLocation);
        WebDriver driver = new ChromeDriver();
        String processingTime = result1Default;
        String lastUpdated = result2Default;

        try {
            driver.get(websiteUrl);

            Select ddl1 = new Select(driver.findElement(By.name(ddl1Name)));
            ddl1.selectByValue(ddl1Value);

            Select ddl2 = new Select(driver.findElement(By.name(ddl2Name)));
            ddl2.selectByValue(ddl2Value);

            Select ddl3 = new Select(driver.findElement(By.name(ddl3Name)));
            ddl3.selectByValue(ddl3Value);

            WebElement btn1 = driver.findElement(By.cssSelector("[" + btn1Class));
            if (btn1.getText().equals(btn1Text)) {
                btn1.click();
            } else {
                log.error("Submit button was not found");
            }

            WebDriverWait waitForResult = new WebDriverWait(driver, 10);
            waitForResult.until(ExpectedConditions.visibilityOfElementLocated(By.id(result1Id)));

            WebElement processingTimeElement = driver.findElement(By.id(result1Id));
            processingTime = processingTimeElement.getText();
            log.info("Processing Time: " + processingTime);

            WebElement lastUpdatedElement = driver.findElement(By.id(result2Id));
            lastUpdated = lastUpdatedElement.getText();
            log.info("Last Updated Date: " + lastUpdated);

        } catch (Exception e) {
            log.error("Caught exception: " + e);
        } finally {
            driver.close();
        }
        return ResultDto.builder().processingTime(processingTime).lastUpdated(lastUpdated).build();
    }

}