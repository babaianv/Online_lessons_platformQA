package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import com.learn.data.UserData;
import com.learn.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteAccountPositiveTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname(UserData.NICKNAME)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickBurgerMenu();
        app.getUserHelper().pause(3000);
    }

    @Test(description = "UI: Delete User Account")
    public void deleteUserAccount(){
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();

        Assert.assertTrue(app.getUserHelper().isDeleteAccountSuccessPopUpPresent());
    }

}

