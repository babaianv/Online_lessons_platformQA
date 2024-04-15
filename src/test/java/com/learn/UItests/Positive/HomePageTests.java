package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
    public void isHomeComponentPresentTest(){
        Assert.assertTrue(app.getHomePageHelper().isHomeComponentPresent());
    }

    @Test
    public void isCourseCardRedirectToCourseDetailsPage(){
        app.getCartHelper().clickOnCourseCard();
        Assert.assertTrue(app.getHomePageHelper().isCourseCardRedirectToCourseDetailsPage());
    }

    @Test
    public void isLoginBtnPresent(){
        Assert.assertTrue(app.getHomePageHelper().isLoginBtnPresent());
    }

    @Test
    public void isSignUpBtnPresent(){
        Assert.assertTrue(app.getHomePageHelper().isSignUpBtnPresent());
    }


}

