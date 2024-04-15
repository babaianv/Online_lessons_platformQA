package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LogoutTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition(){
//        if (!isElementPresent(By.cssSelector("a.signUpBtn[href='/reg']"))){
//            click(By.cssSelector(".burgerMenu"));
//            click(By.cssSelector(".burgerMenuContent a[href='/']"));
//        }

        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testlogout")
                .setEmail("testlogout@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickOnLoginBtn();
        app.getUserHelper().fillLoginForm(new User()
                .setEmail("testlogout@gmail.com")
                .setPassword( "Test1test1!"));
        app.getUserHelper().clickSubmitLoginBtn();
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(1500);
    }

    @Test(description = "Logout Positive Test")
    public void logoutPositiveTest(){
        app.getUserHelper().clickBurgerMenuLogoutBtn();

        softAssert.assertTrue(app.getUserHelper().isLogoutLinkPresent());
        softAssert.assertTrue(app.getUserHelper().isLoginBtnPresent());
    }


}

