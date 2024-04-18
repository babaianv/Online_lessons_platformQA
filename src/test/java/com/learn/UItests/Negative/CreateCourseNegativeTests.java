package com.learn.UItests.Negative;

import com.learn.data.UserData;
import com.learn.fw.DataProviders;
import com.learn.models.Course;
import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateCourseNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition(){

        app.getUserHelper().pause(3000);
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User().
                setNickname(UserData.NICKNAME)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getCourseHelper().clickOnBurgerMenuMyCourses();
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(3000);
    }



    @Test(dataProvider = "getCreateCourseEmptyData", dataProviderClass = DataProviders.class)
    public void createCourseEmptyDescriptionNegTest(Course course){
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(course);
        app.getUserHelper().pause(1500);
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();

        Assert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
    }


    @Test(dataProvider = "getCourseInvalidData", dataProviderClass = DataProviders.class)
    public void createCourseWithPdfFileNegTest(Course course){

        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(course);
        app.getUserHelper().pause(1500);
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();
        Assert.assertFalse(app.getCourseHelper().isCourseCreatedSuccessPopUpPresent());
    }



}

