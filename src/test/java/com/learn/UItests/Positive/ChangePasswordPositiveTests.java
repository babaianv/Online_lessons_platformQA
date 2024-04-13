package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordPositiveTests extends TestBase {


    @BeforeMethod
    public void precondition(){

        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();
        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testcpp");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testpcp@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
    }

    @AfterMethod
    public void clean(){
        driver.findElement(By.xpath("//button[contains(text(),'Account info')]")).click();

        driver.findElement(By.cssSelector("#delete-btn")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }


    @Test(description = "UI: Change Password Positive Test")
    public void changePasswordPositiveTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Test2test2!");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Test2test2!");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#success")));
    }

    @Test(description = "UI: Change Password Boundary Value 8 Test")
    public void changePasswordBoundaryValue8Test(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Test123!");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Test123!");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#success")));
    }


}

