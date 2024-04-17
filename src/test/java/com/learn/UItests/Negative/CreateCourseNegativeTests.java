package com.learn.UItests.Negative;

import com.learn.models.Course;
import com.learn.UItests.TestBase;
import com.learn.models.User;
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
                setNickname("Testccn")
                .setEmail("testccn@gmail.com")
                .setPassword("Test1test1!"));
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

//    @Test(description = "UI: Create Course with empty Title Negative Test")
//    public void createCourseWithEmptyTitleNegTest(){
//        app.getCourseHelper().clickOnCreateCourseBtn();
//        app.getCourseHelper().fillCourseForm(new Course()
//                .setPrice("700")
//                .setDescription("Our Java courses offer students a comprehensive immersion into this powerful programming language. We provide a structured curriculum, starting from basic concepts and progressing to more advanced topics. Students learn fundamental object-oriented programming concepts such as classes, objects, inheritance, and polymorphism, and apply them in practical exercises.Within our courses, we offer a wide range of exercises and projects to help students solidify their knowledge and skills. We also focus on practical aspects of development, including working with integrated development environments, debugging code, and version control.Additionally, our courses cover popular Java frameworks and libraries like Spring and Hibernate, enabling students to master modern tools for web development and database work.Our teaching methodology is based on hands-on experience and interactive sessions, where students actively engage in discussions, solve problems, and create their own projects under the guidance of experienced instructors."));
//        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg");
//        app.getCourseHelper().clickOnSubmitCreateCourseBtn();
//
//        softAssert.assertEquals( "The title must be at least 5 characters long.", app.getCourseHelper().getCreateCourseError().getText());
//        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
//        softAssert.assertAll();
//    }

    @Test(description = "UI: Create Course Title contains numbers Negative Test")
    public void createCourseTitleContainsNumbersNegTest(){
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Devops from begin to 12345")
                .setPrice("700")
                .setDescription("Our Java courses offer students a comprehensive immersion into this powerful programming language. We provide a structured curriculum, starting from basic concepts and progressing to more advanced topics. Students learn fundamental object-oriented programming concepts such as classes, objects, inheritance, and polymorphism, and apply them in practical exercises.Within our courses, we offer a wide range of exercises and projects to help students solidify their knowledge and skills. We also focus on practical aspects of development, including working with integrated development environments, debugging code, and version control.Additionally, our courses cover popular Java frameworks and libraries like Spring and Hibernate, enabling students to master modern tools for web development and database work.Our teaching methodology is based on hands-on experience and interactive sessions, where students actively engage in discussions, solve problems, and create their own projects under the guidance of experienced instructors."));
        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg");
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();

        Assert.assertTrue(app.getCourseHelper().isCreateCourseFailedPopUpPresent());
    }

    @Test(description = "UI: Create Course Short Title Negative Test")
    public void createCourseShortTitleNegTest(){
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Java")
                .setPrice("700")
                .setDescription("Our Java courses offer students a comprehensive immersion into this powerful programming language. We provide a structured curriculum, starting from basic concepts and progressing to more advanced topics. Students learn fundamental object-oriented programming concepts such as classes, objects, inheritance, and polymorphism, and apply them in practical exercises.Within our courses, we offer a wide range of exercises and projects to help students solidify their knowledge and skills. We also focus on practical aspects of development, including working with integrated development environments, debugging code, and version control.Additionally, our courses cover popular Java frameworks and libraries like Spring and Hibernate, enabling students to master modern tools for web development and database work.Our teaching methodology is based on hands-on experience and interactive sessions, where students actively engage in discussions, solve problems, and create their own projects under the guidance of experienced instructors."));
        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg");
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();

        softAssert.assertEquals( "The title must be at least 5 characters long.", app.getCourseHelper().getCreateCourseError().getText());
        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
        softAssert.assertAll();
    }


