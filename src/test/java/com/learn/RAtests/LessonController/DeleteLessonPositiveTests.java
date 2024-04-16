package com.learn.RAtests.LessonController;

import com.learn.RAtests.TestBase;
import com.learn.dto.LessonDto;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteLessonPositiveTests extends TestBase {

    int id;

    @BeforeMethod
    public void precondition() {
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

        if (response.statusCode() == 201) {
            LessonDto createdLesson = response.as(LessonDto.class);
            id = createdLesson.getId();
        } else {
            System.out.println("Failed to create lesson. Response: " + response.prettyPrint());
        }
    }

    @Test(description = "API: Delete Lesson Positive Test")
    public void deleteLessonPositiveTest() {

        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("lessons/" + id)
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

}

