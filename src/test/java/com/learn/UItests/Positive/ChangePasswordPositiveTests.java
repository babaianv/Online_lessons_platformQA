package com.learn.UItests.Positive;

import com.learn.data.UserData;
import com.learn.models.ChangePassword;
import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordPositiveTests extends TestBase {


    @BeforeMethod
    public void precondition(){

        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname(UserData.NICKNAME)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().pause(5000);
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickAccountInfo();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(5000);
    }


    @Test(description = "UI: Change Password Positive Test")
    public void changePasswordPositiveTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword(UserData.PASSWORD)
                .setNewPassword(UserData.PASSWORD_NEW)
                .setConfirmPassword(UserData.PASSWORD_NEW));
        app.getChangePasswordHelper().clickSavePasswordBtn();
        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordSuccessMessagePresent());
    }

    @Test(description = "UI: Change Password Boundary Value 8 Test")
    public void changePasswordBoundaryValue8Test(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword(UserData.PASSWORD)
                .setNewPassword(UserData.PASSWORD_NEWBV)
                .setConfirmPassword(UserData.PASSWORD_NEWBV));
        app.getChangePasswordHelper().clickSavePasswordBtn();
        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordSuccessMessagePresent());
    }


}

