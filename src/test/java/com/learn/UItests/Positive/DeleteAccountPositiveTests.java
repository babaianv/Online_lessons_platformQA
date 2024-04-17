package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteAccountPositiveTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testdel")
                .setEmail("testdel@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickBurgerMenu();
        app.getUserHelper().pause(5000);
    }

    @Test(description = "UI: Delete User Account")
    public void deleteUserAccount(){
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();

        Assert.assertTrue(app.getUserHelper().isDeleteAccountSuccessPopUpPresent());
    }

}

