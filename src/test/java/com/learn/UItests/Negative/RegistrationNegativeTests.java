package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
import com.learn.data.UserData;
import com.learn.fw.DataProviders;
import com.learn.models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition(){
        app.getUserHelper().pause(3000);
    }

    @Test(dataProvider = "getRegisterInvalidData", dataProviderClass = DataProviders.class)
    public void regWithInvalidDataTest(User user){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(user);
        app.getUserHelper().clickSubmitSignUpBtn();

        String actualError = app.getUserHelper().getRegisterError();
        String expectedError = user.getError();

        if (expectedError != null) {
            softAssert.assertTrue(actualError.contains(expectedError), "Actual error does not contain the expected substring");
        } else {
            softAssert.assertFalse(actualError.isEmpty(), "Actual error should not be empty");
        }

        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(dataProvider = "getRegisterEmptyData", dataProviderClass = DataProviders.class)
    public void regWithEmptyDataTest(User user){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(user);
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( app.getUserHelper().getRegisterError(),"Invalid nickname format");
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Exist Nickname Neg Test")
    public void regWithExistNicknameTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname(UserData.NICKNAME_EX)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertTrue(app.getUserHelper().isNicknameTakenErrorPopUpPresent());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Exist Email Neg Test")
    public void regWithExistEmailTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname(UserData.NICKNAME)
                .setEmail(UserData.EMAIL_EX)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertTrue(app.getUserHelper().isEmailExistErrorPopUpPresent());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }



}

