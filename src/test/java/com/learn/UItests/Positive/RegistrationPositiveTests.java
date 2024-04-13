package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @AfterMethod
    public void clean(){
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();

        driver.findElement(By.cssSelector("#delete-btn")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(description = "UI: Registration Positive Test")
    public void registrationPositiveTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testregp");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testregp@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        driver.findElement(By.cssSelector(".burgerMenu")).click();

        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Account has been created and you are logged in.')]")));
        softAssert.assertTrue(isElementPresent(By.cssSelector(".burgerMenuContent a[href='/']")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg with Boundary Value 10 Nickname")
    public void regWithBoundaryValue10NicknameTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg10");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testregp10@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        driver.findElement(By.cssSelector(".burgerMenu")).click();

        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Account has been created and you are logged in.')]")));
        softAssert.assertTrue(isElementPresent(By.cssSelector(".burgerMenuContent a[href='/']")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg with Boundary Value 3 Nickname")
    public void regWithBoundaryValue3NicknameTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Tes");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testregp10@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        driver.findElement(By.cssSelector(".burgerMenu")).click();

        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Account has been created and you are logged in.')]")));
        softAssert.assertTrue(isElementPresent(By.cssSelector(".burgerMenuContent a[href='/']")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg with Boundary Value 8 Password Test")
    public void regWithBoundaryValue8PasswordTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testp8");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testp8@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Testes1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        driver.findElement(By.cssSelector(".burgerMenu")).click();

        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Account has been created and you are logged in.')]")));
        softAssert.assertTrue(isElementPresent(By.cssSelector(".burgerMenuContent a[href='/']")));
        softAssert.assertAll();
    }




}

