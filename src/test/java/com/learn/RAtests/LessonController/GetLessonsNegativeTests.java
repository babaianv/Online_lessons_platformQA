package com.learn.RAtests.LessonController;

import com.learn.RAtests.TestBase;
import com.learn.dto.ForbiddenError;
import com.learn.dto.LessonNotFoundError;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class GetLessonsNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "API: Get Lessons From Course without Auth Neg Test")
    public void getLessonsFromCourseWithoutAuthNegTest() {

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .get("lessons/1")
                        .then()
                        .assertThat().statusCode(403)
                        .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/lessons/1");
        softAssert.assertAll();
    }

    @Test(description = "API: Get Lessons From not exist Course Neg Test")
    public void getLessonsFromNotExistCourseNegTest() {

        Response response =
                given()
                        .contentType("application/json")
                        .auth().oauth2(token)
                        .when()
                        .get("lessons/6060")
                        .then()
                        .assertThat().statusCode(404)
                        .extract().response();

        LessonNotFoundError lessonNotFoundError = response.as(LessonNotFoundError.class);

        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(lessonNotFoundError.getMessage().contains("Course not found with id 6060"));
        softAssert.assertAll();
    }

    @Test(description = "API: Get Lessons From Course with invalid Path Neg Test")
    public void getLessonsFromCourseWithInvalidPathNegTest(){

        Response response =
                given()
                        .contentType("application/json")
                        .auth().oauth2(token)
                        .when()
                        .get("less/1")
                        .then()
                        .assertThat().statusCode(404)
                        .extract().response();

        String responseBody = response.getBody().asString();

        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(responseBody.contains("Not Found"));
        softAssert.assertTrue(responseBody.contains("/api/less/1"));
        softAssert.assertAll();
    }

}

