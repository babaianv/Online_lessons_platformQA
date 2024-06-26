package com.learn.fw;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseHelper {

    WebDriver driver;


    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }


    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size()>0;
    }

    public void type(By locator, String text) {
        if (text != null){
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public void clear(By locator){
      driver.findElement(locator).click();
      driver.findElement(locator).clear();
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isAlertAppears() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

        if (alert == null){
            return false;
        }else {
            alert.accept();
            return true;
        }

    }

    public void typeFile(By locator, String filePath) {
        driver.findElement(locator).sendKeys(filePath);
    }

    public void pause(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public String takeScreenshot(){
        File tmp =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screenshot =   new File("screenshots/screen_"+ System.currentTimeMillis() + ".png");

        try {
            Files.copy(tmp,screenshot);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return screenshot.getAbsolutePath();
    }
}

