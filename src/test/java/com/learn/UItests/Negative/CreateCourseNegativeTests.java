package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateCourseNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    String filePath = "C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg";
    String wrongFile = "C:\\Online_lesson_platform\\src\\coverPhoto\\TestPlan.pdf";


    @BeforeMethod
    public void precondition(){

        driver.findElement(By.cssSelector("a.loginBtn[href='/log']")).click();
        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test1@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-log-button")).click();
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_courses']")).click();
    }


    @Test(description = "UI: Create Course with empty Title Negative Test")
    public void createCourseWithEmptyTitleNegTest(){

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".create-course-button")).click();

        driver.findElement(By.cssSelector("#price")).click();
        driver.findElement(By.cssSelector("#price")).clear();
        driver.findElement(By.cssSelector("#price")).sendKeys("700");

        driver.findElement(By.cssSelector("#description")).click();
        driver.findElement(By.cssSelector("#description")).clear();
        driver.findElement(By.cssSelector("#description"))
                .sendKeys("IT courses are an essential gateway to mastering the intricacies of modern technology. These courses offer a diverse range of learning opportunities, covering everything from programming languages and software development to cybersecurity and data science. With the rapid advancement of technology, staying updated with the latest IT skills is crucial for professionals in the field. IT courses provide a structured learning environment, often combining theoretical knowledge with hands-on practical exercises to enhance understanding and proficiency. Additionally, many IT courses offer certifications upon completion, which can significantly boost one's career prospects. Whether you're a beginner looking to enter the IT industry or a seasoned professional aiming to advance your skills, there are courses tailored to suit every level of expertise. Moreover, the flexibility of online IT courses allows learners to study at their own pace, making education accessible to a wider audience regardless of geographical location or schedule constraints. In today's digital age, investing in IT courses is not just an option but a necessity for individuals and organizations striving to thrive in the ever-evolving tech landscape.");

        driver.findElement(By.cssSelector("#coverPhoto"));
        WebElement fileInput = driver.findElement(By.id("coverPhoto"));
        fileInput.sendKeys(filePath);

        driver.findElement(By.cssSelector(".create-course-submit")).click();

        WebElement errorElement = driver.findElement(By.className("create-course-error"));
        softAssert.assertEquals( "The title must be at least 5 characters long.", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Create Course Title contains numbers Negative Test")
    public void createCourseTitleContainsNumbersNegTest(){

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".create-course-button")).click();

        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys("Devops from begin to 12345");

        driver.findElement(By.cssSelector("#price")).click();
        driver.findElement(By.cssSelector("#price")).clear();
        driver.findElement(By.cssSelector("#price")).sendKeys("700");

        driver.findElement(By.cssSelector("#description")).click();
        driver.findElement(By.cssSelector("#description")).clear();
        driver.findElement(By.cssSelector("#description"))
                .sendKeys("IT courses are an essential gateway to mastering the intricacies of modern technology. These courses offer a diverse range of learning opportunities, covering everything from programming languages and software development to cybersecurity and data science. With the rapid advancement of technology, staying updated with the latest IT skills is crucial for professionals in the field. IT courses provide a structured learning environment, often combining theoretical knowledge with hands-on practical exercises to enhance understanding and proficiency. Additionally, many IT courses offer certifications upon completion, which can significantly boost one's career prospects. Whether you're a beginner looking to enter the IT industry or a seasoned professional aiming to advance your skills, there are courses tailored to suit every level of expertise. Moreover, the flexibility of online IT courses allows learners to study at their own pace, making education accessible to a wider audience regardless of geographical location or schedule constraints. In today's digital age, investing in IT courses is not just an option but a necessity for individuals and organizations striving to thrive in the ever-evolving tech landscape.");

        driver.findElement(By.cssSelector("#coverPhoto"));
        WebElement fileInput = driver.findElement(By.id("coverPhoto"));
        fileInput.sendKeys(filePath);


        driver.findElement(By.cssSelector(".create-course-submit")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Failed to create the course.')]")));
    }

    @Test(description = "UI: Create Course Short Title Negative Test")
    public void createCourseShortTitleNegTest(){

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".create-course-button")).click();

        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys("Java");

        driver.findElement(By.cssSelector("#price")).click();
        driver.findElement(By.cssSelector("#price")).clear();
        driver.findElement(By.cssSelector("#price")).sendKeys("700");

        driver.findElement(By.cssSelector("#description")).click();
        driver.findElement(By.cssSelector("#description")).clear();
        driver.findElement(By.cssSelector("#description"))
                .sendKeys("IT courses are an essential gateway to mastering the intricacies of modern technology. These courses offer a diverse range of learning opportunities, covering everything from programming languages and software development to cybersecurity and data science. With the rapid advancement of technology, staying updated with the latest IT skills is crucial for professionals in the field. IT courses provide a structured learning environment, often combining theoretical knowledge with hands-on practical exercises to enhance understanding and proficiency. Additionally, many IT courses offer certifications upon completion, which can significantly boost one's career prospects. Whether you're a beginner looking to enter the IT industry or a seasoned professional aiming to advance your skills, there are courses tailored to suit every level of expertise. Moreover, the flexibility of online IT courses allows learners to study at their own pace, making education accessible to a wider audience regardless of geographical location or schedule constraints. In today's digital age, investing in IT courses is not just an option but a necessity for individuals and organizations striving to thrive in the ever-evolving tech landscape.");

        driver.findElement(By.cssSelector("#coverPhoto"));
        WebElement fileInput = driver.findElement(By.id("coverPhoto"));
        fileInput.sendKeys(filePath);

        driver.findElement(By.cssSelector(".create-course-submit")).click();

        WebElement errorElement = driver.findElement(By.className("create-course-error"));
        softAssert.assertEquals( "The title must be at least 5 characters long.", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
        softAssert.assertAll();
    }

    ///Bug T25-133
    @Test(description = "UI: Create Course Empty Price Negative Test")
    public void createCourseEmptyPriceNegTest(){

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".create-course-button")).click();

        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys("Java Advanced");

        driver.findElement(By.cssSelector("#price")).click();
        driver.findElement(By.cssSelector("#price")).clear();

        driver.findElement(By.cssSelector("#description")).click();
        driver.findElement(By.cssSelector("#description")).clear();
        driver.findElement(By.cssSelector("#description"))
                .sendKeys("IT courses are an essential gateway to mastering the intricacies of modern technology. These courses offer a diverse range of learning opportunities, covering everything from programming languages and software development to cybersecurity and data science. With the rapid advancement of technology, staying updated with the latest IT skills is crucial for professionals in the field. IT courses provide a structured learning environment, often combining theoretical knowledge with hands-on practical exercises to enhance understanding and proficiency. Additionally, many IT courses offer certifications upon completion, which can significantly boost one's career prospects. Whether you're a beginner looking to enter the IT industry or a seasoned professional aiming to advance your skills, there are courses tailored to suit every level of expertise. Moreover, the flexibility of online IT courses allows learners to study at their own pace, making education accessible to a wider audience regardless of geographical location or schedule constraints. In today's digital age, investing in IT courses is not just an option but a necessity for individuals and organizations striving to thrive in the ever-evolving tech landscape.");

        driver.findElement(By.cssSelector("#coverPhoto"));
        WebElement fileInput = driver.findElement(By.id("coverPhoto"));
        fileInput.sendKeys(filePath);


        driver.findElement(By.cssSelector(".create-course-submit")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
    }

    @Test(description = "UI: Create Course negative Price Negative Test")
    public void createCourseNegativePriceNegTest(){

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".create-course-button")).click();

        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys("Devops from begin to advanced");

        driver.findElement(By.cssSelector("#price")).click();
        driver.findElement(By.cssSelector("#price")).clear();
        driver.findElement(By.cssSelector("#price")).sendKeys("-100");

        driver.findElement(By.cssSelector("#description")).click();
        driver.findElement(By.cssSelector("#description")).clear();
        driver.findElement(By.cssSelector("#description"))
                .sendKeys("IT courses are an essential gateway to mastering the intricacies of modern technology. These courses offer a diverse range of learning opportunities, covering everything from programming languages and software development to cybersecurity and data science. With the rapid advancement of technology, staying updated with the latest IT skills is crucial for professionals in the field. IT courses provide a structured learning environment, often combining theoretical knowledge with hands-on practical exercises to enhance understanding and proficiency. Additionally, many IT courses offer certifications upon completion, which can significantly boost one's career prospects. Whether you're a beginner looking to enter the IT industry or a seasoned professional aiming to advance your skills, there are courses tailored to suit every level of expertise. Moreover, the flexibility of online IT courses allows learners to study at their own pace, making education accessible to a wider audience regardless of geographical location or schedule constraints. In today's digital age, investing in IT courses is not just an option but a necessity for individuals and organizations striving to thrive in the ever-evolving tech landscape.");

        driver.findElement(By.cssSelector("#coverPhoto"));
        WebElement fileInput = driver.findElement(By.id("coverPhoto"));
        fileInput.sendKeys(filePath);

        driver.findElement(By.cssSelector(".create-course-submit")).click();

        WebElement errorElement = driver.findElement(By.className("create-course-error"));
        softAssert.assertEquals( "The price must be between 0 and 9999.", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Create Course height Price Negative Test")
    public void createCourseHeightPriceNegTest(){

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".create-course-button")).click();

        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys("Devops from begin to advanced");

        driver.findElement(By.cssSelector("#price")).click();
        driver.findElement(By.cssSelector("#price")).clear();
        driver.findElement(By.cssSelector("#price")).sendKeys("10000");

        driver.findElement(By.cssSelector("#description")).click();
        driver.findElement(By.cssSelector("#description")).clear();
        driver.findElement(By.cssSelector("#description"))
                .sendKeys("IT courses are an essential gateway to mastering the intricacies of modern technology. These courses offer a diverse range of learning opportunities, covering everything from programming languages and software development to cybersecurity and data science. With the rapid advancement of technology, staying updated with the latest IT skills is crucial for professionals in the field. IT courses provide a structured learning environment, often combining theoretical knowledge with hands-on practical exercises to enhance understanding and proficiency. Additionally, many IT courses offer certifications upon completion, which can significantly boost one's career prospects. Whether you're a beginner looking to enter the IT industry or a seasoned professional aiming to advance your skills, there are courses tailored to suit every level of expertise. Moreover, the flexibility of online IT courses allows learners to study at their own pace, making education accessible to a wider audience regardless of geographical location or schedule constraints. In today's digital age, investing in IT courses is not just an option but a necessity for individuals and organizations striving to thrive in the ever-evolving tech landscape.");

        driver.findElement(By.cssSelector("#coverPhoto"));
        WebElement fileInput = driver.findElement(By.id("coverPhoto"));
        fileInput.sendKeys(filePath);

        driver.findElement(By.cssSelector(".create-course-submit")).click();

        WebElement errorElement = driver.findElement(By.className("create-course-error"));
        softAssert.assertEquals( "The price must be between 0 and 9999.", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Create Course empty Description Negative Test")
    public void createCourseEmptyDescriptionNegTest(){

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".create-course-button")).click();

        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys("Devops from begin to advanced");

        driver.findElement(By.cssSelector("#price")).click();
        driver.findElement(By.cssSelector("#price")).clear();
        driver.findElement(By.cssSelector("#price")).sendKeys("300");

        driver.findElement(By.cssSelector("#description")).click();
        driver.findElement(By.cssSelector("#description")).clear();

        driver.findElement(By.cssSelector("#coverPhoto"));
        WebElement fileInput = driver.findElement(By.id("coverPhoto"));
        fileInput.sendKeys(filePath);

        driver.findElement(By.cssSelector(".create-course-submit")).click();

        WebElement errorElement = driver.findElement(By.className("create-course-error"));
        softAssert.assertEquals( "The description must be at least 300 characters long.", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Create Course short Description Negative Test")
    public void createCourseShortDescriptionNegTest(){

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".create-course-button")).click();

        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys("Devops from begin to advanced");

        driver.findElement(By.cssSelector("#price")).click();
        driver.findElement(By.cssSelector("#price")).clear();
        driver.findElement(By.cssSelector("#price")).sendKeys("100");

        driver.findElement(By.cssSelector("#description")).click();
        driver.findElement(By.cssSelector("#description")).clear();
        driver.findElement(By.cssSelector("#description"))
                .sendKeys("IT courses are an essential gateway to mastering the intricacies of modern technology.");

        driver.findElement(By.cssSelector("#coverPhoto"));
        WebElement fileInput = driver.findElement(By.id("coverPhoto"));
        fileInput.sendKeys(filePath);

        driver.findElement(By.cssSelector(".create-course-submit")).click();

        WebElement errorElement = driver.findElement(By.className("create-course-error"));
        softAssert.assertEquals( "The description must be at least 300 characters long.", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Create Course without cover Photo Negative Test")
    public void createCourseWithoutCoverPhotoNegTest(){

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".create-course-button")).click();

        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys("Devops from begin to advanced");

        driver.findElement(By.cssSelector("#price")).click();
        driver.findElement(By.cssSelector("#price")).clear();
        driver.findElement(By.cssSelector("#price")).sendKeys("300");

        driver.findElement(By.cssSelector("#description")).click();
        driver.findElement(By.cssSelector("#description")).clear();
        driver.findElement(By.cssSelector("#description"))
                .sendKeys("IT courses are an essential gateway to mastering the intricacies of modern technology. These courses offer a diverse range of learning opportunities, covering everything from programming languages and software development to cybersecurity and data science. With the rapid advancement of technology, staying updated with the latest IT skills is crucial for professionals in the field. IT courses provide a structured learning environment, often combining theoretical knowledge with hands-on practical exercises to enhance understanding and proficiency. Additionally, many IT courses offer certifications upon completion, which can significantly boost one's career prospects. Whether you're a beginner looking to enter the IT industry or a seasoned professional aiming to advance your skills, there are courses tailored to suit every level of expertise. Moreover, the flexibility of online IT courses allows learners to study at their own pace, making education accessible to a wider audience regardless of geographical location or schedule constraints. In today's digital age, investing in IT courses is not just an option but a necessity for individuals and organizations striving to thrive in the ever-evolving tech landscape.");


        driver.findElement(By.cssSelector(".create-course-submit")).click();
        WebElement errorElement = driver.findElement(By.className("create-course-error"));
        softAssert.assertEquals( "Cover photo is required.", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
        softAssert.assertAll();
    }

    ////FAILED T25-126
