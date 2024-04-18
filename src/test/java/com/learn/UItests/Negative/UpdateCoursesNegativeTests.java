package com.learn.UItests.Negative;

import com.learn.data.CourseData;
import com.learn.data.UserData;
import com.learn.models.Course;
import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateCoursesNegativeTests extends TestBase {
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition() {
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname(UserData.NICKNAME)
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getCourseHelper().clickOnBurgerMenuMyCourses();
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle(CourseData.TITLE)
                .setPrice(CourseData.PRICE)
                .setDescription(CourseData.DESCRIPTION)
                .setPhotoPath(CourseData.PHOTO));
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();
        app.getUserHelper().pause(3000);
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(3000);
    }


    @Test(description = "Edit Course with empty Title Neg Test")
    public void editCourseWithEmptyTitleTest() {
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnEditCourseBtn();
        app.getCourseHelper().removeCourseTitle();
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        Assert.assertTrue(app.getCourseHelper().isCourseWarningPopUpPresent());
    }


    @Test(description = "Edit Course Empty Description Negative Test")
    public void editCourseEmptyDescriptionNegTest() {
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnEditCourseBtn();
        app.getCourseHelper().removeCourseDescription();
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        Assert.assertTrue(app.getCourseHelper().isCourseWarningPopUpPresent());
    }

    @Test(description = "Edit Course Short Description Negative Test")
    public void editCourseShortDescriptionNegTest() {
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnEditCourseBtn();
        app.getCourseHelper().changeCourseDesc(CourseData.DESCRIPTION_NEWS);
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        softAssert.assertEquals( "The description must be at least 300 characters long.", app.getCourseHelper().getCreateCourseError().getText());
        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
        softAssert.assertAll();
    }

    @Test(description = "Edit Course Height Price Negative Test")
    public void editCourseHeightPriceNegTest() {

        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnEditCourseBtn();
        app.getCourseHelper().changeCoursePrice(CourseData.PRICE_H);
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        softAssert.assertEquals( "The price must be between 0 and 9999.", app.getCourseHelper().getCreateCourseError().getText());
        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
        softAssert.assertAll();
    }

}

