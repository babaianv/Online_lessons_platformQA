package com.learn.fw;

import com.learn.models.Lesson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LessonHelper extends BaseHelper{

    public LessonHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddLessonBtn(){
        click(By.cssSelector(".add-button"));
    }

    public void fillLessonForm(Lesson lesson ){
        if (lesson.getLessonPhoto() == null || lesson.getLessonPhoto().isEmpty()) {
            // Если фото пусто, просто пропускаем заполнение этого поля
            type(By.cssSelector("#number"), lesson.getNumber());
            type(By.cssSelector("#title"), lesson.getTitle());
            type(By.cssSelector("#content"), lesson.getContent());
        } else {
            // Если фото не пусто, заполняем все поля в форме
            typeFile(By.id("lessonPhoto"), lesson.getLessonPhoto());
            type(By.cssSelector("#number"), lesson.getNumber());
            type(By.cssSelector("#title"), lesson.getTitle());
            type(By.cssSelector("#content"), lesson.getContent());
        }
    }

    public void clickSubmitAddLessonBtn(){
        click(By.cssSelector(".create-lesson-submit"));
    }

    public boolean isSuccessAddLessonPopUpPresent(){
        return isElementPresent(By.xpath("//div[contains(text(), 'Lesson added to list')]"));
    }

    public boolean isSuccessSubmitAllLessonsPopUpPresent(){
        return isElementPresent(By.xpath("//div[contains(text(), 'All lessons created successfully')]"));
    }

    public boolean isErrorPhotoMessagePopUpPresent(){
        return isElementPresent(By.xpath("//div[contains(text(), 'Please select a photo for the lesson.')]"));
    }

    public boolean isErrorEmptyFieldMessagePopUpPresent(){
        return isElementPresent(By.xpath("//div[contains(text(), 'Please fill in all the fields.')]"));
    }



}

