package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import com.learn.data.UserData;
import com.learn.models.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname(UserData.NICKNAME)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickBurgerMenuLogoutBtn();
        app.getUserHelper().pause(3000);
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(3000);
    }

    @Test(description = "UI: Login positive test")
    public void loginPositiveTest(){
        app.getUserHelper().clickOnLoginBtn();
        app.getUserHelper().fillLoginForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitLoginBtn();
        app.getUserHelper().clickBurgerMenu();

        softAssert.assertTrue(app.getUserHelper().isLoginPopUpPresent());
        softAssert.assertTrue(app.getUserHelper().isLogoutLinkPresent());
        softAssert.assertAll();
    }


}

