package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testlogn")
                .setEmail("testlogn@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickBurgerMenuLogoutBtn();
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().fillLoginForm(new User()
                .setEmail("testlogn@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitLoginBtn();
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(1500);
    }

    @Test(description = "UI: Login with wrong Email NEG Test ")
    public void loginWithWrongEmailNegTest(){
        app.getUserHelper().clickOnLoginBtn();
        app.getUserHelper().fillLoginForm(new User()
                .setEmail("testwrong@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitLoginBtn();

        softAssert.assertTrue(app.getUserHelper().isLoginErrorIncorrectPopUpPresent());
        softAssert.assertFalse(app.getUserHelper().isBurgerMenuPresent());
        softAssert.assertAll();
    }


    @Test(description = "UI: Login with empty Email NEG Test")
    public void loginWithEmptyEmailNegTest(){
        app.getUserHelper().clickOnLoginBtn();
        app.getUserHelper().fillLoginForm(new User()
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitLoginBtn();

        softAssert.assertTrue(app.getUserHelper().isLoginErrorEmptyPopUpPresent());
        softAssert.assertFalse(app.getUserHelper().isBurgerMenuPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Login With Wrong Password Neg Test")
    public void loginWithWrongPasswordNegTest(){
        app.getUserHelper().clickOnLoginBtn();
        app.getUserHelper().fillLoginForm(new User()
                .setEmail("testlogn@gmail.com")
                .setPassword("Test1wrong1!"));
        app.getUserHelper().clickSubmitLoginBtn();

        softAssert.assertTrue(app.getUserHelper().isLoginErrorIncorrectPopUpPresent());
        softAssert.assertFalse(app.getUserHelper().isBurgerMenuPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Login With empty Password NEG Test")
    public void loginWithEmptyPasswordNegTest(){
        app.getUserHelper().clickOnLoginBtn();
        app.getUserHelper().fillLoginForm(new User()
                .setEmail("testwrong@gmail.com"));
        app.getUserHelper().clickSubmitLoginBtn();

        softAssert.assertTrue(app.getUserHelper().isLoginErrorEmptyPopUpPresent());
        softAssert.assertFalse(app.getUserHelper().isBurgerMenuPresent());
        softAssert.assertAll();
    }

}

