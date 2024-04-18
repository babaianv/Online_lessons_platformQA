package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import com.learn.data.CourseData;
import com.learn.data.LessonData;
import com.learn.data.UserData;
import com.learn.models.Course;
import com.learn.models.Lesson;
import com.learn.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateLessonsPositiveTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User().
                setNickname(UserData.NICKNAME)
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
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getUserHelper().pause(3000);
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(3000);
    }

    @Test(description = "UI: Create Lesson Positive Test")
    public void createLessonPositiveTest(){
        app.getLessonHelper().clickOnAddLessonBtn();
        app.getLessonHelper().fillLessonForm(new Lesson()
                .setLessonPhoto(LessonData.PHOTO)
                .setNumber(LessonData.NUMBER)
                .setTitle(LessonData.TITLE)
                .setContent(LessonData.CONTENT));
        app.getLessonHelper().clickSubmitAddLessonBtn();

        Assert.assertTrue(app.getLessonHelper().isSuccessAddLessonPopUpPresent());
    }

}

