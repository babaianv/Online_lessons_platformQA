package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @AfterMethod
    public void clean(){
        app.getUserHelper().click(By.cssSelector(".burgerMenuContent a[href='/my_account']"));
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(1500);
    }

    @Test(description = "UI: Registration Positive Test")
    public void registrationPositiveTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testregp")
                .setEmail("testregp@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickBurgerMenu();
        softAssert.assertTrue(app.getUserHelper().isRegisterSuccessPopUpPresent());
        softAssert.assertTrue(app.getUserHelper().isLogoutLinkPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg with Boundary Value 10 Nickname")
    public void regWithBoundaryValue10NicknameTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg10")
                .setEmail("testregp10@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickBurgerMenu();

        softAssert.assertTrue(app.getUserHelper().isRegisterSuccessPopUpPresent());
        softAssert.assertTrue(app.getUserHelper().isLogoutLinkPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg with Boundary Value 3 Nickname")
    public void regWithBoundaryValue3NicknameTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Tes")
                .setEmail("testregp10@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickBurgerMenu();

        softAssert.assertTrue(app.getUserHelper().isRegisterSuccessPopUpPresent());
        softAssert.assertTrue(app.getUserHelper().isLogoutLinkPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg with Boundary Value 8 Password Test")
    public void regWithBoundaryValue8PasswordTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testp8")
                .setEmail("testp8@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickBurgerMenu();

        softAssert.assertTrue(app.getUserHelper().isRegisterSuccessPopUpPresent());
        softAssert.assertTrue(app.getUserHelper().isLogoutLinkPresent());
        softAssert.assertAll();
    }

}

