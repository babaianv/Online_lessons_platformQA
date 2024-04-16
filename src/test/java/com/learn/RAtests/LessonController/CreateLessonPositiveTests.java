package com.learn.RAtests.LessonController;

import com.learn.RAtests.TestBase;
import com.learn.dto.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class CreateLessonPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    int lessonId;

    @Test(description = "API: Create Lesson Positive Test")
    public void createLessonPositiveTest() {
        LessonDto lessonDto = LessonDto.builder()
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

        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertNotNull(lessonResponseDto.getTitle());
        softAssert.assertNotNull(lessonResponseDto.getContent());
        softAssert.assertNotNull(lessonResponseDto.getPhotoPath());
        softAssert.assertNotNull(lessonResponseDto.getPhotoPath());
        softAssert.assertAll();
    }

    @AfterMethod
    public void clean() {
        given()
                .auth().oauth2(token)
                .when()
                .delete("lessons/" +lessonId)
                .then()
                .statusCode(200);
    }

}

