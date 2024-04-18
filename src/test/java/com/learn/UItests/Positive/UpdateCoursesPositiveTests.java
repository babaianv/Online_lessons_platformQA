package com.learn.UItests.Positive;

import com.learn.data.CourseData;
import com.learn.data.UserData;
import com.learn.models.Course;
import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateCoursesPositiveTests extends TestBase {

    @BeforeMethod
    public void precondition(){
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

    @Test(description = "UI: Edit Course Title Positive Test")
    public void editCourseTitlePositiveTest(){
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnEditCourseBtn();
        app.getCourseHelper().changeCourseTitle(CourseData.TITLE_NEW);
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        Assert.assertTrue(app.getCourseHelper().isUpdateCourseSuccessPopUpPresent());
    }

    @Test(description = "UI: Edit Course Price Positive Test")
    public void editCoursePricePositiveTest(){
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnEditCourseBtn();
        app.getCourseHelper().changeCoursePrice(CourseData.PRICE_NEW);
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        Assert.assertTrue(app.getCourseHelper().isUpdateCourseSuccessPopUpPresent());
    }

    @Test(description = "UI: Edit Course Description Positive Test")
    public void editCourseDescriptionPositiveTest(){
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnEditCourseBtn();
        app.getCourseHelper().changeCourseDesc(CourseData.DESCRIPTION_NEW);
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        Assert.assertTrue(app.getCourseHelper().isUpdateCourseSuccessPopUpPresent());
    }


}