//    @Test(description = "UI: Create Course Empty Price Negative Test")
//    public void createCourseEmptyPriceNegTest(){
//        app.getCourseHelper().clickOnCreateCourseBtn();
//        app.getCourseHelper().fillCourseForm(new Course()
//                .setTitle("Devops from begin to advanced")
//                .setDescription("Our Java courses offer students a comprehensive immersion into this powerful programming language. We provide a structured curriculum, starting from basic concepts and progressing to more advanced topics. Students learn fundamental object-oriented programming concepts such as classes, objects, inheritance, and polymorphism, and apply them in practical exercises.Within our courses, we offer a wide range of exercises and projects to help students solidify their knowledge and skills. We also focus on practical aspects of development, including working with integrated development environments, debugging code, and version control.Additionally, our courses cover popular Java frameworks and libraries like Spring and Hibernate, enabling students to master modern tools for web development and database work.Our teaching methodology is based on hands-on experience and interactive sessions, where students actively engage in discussions, solve problems, and create their own projects under the guidance of experienced instructors."));
//        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg");
//        app.getCourseHelper().clickOnSubmitCreateCourseBtn();
//
//        softAssert.assertEquals( "The price must be between 0 and 9999.", app.getCourseHelper().getCreateCourseError().getText());
//        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
//        softAssert.assertAll();
//    }


    @Test(description = "UI: Create Course height Price Negative Test")
    public void createCourseHeightPriceNegTest(){
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Devops from begin to advanced")
                .setPrice("10000")
                .setDescription("Our Java courses offer students a comprehensive immersion into this powerful programming language. We provide a structured curriculum, starting from basic concepts and progressing to more advanced topics. Students learn fundamental object-oriented programming concepts such as classes, objects, inheritance, and polymorphism, and apply them in practical exercises.Within our courses, we offer a wide range of exercises and projects to help students solidify their knowledge and skills. We also focus on practical aspects of development, including working with integrated development environments, debugging code, and version control.Additionally, our courses cover popular Java frameworks and libraries like Spring and Hibernate, enabling students to master modern tools for web development and database work.Our teaching methodology is based on hands-on experience and interactive sessions, where students actively engage in discussions, solve problems, and create their own projects under the guidance of experienced instructors."));
        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg");
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();

        softAssert.assertEquals( "The price must be between 0 and 9999.", app.getCourseHelper().getCreateCourseError().getText());
        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Create Course empty Description Negative Test")
    public void createCourseEmptyDescriptionNegTest(){
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Devops from begin to advanced")
                .setPrice("300"));
        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg");
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();

        softAssert.assertEquals( "The description must be at least 300 characters long.", app.getCourseHelper().getCreateCourseError().getText());
        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Create Course short Description Negative Test")
    public void createCourseShortDescriptionNegTest(){
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Devops from begin to advanced")
                .setPrice("300")
                .setDescription("IT courses are an essential gateway to mastering the intricacies of modern technology."));
        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg");
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();

        softAssert.assertEquals( "The description must be at least 300 characters long.", app.getCourseHelper().getCreateCourseError().getText());
        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Create Course without cover Photo Negative Test")
    public void createCourseWithoutCoverPhotoNegTest(){
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Devops from begin to advanced")
                .setPrice("150")
                .setDescription("Our Java courses offer students a comprehensive immersion into this powerful programming language. We provide a structured curriculum, starting from basic concepts and progressing to more advanced topics. Students learn fundamental object-oriented programming concepts such as classes, objects, inheritance, and polymorphism, and apply them in practical exercises.Within our courses, we offer a wide range of exercises and projects to help students solidify their knowledge and skills. We also focus on practical aspects of development, including working with integrated development environments, debugging code, and version control.Additionally, our courses cover popular Java frameworks and libraries like Spring and Hibernate, enabling students to master modern tools for web development and database work.Our teaching methodology is based on hands-on experience and interactive sessions, where students actively engage in discussions, solve problems, and create their own projects under the guidance of experienced instructors."));
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();

        softAssert.assertEquals( "Cover photo is required.", app.getCourseHelper().getCreateCourseError().getText());
        softAssert.assertTrue(app.getCourseHelper().isCourseErrorPopUpPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Create Course with PDF File Negative Test")
    public void createCourseWithPdfFileNegTest(){
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Devops from begin to advanced")
                .setPrice("100")
                .setDescription("Our Java courses offer students a comprehensive immersion into this powerful programming language. We provide a structured curriculum, starting from basic concepts and progressing to more advanced topics. Students learn fundamental object-oriented programming concepts such as classes, objects, inheritance, and polymorphism, and apply them in practical exercises.Within our courses, we offer a wide range of exercises and projects to help students solidify their knowledge and skills. We also focus on practical aspects of development, including working with integrated development environments, debugging code, and version control.Additionally, our courses cover popular Java frameworks and libraries like Spring and Hibernate, enabling students to master modern tools for web development and database work.Our teaching methodology is based on hands-on experience and interactive sessions, where students actively engage in discussions, solve problems, and create their own projects under the guidance of experienced instructors."));
        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\TestPlan.pdf");
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();

        Assert.assertTrue(app.getCourseHelper().isUploadPhotoErrorMessagePresent());
    }

}

