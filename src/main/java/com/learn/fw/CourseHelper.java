package com.learn.fw;

import com.learn.models.Course;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CourseHelper extends BaseHelper {

    public CourseHelper(WebDriver driver) {
        super(driver);
    }

    public WebElement getCreateCourseError() {
        WebElement errorCC = ApplicationManager.driver.findElement(By.className("create-course-error"));
        return errorCC;
    }

    public void clickOnBurgerMenuMyCourses() {
        click(By.cssSelector(".burgerMenu"));
        click(By.cssSelector(".burgerMenuContent a[href='/my_courses']"));
    }

    public boolean isCourseCreatedSuccessPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'Course created successfully')]"));
    }

    public void uploadCoverPhoto(String filePath) {
        typeFile(By.id("coverPhoto"), filePath);
    }

    public void fillCourseForm(Course course) {
        typeFile(By.id("coverPhoto"), course.getPhotoPath());
        type(By.cssSelector("#description"), course.getDescription());
        type(By.cssSelector("#title"), course.getTitle());
        type(By.cssSelector("#price"), course.getPrice());
    }

    public void clickOnSubmitCreateCourseBtn() {
        click(By.cssSelector(".create-course-submit"));
    }

    public void clickOnCreateCourseBtn() {
        click(By.cssSelector(".create-course-button"));
    }

    public void clickOnMyCreatedCoursesLink() {
        click(By.xpath("//button[text()='My Created Courses']"));
    }

    public void clickDeleteCourseBtn() {
        click(By.cssSelector(".delete-button"));
    }

    public boolean isDeleteCoursePopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'Course deleted successfully.')]"));
    }

    public boolean isUpdateCourseSuccessPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'Course updated successfully')]"));
    }

    public void clickOnSubmitEditCourseBtn() {
        click(By.cssSelector(".edit-course-submit"));
    }

    public void clickOnEditCourseBtn() {
        click(By.cssSelector(".edit-button"));
    }

    public boolean isCourseErrorPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'Please correct the errors before submitting.')]"));
    }

    public boolean isCourseWarningPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'No changes were made.')]"));
    }

    public void removeCourseTitle() {
        clear(By.cssSelector("#title"));
    }

    public void removeCourseDescription() {
        clear(By.cssSelector("#title"));
    }

    public void changeCoursePrice(String price) {
        clear(By.cssSelector("#price"));
        type(By.cssSelector("#price"), price);
    }

    public void changeCourseDesc(String desc) {
        clear(By.cssSelector("#description"));
        type(By.cssSelector("#description"), desc);
    }

    public void changeCourseTitle(String title) {
        clear(By.cssSelector("#title"));
        type(By.cssSelector("#title"), title);
    }
}

