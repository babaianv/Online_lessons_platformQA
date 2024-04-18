package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import com.learn.data.UserData;
import com.learn.fw.DataProviders;
import com.learn.models.User;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @AfterMethod
    public void clean(){
        app.getUserHelper().click(By.cssSelector(".burgerMenuContent a[href='/my_account']"));
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(5000);
    }

    @Test(dataProvider = "getRegisterPositiveData", dataProviderClass = DataProviders.class)
    public void regWithBoundaryValue8PasswordTest(User user){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(user);
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().clickBurgerMenu();

        softAssert.assertTrue(app.getUserHelper().isRegisterSuccessPopUpPresent());
        softAssert.assertTrue(app.getUserHelper().isLogoutLinkPresent());
        softAssert.assertAll();
    }

}

