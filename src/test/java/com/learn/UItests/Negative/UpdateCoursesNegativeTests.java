package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateCoursesNegativeTests extends TestBase {
    SoftAssert softAssert = new SoftAssert();
    String filePath = "C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg";

    @BeforeMethod
    public void precondition() {

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

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".create-course-button")).click();

        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys("Devops from begin to advanced");

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
    }

    @AfterMethod
    public void clean(){
        driver.navigate().back();
        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".delete-button")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
//        driver.findElement(By.xpath("//div[@class='Toastify__toast-body' and contains(., 'Course deleted successfully.')]"));
        Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Course deleted successfully.')]")));
    }


                    ////   ???????????????????


//    @Test(description = "Edit Course with empty Title Neg Test")
//    public void editCourseWithEmptyTitleTest() {
//
//        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
//        driver.findElement(By.cssSelector(".edit-button")).click();
//
//        driver.findElement(By.cssSelector("#title")).click();
//        driver.findElement(By.cssSelector("#title")).clear();
//        driver.findElement(By.cssSelector("#title")).sendKeys("");
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        driver.findElement(By.cssSelector(".edit-course-submit")).click();
//
//        WebElement errorElement = driver.findElement(By.className("create-course-error"));
//        softAssert.assertEquals( "The title must be at least 5 characters long.", errorElement.getText());
//        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
//        softAssert.assertAll();
//    }

           ////Bug fix message
//    @Test(description = "Edit Course Title contains numbers Negative Test")
//    public void editCourseContainsNumberNegTest() {
//
//        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
//        driver.findElement(By.cssSelector(".edit-button")).click();
//
//        driver.findElement(By.cssSelector("#title")).click();
//        driver.findElement(By.cssSelector("#title")).clear();
//        driver.findElement(By.cssSelector("#title")).sendKeys("Devops from begin to 12345");
//
//        driver.findElement(By.cssSelector(".edit-course-submit")).click();
//        Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
//    }

    @Test(description = "Edit Course Short Title Negative Test")
    public void editCourseShortTitleNegTest() {

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".edit-button")).click();

        driver.findElement(By.cssSelector("#title")).click();
        driver.findElement(By.cssSelector("#title")).clear();
        driver.findElement(By.cssSelector("#title")).sendKeys("Devo");

        driver.findElement(By.cssSelector(".edit-course-submit")).click();

        WebElement errorElement = driver.findElement(By.className("create-course-error"));
        softAssert.assertEquals( "The title must be at least 5 characters long.", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
        softAssert.assertAll();
    }


//    @Test(description = "Edit Course Negative Price Negative Test")
//    public void editCourseNegativePriceNegTest() {
//
//        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
//        driver.findElement(By.cssSelector(".edit-button")).click();
//
//        driver.findElement(By.cssSelector("#price")).click();
//        driver.findElement(By.cssSelector("#price")).clear();
//        driver.findElement(By.cssSelector("#price")).sendKeys("-100");
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        driver.findElement(By.cssSelector(".edit-course-submit")).click();
//        Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
//    }


//    @Test(description = "Edit Course Empty Description Negative Test")
//    public void editCourseEmptyDescriptionNegTest() {
//
//        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
//        driver.findElement(By.cssSelector(".edit-button")).click();
//
//        driver.findElement(By.cssSelector("#description")).click();
//        driver.findElement(By.cssSelector("#description")).clear();
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        driver.findElement(By.cssSelector(".edit-course-submit")).click();
//
//        WebElement errorElement = driver.findElement(By.className("create-course-error"));
//        softAssert.assertEquals( "The description must be at least 300 characters long.", errorElement.getText());
//        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
//        softAssert.assertAll();
//    }

    @Test(description = "Edit Course Short Description Negative Test")
    public void editCourseShortDescriptionNegTest() {

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".edit-button")).click();

        driver.findElement(By.cssSelector("#description")).click();
        driver.findElement(By.cssSelector("#description")).clear();
        driver.findElement(By.cssSelector("#description")).sendKeys("IT courses are an essential gateway to mastering the intricacies of modern technology.");

        driver.findElement(By.cssSelector(".edit-course-submit")).click();
        WebElement errorElement = driver.findElement(By.className("create-course-error"));
        softAssert.assertEquals( "The description must be at least 300 characters long.", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
        softAssert.assertAll();
    }

    @Test(description = "Edit Course Height Price Negative Test", priority = 4)
    public void editCourseHeightPriceNegTest() {

        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".edit-button")).click();

        driver.findElement(By.cssSelector("#price")).click();
        driver.findElement(By.cssSelector("#price")).clear();
        driver.findElement(By.cssSelector("#price")).sendKeys("10000");

        driver.findElement(By.cssSelector(".edit-course-submit")).click();

        WebElement errorElement = driver.findElement(By.className("create-course-error"));
        softAssert.assertEquals( "The price must be between 0 and 9999.", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]")));
        softAssert.assertAll();
    }

}

