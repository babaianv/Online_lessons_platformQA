package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import com.learn.data.UserData;
import com.learn.models.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LogoutPositiveTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition(){

        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname(UserData.NICKNAME)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().pause(3000);
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickOnLoginBtn();
        app.getUserHelper().fillLoginForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword( UserData.PASSWORD));
        app.getUserHelper().clickSubmitLoginBtn();
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(3000);
    }

    @Test(description = "Logout Positive Test")
    public void logoutPositiveTest(){
        app.getUserHelper().clickBurgerMenuLogoutBtn();

        softAssert.assertTrue(app.getUserHelper().isLogoutLinkPresent());
        softAssert.assertTrue(app.getUserHelper().isLoginBtnPresent());
    }


}

