package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "UI: Login positive test")
    public void loginPositiveTest(){
        click(By.cssSelector("a.loginBtn[href='/log']"));

        type(By.cssSelector("#email"), "test1@gmail.com");

        type(By.cssSelector("#password"), "Test1test1!");

        click(By.cssSelector("button.submit-log-button"));
        click(By.cssSelector(".burgerMenu"));

        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'You logged in.')]")));
        softAssert.assertTrue(isElementPresent(By.cssSelector(".burgerMenuContent a[href='/']")));
        softAssert.assertAll();
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    @AfterMethod
    public void logout(){
        click(By.cssSelector(".burgerMenuContent a[href='/']"));
    }



}