//    @Test(description = "UI: Create Course with PDF File Negative Test")
//    public void createCourseWithPdfFileNegTest(){
//
//        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
//        driver.findElement(By.cssSelector(".create-course-button")).click();
//
//        driver.findElement(By.cssSelector("#title")).click();
//        driver.findElement(By.cssSelector("#title")).clear();
//        driver.findElement(By.cssSelector("#title")).sendKeys("Devops from begin to advanced");
//
//        driver.findElement(By.cssSelector("#price")).click();
//        driver.findElement(By.cssSelector("#price")).clear();
//        driver.findElement(By.cssSelector("#price")).sendKeys("100");
//
//        driver.findElement(By.cssSelector("#description")).click();
//        driver.findElement(By.cssSelector("#description")).clear();
//        driver.findElement(By.cssSelector("#description"))
//                .sendKeys("IT courses are an essential gateway to mastering the intricacies of modern technology. These courses offer a diverse range of learning opportunities, covering everything from programming languages and software development to cybersecurity and data science. With the rapid advancement of technology, staying updated with the latest IT skills is crucial for professionals in the field. IT courses provide a structured learning environment, often combining theoretical knowledge with hands-on practical exercises to enhance understanding and proficiency. Additionally, many IT courses offer certifications upon completion, which can significantly boost one's career prospects. Whether you're a beginner looking to enter the IT industry or a seasoned professional aiming to advance your skills, there are courses tailored to suit every level of expertise. Moreover, the flexibility of online IT courses allows learners to study at their own pace, making education accessible to a wider audience regardless of geographical location or schedule constraints. In today's digital age, investing in IT courses is not just an option but a necessity for individuals and organizations striving to thrive in the ever-evolving tech landscape.");
//
//
//        driver.findElement(By.cssSelector("#coverPhoto"));
//        WebElement fileInput = driver.findElement(By.id("coverPhoto"));
//        fileInput.sendKeys(wrongFile);
//
//
//        driver.findElement(By.cssSelector(".create-course-submit")).click();
//        Assert.assertTrue(isElementPresent(By.cssSelector(".Toastify__toast-container.Toastify__toast-container--bottom-left .Toastify__toast.Toastify__toast-theme--light.Toastify__toast--error")));
//    }

}

