package com.learn.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageHelper extends BaseHelper{


    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isHomeComponentPresent(){
        return isElementPresent(By.cssSelector("#home"));
    }

    public boolean isCourseCardRedirectToCourseDetailsPage(){
        return isElementPresent(By.cssSelector(".addToCartBtn"));
    }

    public boolean isLoginBtnPresent(){
        return isElementPresent(By.cssSelector("a.loginBtn[href='/log']"));
    }

    public boolean isSignUpBtnPresent(){
        return isElementPresent(By.cssSelector("a.signUpBtn[href='/reg']"));
    }

}

