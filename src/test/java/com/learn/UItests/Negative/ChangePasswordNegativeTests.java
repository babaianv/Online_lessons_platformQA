package com.learn.UItests.Negative;

import com.learn.data.UserData;
import com.learn.fw.DataProviders;
import com.learn.models.ChangePassword;
import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordNegativeTests extends TestBase {

    @BeforeMethod
    public void precondition(){

        app.getUserHelper().pause(3000);
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname(UserData.NICKNAME)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickOnBurgerMenuMyAccount();
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickAccountInfo();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(3000);
    }


    @Test(description = "UI: Change Password with Empty confirm password Negative Test")
    public void changePasswordWithEmptyConfirmPasswordNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("Test2test2!"));
        app.getChangePasswordHelper().clickSavePasswordBtn();
    }


    @Test(dataProvider = "getPasswordInvalidData", dataProviderClass = DataProviders.class)
    public void changePasswordWithInvalidDataTests(ChangePassword changePassword){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(changePassword);
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertFalse(app.getChangePasswordHelper().isChangePasswordSuccessPopupPresent());
    }

}

