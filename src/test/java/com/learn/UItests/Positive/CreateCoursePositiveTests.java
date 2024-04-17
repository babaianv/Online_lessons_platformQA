package com.learn.UItests.Positive;

import com.learn.models.Course;
import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateCoursePositiveTests extends TestBase {


    @BeforeMethod
    public void precondition(){

        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Test_CC")
                .setEmail("testccp@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        app.getCourseHelper().clickOnBurgerMenuMyCourses();
        app.getUserHelper().pause(5000);
    }

    @AfterMethod
    public void clean(){
        app.getUserHelper().clickOnBurgerMenuMyAccount();
        app.getUserHelper().clickOnDeleteAccountBtn();
        app.getUserHelper().isAlertAppears();
        app.getUserHelper().pause(5000);
    }

    @Test(description = "UI: Create Course Positive Test")
    public void createCoursePositiveTest(){
        app.getCourseHelper().clickOnMyCreatedCoursesLink();
        app.getCourseHelper().clickOnCreateCourseBtn();
        app.getCourseHelper().fillCourseForm(new Course()
                .setTitle("Devops from begin to advanced")
                .setPrice("700")
                .setDescription("IT courses are an essential gateway to mastering the intricacies of modern technology. These courses offer a diverse range of learning opportunities, covering everything from programming languages and software development to cybersecurity and data science. With the rapid advancement of technology, staying updated with the latest IT skills is crucial for professionals in the field. IT courses provide a structured learning environment, often combining theoretical knowledge with hands-on practical exercises to enhance understanding and proficiency. Additionally, many IT courses offer certifications upon completion, which can significantly boost one's career prospects. Whether you're a beginner looking to enter the IT industry or a seasoned professional aiming to advance your skills, there are courses tailored to suit every level of expertise. Moreover, the flexibility of online IT courses allows learners to study at their own pace, making education accessible to a wider audience regardless of geographical location or schedule constraints. In today's digital age, investing in IT courses is not just an option but a necessity for individuals and organizations striving to thrive in the ever-evolving tech landscape."));
        app.getCourseHelper().uploadCoverPhoto("C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg");
        app.getCourseHelper().clickOnSubmitCreateCourseBtn();

        Assert.assertTrue(app.getCourseHelper().isCourseCreatedSuccessPopUpPresent());
    }


}

