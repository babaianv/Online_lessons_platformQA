package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePagePositiveTests extends TestBase {


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

