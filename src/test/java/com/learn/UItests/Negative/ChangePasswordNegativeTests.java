package com.learn.UItests.Negative;

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
        app.getUserHelper().pause(1500);
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testcpn")
                .setEmail("testpcn@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickOnBurgerMenuMyAccount();
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickAccountInfo();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(1500);
    }

    @Test(description = "UI: Change Password with wrong old password Negative Test")
    public void changePasswordWithWrongOldPasswordNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Testtest!")
                .setNewPassword("Test2test2!")
                .setConfirmPassword("Test2test2!"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password with empty old password Negative Test")
    public void changePasswordWithEmptyOldPasswordNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setNewPassword("Test2test2!")
                .setConfirmPassword("Test2test2!"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password with empty new password Negative Test")
    public void changePasswordWithEmptyNewPasswordNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setConfirmPassword("Test2test2!"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }


    @Test(description = "UI: Change Password with wrong new password Negative Test")
    public void changePasswordWithWrongNewPasswordNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("Test123")
                .setConfirmPassword("Test2test2!"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password with wrong confirm password Negative Test")
    public void changePasswordWithWrongConfirmPasswordNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("Test2test2!")
                .setConfirmPassword("Test123"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password with Empty confirm password Negative Test")
    public void changePasswordWithEmptyConfirmPasswordNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("Test2test2!"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password with Only numbers Negative Test")
    public void changePasswordWithOnlyNumbersNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("123456789")
                .setConfirmPassword("123456789"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password with Only letters Negative Test")
    public void changePasswordWithOnlyLettersNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("Testtest")
                .setConfirmPassword("Testtest"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password with Only valid Symbol Negative Test")
    public void changePasswordWithOnlySymbolNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("!$#%!$#%!")
                .setConfirmPassword("!$#%!$#%!"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password with invalid Symbol Negative Test")
    public void changePasswordWithInvalidSymbolNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("Test1test1>")
                .setConfirmPassword("Test1test1>"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password with only Lowercase Negative Test")
    public void changePasswordWithOnlyLowercaseNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("test1test1!")
                .setConfirmPassword("test1test1!"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password with only Uppercase Negative Test")
    public void changePasswordWithOnlyUppercaseNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("TEST1TEST1!")
                .setConfirmPassword("TEST1TEST1!"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password Without Special Symbol Negative Test")
    public void changePasswordWithoutSpecialSymbolNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("Test1test1")
                .setConfirmPassword("Test1test1"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }

    @Test(description = "UI: Change Password Without Number Negative Test")
    public void changePasswordWithoutNumberNegativeTest(){
        app.getChangePasswordHelper().clickChangePasswordLink();
        app.getChangePasswordHelper().fillChangePasswordForm(new ChangePassword()
                .setOldPassword("Test1test1!")
                .setNewPassword("Testtest!")
                .setConfirmPassword("Testtest"));
        app.getChangePasswordHelper().clickSavePasswordBtn();

        Assert.assertTrue(app.getChangePasswordHelper().isChangePasswordErrorMessagePresent());
    }


}

