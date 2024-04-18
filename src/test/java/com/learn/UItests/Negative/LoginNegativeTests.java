package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
import com.learn.data.UserData;
import com.learn.fw.DataProviders;
import com.learn.models.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class LoginNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition(){
        app.getUserHelper().pause(3000);
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname(UserData.NICKNAME)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickBurgerMenuLogoutBtn();
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().fillLoginForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitLoginBtn();
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(3000);
    }


    @Test(dataProvider = "getLoginEmptyData", dataProviderClass = DataProviders.class)
    public void loginNegativeEmptyDataTests(User user){
        app.getUserHelper().clickOnLoginBtn();
        app.getUserHelper().fillLoginForm(user);
        app.getUserHelper().clickSubmitLoginBtn();

        softAssert.assertTrue(app.getUserHelper().isLoginErrorEmptyPopUpPresent());
        softAssert.assertFalse(app.getUserHelper().isBurgerMenuPresent());
        softAssert.assertAll();
    }

    @Test(dataProvider = "getLoginInvalidData", dataProviderClass = DataProviders.class)
    public void loginNegativeTests(User user){
        app.getUserHelper().clickOnLoginBtn();
        app.getUserHelper().fillLoginForm(user);
        app.getUserHelper().clickSubmitLoginBtn();

        softAssert.assertTrue(app.getUserHelper().isLoginErrorIncorrectPopUpPresent());
        softAssert.assertFalse(app.getUserHelper().isBurgerMenuPresent());
        softAssert.assertAll();
    }


}

