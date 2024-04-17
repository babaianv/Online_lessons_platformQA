package com.learn.RAtests.LessonController;

import com.learn.RAtests.TestBase;
import com.learn.dto.LessonDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateLessonsPositiveTests extends TestBase {

    private  int lessonId;
    private LessonDto lessonDto;

    @BeforeMethod
    public void precondition() {
       lessonDto = LessonDto.builder()
                .title("GitHub basic")
                .photoPath("https://kanzlei-baumfalk.de/wp-content/uploads/2022/12/programmers-cooperating-at-information-technology-2021-08-28-19-22-29-utc-1024x665.jpg")
                .content("GitHub is a developer platform that allows developers to create, store, manage and share their code. It uses Git software, providing the distributed version control of Git plus access control, bug tracking.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .post("lessons/606")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        LessonDto lessonResponseDto = response.as(LessonDto.class);
        lessonId = lessonResponseDto.getId();
    }

    @AfterMethod
    public void clean(){
        given()
                .auth().oauth2(token)
                .when()
                .delete("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(200);
    }

    @Test(description = "API: Update Lesson Title Positive Test")
    public void updateLessonTitlePositiveTest(){

        lessonDto.setTitle("GitHub advanced");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        LessonDto dto = response.as(LessonDto.class);
        Assert.assertTrue(dto.getTitle().contains("GitHub advanced"));
    }

    @Test(description = "API: Update Lesson Content Positive Test")
    public void updateLessonContentPositiveTest(){

        lessonDto.setContent("GitHub Advanced Security (GHAS) is a developer-first application security testing solution that brings GitHub's world-class security capabilities to public and private repositories.");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        LessonDto dto = response.as(LessonDto.class);
        Assert.assertTrue(dto.getContent().contains("GitHub Advanced Security (GHAS) is a developer-first application security testing solution that brings GitHub's world-class security capabilities to public and private repositories."));
    }


    @Test(description = "API: Update Lesson Photo Positive Test")
    public void updateLessonPhotoPositiveTest(){

        lessonDto.setPhotoPath("https://149366088.v2.pressablecdn.com/wp-content/uploads/2018/06/github-logo-768x373.jpeg");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        LessonDto dto = response.as(LessonDto.class);
        Assert.assertTrue(dto.getPhotoPath().contains("https://149366088.v2.pressablecdn.com/wp-content/uploads/2018/06/github-logo-768x373.jpeg"));
    }

}

