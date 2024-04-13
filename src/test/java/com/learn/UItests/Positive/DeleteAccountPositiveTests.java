package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteAccountPositiveTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testdel");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testdel@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        driver.findElement(By.cssSelector(".burgerMenu")).click();
    }

    @Test(description = "UI: Delete User Account")
    public void deleteUserAccount(){

        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.cssSelector("#delete-btn")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        ////add alert
    }
}

