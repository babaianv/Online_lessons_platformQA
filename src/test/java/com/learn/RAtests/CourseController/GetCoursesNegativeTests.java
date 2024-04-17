package com.learn.RAtests.CourseController;

import com.learn.RAtests.TestBase;
import com.learn.dto.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.given;

public class GetCoursesNegativeTests extends TestBase {
    SoftAssert softAssert = new SoftAssert();

    @Test(description = "API: Get Not Exist Course by id Neg Test")
    public void getNotExistCourseByIdNegTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("courses/1200")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CourseNotFoundError courseNotFoundDto = response.as(CourseNotFoundError.class);
        Assert.assertEquals(courseNotFoundDto.getMessage(), "Course not found with id 1200");
    }


    @Test(description = "API: Get Course by id With Invalid Path Neg Test")
    public void getCourseByIdWithInvalidPathNegTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("cour/1")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));
    }


    @Test(description = "API: Get All Courses with Invalid Path Test")
    public void getAllCoursesWithInvalidPathTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("cours")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));
    }

    @Test(description = "API: Get user Course without AUTH Neg Test")
    public void getAllCoursesByUserWithoutAuthNegTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("courses/created/Test1")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertAll();
    }


    @Test(description = "API: Get User Courses with Invalid Path Test")
    public void getUserCoursesWithInvalidPathTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("courses/cr/Test1")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));
    }



    @Test(description = "API: Get all purchased Courses of user without AUTH neg Test")
    public void getCoursesPurchasedByUserWithoutAuthTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("courses/available/Test1")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/courses/available/Testcc");
        softAssert.assertAll();
    }


    @Test(description = "API: Get all purchased Courses of not exist user neg Test")
    public void getCoursesPurchasedByNotExistUserNegTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("courses/available/Testnoex")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        UserNotFoundError userNotFoundError = response.as(UserNotFoundError.class);
        softAssert.assertEquals(userNotFoundError.getMessage(),"User with this username not found");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();
    }


    @Test(description = "API: Get all purchased Courses of user with wrong Path neg Test")
    public void getCoursesPurchasedByUserWithWrongPathNegTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("course/avail/Test1")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));
    }

}

