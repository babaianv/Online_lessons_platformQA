package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "UI: Login with wrong Email NEG Test ")
    public void loginWithWrongEmailNegTest(){

        driver.findElement(By.cssSelector("a.loginBtn[href='/log']")).click();
        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testwrong@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-log-button")).click();

        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Email or password is incorrect')]")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }


    @Test(description = "UI: Login with empty Email NEG Test")
    public void loginWithEmptyEmailNegTest(){

        driver.findElement(By.cssSelector("a.loginBtn[href='/log']")).click();

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-log-button")).click();
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Email and password must not be empty')]")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));

        softAssert.assertAll();
    }

    @Test(description = "UI: Login With Wrong Password Neg Test")
    public void loginWithWrongPasswordNegTest(){
        driver.findElement(By.cssSelector("a.loginBtn[href='/log']")).click();

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test1@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1wrong1!");

        driver.findElement(By.cssSelector("button.submit-log-button")).click();
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Email or password is incorrect')]")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }


    @Test(description = "UI: Login With empty Password NEG Test")
    public void loginWithEmptyPasswordNegTest(){
        driver.findElement(By.cssSelector("a.loginBtn[href='/log']")).click();

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test1@gmail.com");

        driver.findElement(By.cssSelector("button.submit-log-button")).click();
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Email and password must not be empty')]")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

}

