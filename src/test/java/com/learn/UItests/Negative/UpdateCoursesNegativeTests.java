package com.learn.UItests.Negative;

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
                .setNickname("Testupn")
                .setEmail("testupc@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getCourseHelper().clickOnBurgerMenuMyCourses();
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Java Advanced")
                .setPrice("700")
                .setDescription("Our Java courses offer students a comprehensive immersion into this powerful programming language. We provide a structured curriculum, starting from basic concepts and progressing to more advanced topics. Students learn fundamental object-oriented programming concepts such as classes, objects, inheritance, and polymorphism, and apply them in practical exercises.Within our courses, we offer a wide range of exercises and projects to help students solidify their knowledge and skills. We also focus on practical aspects of development, including working with integrated development environments, debugging code, and version control.Additionally, our courses cover popular Java frameworks and libraries like Spring and Hibernate, enabling students to master modern tools for web development and database work.Our teaching methodology is based on hands-on experience and interactive sessions, where students actively engage in discussions, solve problems, and create their own projects under the guidance of experienced instructors."));
        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg");
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(1500);
    }


    @Test(description = "Edit Course with empty Title Neg Test")
    public void editCourseWithEmptyTitleTest() {
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnEditCourseBtn();
        app.getCourseHelper().removeCourseTitle();
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        Assert.assertTrue(app.getCourseHelper().isCourseWarningPopUpPresent());
    }


    @Test(description = "Edit Course Short Title Negative Test")
    public void editCourseShortTitleNegTest() {
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnEditCourseBtn();
                app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Devo"));
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        softAssert.assertEquals( app.getCourseHelper().getCreateCourseError().getText(),"The title must be at least 5 characters long.");
        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
        softAssert.assertAll();
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
        app.getCourseHelper().fillCourseForm(new Course().setDescription("IT courses are an essential gateway to mastering the intricacies of modern technology."));
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        softAssert.assertEquals( "The description must be at least 300 characters long.", app.getCourseHelper().getCreateCourseError().getText());
        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
        softAssert.assertAll();
    }

    @Test(description = "Edit Course Height Price Negative Test", priority = 1)
    public void editCourseHeightPriceNegTest() {
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnEditCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course().setPrice("10000"));
        app.getCourseHelper().clickOnSubmitEditCourseBtn();

        softAssert.assertEquals( "The price must be between 0 and 9999.", app.getCourseHelper().getCreateCourseError().getText());
        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
        softAssert.assertAll();
    }

}

