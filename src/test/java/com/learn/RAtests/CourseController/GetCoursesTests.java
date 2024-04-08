package com.learn.RAtests.CourseController;

import com.learn.RAtests.TestBase;
import com.learn.dto.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.given;
public class GetCoursesTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

                                    ////Get Course by Id
                                       //Pass

    @Test(description = "API: Get Course By Id Positive Test", priority = 1)
    public void getCourseByIdPositiveTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("courses/1")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        CourseDto courseDto = response.as(CourseDto.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseDto.getPrice() > 0);

        softAssert.assertNotNull(courseDto.getId());
        softAssert.assertNotNull(courseDto.getTitle());
        softAssert.assertNotNull(courseDto.getPrice());
        softAssert.assertNotNull(courseDto.getPhotoPath());
        softAssert.assertNull(courseDto.getPresentationPath());
        softAssert.assertNotNull(courseDto.getDescription());
        softAssert.assertAll();

        System.out.println(courseDto);
    }

    //NEGATIVE

    ///PASS

    @Test(description = "API: Get Not Exist Course by id Neg Test", priority = 2)
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

        System.out.println(courseNotFoundDto);
    }

    //PASS

    @Test(description = "API: Get Course by id With Invalid Path Neg Test", priority = 3)
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

        System.out.println(responseBody);
    }

    ///Get all courses

    //POSITIVE

    @Test(description = "API: Get All Courses Positive Test", priority = 4)
    public void getAllCoursesPositiveTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("courses")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        List<CourseDto> courseDtoList = response.jsonPath().getList(".", CourseDto.class);
        softAssert.assertNotNull(courseDtoList);

        for (CourseDto courseDto : courseDtoList) {
            softAssert.assertNotNull(courseDto.getId());
            softAssert.assertNotNull(courseDto.getTitle());
            softAssert.assertTrue(courseDto.getPrice() > 0);
            softAssert.assertNotNull(courseDto.getPhotoPath());
            softAssert.assertNull(courseDto.getPresentationPath());
            softAssert.assertNotNull(courseDto.getDescription());

            System.out.println(courseDtoList);
        }
        softAssert.assertAll();
    }

    ///NEGATIVE

    @Test(description = "API: Get All Courses with Invalid Path Test", priority = 5)
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

        System.out.println(responseBody);
    }

    /// Getting user created courses

    ///POSITIVE

    @Test(description = "API: Get all created Courses of user Positive Test", priority = 6)
    public void getCourseByUserCreatedPositiveTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/created/Testcc")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        List<CourseDto> courseDtoList = response.jsonPath().getList(".", CourseDto.class);
        softAssert.assertNotNull(courseDtoList);

        for (CourseDto courseDto : courseDtoList) {
            softAssert.assertNotNull(courseDto.getId());
            softAssert.assertNotNull(courseDto.getTitle());
            softAssert.assertTrue(courseDto.getPrice() > 0);
            softAssert.assertNotNull(courseDto.getPhotoPath());
            softAssert.assertNull(courseDto.getPresentationPath());
            softAssert.assertNotNull(courseDto.getDescription());

            System.out.println(courseDtoList);
        }
        softAssert.assertAll();
    }

    ////NEGATIVE
    ///PASS

    @Test(description = "API: Get user Course without AUTH  Neg Test", priority = 7)
    public void getAllCoursesByUserWithoutAuthNegTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("courses/created/Testcc")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() + " " + forbiddenError.getStatus());
    }

    ///PASS

    @Test(description = "API: Get User Courses with Invalid Path Test", priority = 8)
    public void getUserCoursesWithInvalidPathTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("courses/cr/Testcc")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));

        System.out.println(responseBody);
    }

                                     ///Get courses purchased by the user

                                     ///POSITIVE

    @Test(description = "API: Get all purchased Courses of user Positive Test", priority = 9)
    public void getCoursesPurchasedByUserPositiveTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/available/Testcc")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        EnrollmentResponseDto[] enrollmentArray = response.as(EnrollmentResponseDto[].class);

        softAssert.assertNotNull(enrollmentArray);
        softAssert.assertTrue(enrollmentArray.length > 0);

        for (EnrollmentResponseDto enrollmentResponseDto : enrollmentArray) {
            softAssert.assertNotNull(enrollmentResponseDto.getId());
            softAssert.assertNotNull(enrollmentResponseDto.getEnrollmentDate());
            softAssert.assertNotNull(enrollmentResponseDto.getStatus());
            softAssert.assertNotNull(enrollmentResponseDto.getCourse());
            Course courseDto = enrollmentResponseDto.getCourse();
            softAssert.assertNotNull(courseDto.getId());
            softAssert.assertNotNull(courseDto.getTitle());
            softAssert.assertNotNull(courseDto.getPrice());
            softAssert.assertNotNull(courseDto.getPhotoPath());
            softAssert.assertNotNull(courseDto.getDescription());

            System.out.println(Arrays.toString(enrollmentArray));
        }
        softAssert.assertAll();
    }

                                        ///Pass
    @Test(description = "API: Get all purchased Courses of user without AUTH neg Test", priority = 10)
    public void getCoursesPurchasedByUserWithoutAuthTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .get("courses/available/Testcc")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/courses/available/Testcc");
        softAssert.assertAll();
        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }


                                           ///Pass
    @Test(description = "API: Get all purchased Courses of not exist user neg Test", priority = 11)
    public void getCoursesPurchasedByNotExistUserNegTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/available/Testw")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        UserNotFoundError userNotFoundError = response.as(UserNotFoundError.class);
        softAssert.assertEquals(userNotFoundError.getMessage(),"User with this username not found");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();

        System.out.println(userNotFoundError.getMessage());
    }

                                         //Pass
    @Test(description = "API: Get all purchased Courses of user with wrong Path neg Test", priority = 12)
    public void getCoursesPurchasedByUserWithWrongPathNegTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("course/avail/Testcc")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));

        System.out.println(responseBody);
    }

}

