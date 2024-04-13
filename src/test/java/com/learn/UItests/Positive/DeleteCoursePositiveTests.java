package com.learn.UItests.Positive;

import com.learn.UItests.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteCoursePositiveTests extends TestBase {

    String filePath = "C:\\Online_lesson_platform\\src\\coverPhoto\\coverPhoto.jpg";


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

    @Test(description = "UI: Delete Course Positive Test")
    public void deleteCoursePositiveTest(){
        driver.findElement(By.xpath("//button[text()='My Created Courses']")).click();
        driver.findElement(By.cssSelector(".delete-button")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Assert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'Course deleted successfully.')]")));
    }
}

