package com.learn.UItests.Negative;

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

public class CreateLessonsNegativeTests extends TestBase {

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

    @Test(description = "UI: Create Lesson With Empty Title Neg Test")
    public void createLessonWithEmptyTitleNegTest(){
        app.getLessonHelper().clickOnAddLessonBtn();
        app.getLessonHelper().fillLessonForm(new Lesson()
                .setNumber(LessonData.NUMBER)
                .setContent(LessonData.CONTENT));
        app.getLessonHelper().uploadLessonPhoto(LessonData.PHOTO);
        app.getLessonHelper().clickSubmitAddLessonBtn();

        Assert.assertTrue(app.getLessonHelper().isErrorEmptyFieldMessagePopUpPresent());
    }

    @Test(description = "UI: Create Lesson With Empty Content Neg Test")
    public void createLessonWithEmptyContentNegTest(){
        app.getLessonHelper().clickOnAddLessonBtn();
        app.getLessonHelper().fillLessonForm(new Lesson()
                .setNumber(LessonData.NUMBER)
                .setTitle(LessonData.TITLE));
        app.getLessonHelper().uploadLessonPhoto(LessonData.PHOTO);
        app.getLessonHelper().clickSubmitAddLessonBtn();

        Assert.assertTrue(app.getLessonHelper().isErrorEmptyFieldMessagePopUpPresent());
    }

    @Test(description = "UI: Create Lesson Without Photo Neg Test")
    public void createLessonWithoutPhotoNegTest(){
        app.getLessonHelper().clickOnAddLessonBtn();
        app.getLessonHelper().fillLessonForm(new Lesson()
                .setNumber(LessonData.NUMBER)
                .setTitle(LessonData.TITLE)
                .setContent(LessonData.CONTENT));
        app.getLessonHelper().clickSubmitAddLessonBtn();

        Assert.assertTrue(app.getLessonHelper().isErrorPhotoMessagePopUpPresent());
    }



}

