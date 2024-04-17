package com.learn.RAtests.LessonController;

import com.learn.RAtests.TestBase;
import com.learn.dto.ForbiddenError;
import com.learn.dto.LessonDto;
import com.learn.dto.LessonNotFoundError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class DeleteLessonNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
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

    @AfterMethod
    public void clean() {
        given()
                .auth().oauth2(token)
                .when()
                .delete("lessons/" +id)
                .then()
                .statusCode(200);
    }


    @Test(description = "API: Delete Lesson Without Auth Neg Test")
    public void deleteLessonWithoutAuthNegTest() {
        Response response =  given()
                .contentType("application/json")
                .when()
                .delete("lessons/"+id)
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertAll();
    }


    @Test(description = "API: Delete Lesson with wrong Path Neg Test")
    public void deleteLessonWithWrongPathTest() {

        Response response =   given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("less/7")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));

    }


    @Test(description = "API: Delete Not exist Lesson Neg Test")
    public void deleteNotExistLessonNegTest() {
        Response response =  given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("lessons/555")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        LessonNotFoundError lessonNotFoundError = response.as(LessonNotFoundError.class);
        Assert.assertEquals(lessonNotFoundError.getMessage(), "Lesson not found with id 555");
    }


}

