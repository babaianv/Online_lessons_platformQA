package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CoursePurchasingPositiveTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testcart");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testcart@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
    }


    @Test(description = "UI: Purchasing Course Positive Test")
    public void purchasingCoursePositiveTest(){
        driver.findElement(By.cssSelector("a[href='/courses/1']")).click();
        driver.findElement(By.cssSelector(".addToCartBtn")).click();
        driver.findElement(By.cssSelector(".cartIcon")).click();
        driver.findElement(By.cssSelector("#paypalCheckbox")).click();
        driver.findElement(By.cssSelector(".payNowBtn")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Payment successful!')]")));
    }

    @AfterMethod
    public void clean(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.cssSelector("#delete-btn")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}

