package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
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
                .setNickname("Testles")
                .setEmail("testles@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getCourseHelper().clickOnBurgerMenuMyCourses();
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Devops from begin to advanced")
                .setPrice("700")
                .setDescription("Our Java courses offer students a comprehensive immersion into this powerful programming language. We provide a structured curriculum, starting from basic concepts and progressing to more advanced topics. Students learn fundamental object-oriented programming concepts such as classes, objects, inheritance, and polymorphism, and apply them in practical exercises.Within our courses, we offer a wide range of exercises and projects to help students solidify their knowledge and skills. We also focus on practical aspects of development, including working with integrated development environments, debugging code, and version control.Additionally, our courses cover popular Java frameworks and libraries like Spring and Hibernate, enabling students to master modern tools for web development and database work.Our teaching methodology is based on hands-on experience and interactive sessions, where students actively engage in discussions, solve problems, and create their own projects under the guidance of experienced instructors."));
        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg");
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

//    @Test(description = "UI: Create Lesson With Empty Title Neg Test")
//    public void createLessonWithEmptyTitleNegTest(){
//        app.getLessonHelper().clickOnAddLessonBtn();
//        app.getLessonHelper().fillLessonForm(new Lesson()
//                .setNumber("1")
//                .setContent("Introduction to Java: Covering essential concepts such as variables, data types, loops, conditionals, methods, and problem-solving strategies. Interactive sessions with hands-on exercises for practical learning."));
//        app.getLessonHelper().uploadLessonPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\lessonPhoto.png");
//        app.getLessonHelper().clickSubmitAddLessonBtn();
//
//        Assert.assertTrue(app.getLessonHelper().isErrorEmptyFieldMessagePopUpPresent());
//    }

//    @Test(description = "UI: Create Lesson With Empty Content Neg Test")
//    public void createLessonWithEmptyContentNegTest(){
//        app.getLessonHelper().clickOnAddLessonBtn();
//        app.getLessonHelper().fillLessonForm(new Lesson()
//                .setNumber("1")
//                .setTitle("Basic concepts and definitions"));
//        app.getLessonHelper().uploadLessonPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\lessonPhoto.png");
//        app.getLessonHelper().clickSubmitAddLessonBtn();
//
//        Assert.assertTrue(app.getLessonHelper().isErrorEmptyFieldMessagePopUpPresent());
//    }

    @Test(description = "UI: Create Lesson Without Photo Neg Test")
    public void createLessonWithoutPhotoNegTest(){
        app.getLessonHelper().clickOnAddLessonBtn();
        app.getLessonHelper().fillLessonForm(new Lesson()
                .setNumber("1")
                .setTitle("Basic concepts and definitions")
                .setContent("Introduction to Java: Covering essential concepts such as variables, data types, loops, conditionals, methods, and problem-solving strategies. Interactive sessions with hands-on exercises for practical learning."));
        app.getLessonHelper().clickSubmitAddLessonBtn();

        Assert.assertTrue(app.getLessonHelper().isErrorPhotoMessagePopUpPresent());
    }

    @Test(description = "UI: Create Lesson with wrong Photo format Neg Test")
    public void createLessonWithWrongPhotoFormatNegTest(){
        app.getLessonHelper().clickOnAddLessonBtn();
        app.getLessonHelper().fillLessonForm(new Lesson()
                .setNumber("1")
                .setTitle("Basic concepts and definitions")
                .setContent("Introduction to Java: Covering essential concepts such as variables, data types, loops, conditionals, methods, and problem-solving strategies. Interactive sessions with hands-on exercises for practical learning."));
        app.getLessonHelper().uploadLessonPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\TestPlan.docx");
        app.getLessonHelper().clickSubmitAddLessonBtn();

        Assert.assertTrue(app.getLessonHelper().isSuccessAddLessonPopUpPresent());
    }


}

