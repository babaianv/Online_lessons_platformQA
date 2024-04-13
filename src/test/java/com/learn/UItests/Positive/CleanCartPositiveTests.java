package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CleanCartPositiveTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        driver.findElement(By.cssSelector("a.loginBtn[href='/log']")).click();
        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test1@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");
        driver.findElement(By.cssSelector("button.submit-log-button")).click();
    }

    @Test(description = "Delete Course From Cart Test")
    public void deleteCourseFromCartTest(){
        driver.findElement(By.cssSelector("a[href='/courses/1']")).click();
        driver.findElement(By.cssSelector(".addToCartBtn")).click();
        driver.findElement(By.cssSelector(".cartIcon")).click();
        driver.findElement(By.cssSelector(".removeBtn")).click();

        Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Done!')]")));
    }

    @Test(description = "Remove All Courses From Cart Test")
    public void removeAllCoursesFromCartTest(){

        driver.findElement(By.cssSelector("a[href='/courses/1']")).click();
        driver.findElement(By.cssSelector(".addToCartBtn")).click();

        driver.findElement(By.cssSelector("a[href='/']")).click();
        driver.findElement(By.cssSelector("a[href='/courses/2']")).click();
        driver.findElement(By.cssSelector(".addToCartBtn")).click();

        driver.findElement(By.cssSelector(".cartIcon")).click();
        driver.findElement(By.className("removeAllBtn")).click();

        Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Done!')]")));
    }

}

