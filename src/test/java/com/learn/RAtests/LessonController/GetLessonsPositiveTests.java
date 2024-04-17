package com.learn.RAtests.LessonController;

import com.learn.RAtests.TestBase;
import com.learn.dto.LessonDto;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class GetLessonsPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    int id;
    @BeforeMethod
    public void precondition() {
        LessonDto lesson = LessonDto.builder()
                .title("GitHub basic")
                .photoPath("https://kanzlei-baumfalk.de/wp-content/uploads/2022/12/programmers-cooperating-at-information-technology-2021-08-28-19-22-29-utc-1024x665.jpg")
                .content("GitHub is a developer platform that allows developers to create, store, manage and share their code. It uses Git software, providing the distributed version control of Git plus access control, bug tracking.")
                .build();

       Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lesson)
                .when()
                .post("lessons/2")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

       LessonDto lessonDto = response.as(LessonDto.class);
       id = lessonDto.getId();
    }

    @AfterMethod
    public void clean(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("lessons/" +id)
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    @Test(description = "API: Get Available Lessons From Course Positive Test")
    public void getAvailableLessonsFromCoursePositiveTest() {

        Response response =
                given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("lessons/2")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        LessonDto[] lessonDtos = response.as(LessonDto[].class);

        for (LessonDto lessonDto : lessonDtos) {
            softAssert.assertNotNull(lessonDto.getTitle());
            softAssert.assertNotNull(lessonDto.getContent());
            softAssert.assertNotNull(lessonDto.getPhotoPath());
        }

        softAssert.assertAll();
    }

    @Test(description = "API: Get all Available Lessons Positive Test")
    public void getAllAvailableLessonsPositiveTest() {

        Response response =
                given()
                        .contentType("application/json")
                        .auth().oauth2(token)
                        .when()
                        .get("lessons")
                        .then()
                        .assertThat().statusCode(201)
                        .extract().response();

        LessonDto[] lessonDtos = response.as(LessonDto[].class);

        for (LessonDto lessonDto : lessonDtos) {
            softAssert.assertNotNull(lessonDto.getTitle());
            softAssert.assertNotNull(lessonDto.getContent());
            softAssert.assertNotNull(lessonDto.getPhotoPath());
        }

        softAssert.assertAll();
    }


}

