package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import com.learn.data.UserData;
import com.learn.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CleanCartPositiveTests extends TestBase {

    @BeforeMethod
    public void precondition(){

        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname(UserData.NICKNAME)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getUserHelper().pause(5000);
    }
    @AfterMethod
    public void clean(){
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(5000);
    }


    @Test(description = "UI: Remove Course From Cart Test")
    public void deleteCourseFromCartTest(){
        app.getCartHelper().clickOnCourseCard();
        app.getCartHelper().clickAddToCartBtn();
        app.getCartHelper().clickCartIcon();
        app.getCartHelper().clickRemoveOneItemFromCartBtn();

        Assert.assertTrue(app.getCartHelper().isRemoveCourseFromCartSuccessPopUpPresent());
    }

    @Test(description = "UI: Remove All Courses From Cart Test")
    public void removeAllCoursesFromCartTest(){
        app.getCartHelper().clickOnCourseCard();
        app.getCartHelper().clickAddToCartBtn();
        app.getCartHelper().clickAddToCartBtn();
        app.getCartHelper().clickCartIcon();
        app.getCartHelper().clickRemoveAllItemsFromCartBtn();

        Assert.assertTrue(app.getCartHelper().isRemoveCourseFromCartSuccessPopUpPresent());
    }

}

