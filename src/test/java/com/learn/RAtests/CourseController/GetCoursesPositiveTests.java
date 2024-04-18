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
public class GetCoursesPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "API: Get Course By Id Positive Test")
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
    }

    @Test(description = "API: Get All Courses Positive Test")
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
            softAssert.assertNotNull(courseDto.getDescription());
        }

        softAssert.assertAll();
    }

    @Test(description = "API: Get all created Courses of user Positive Test")
    public void getCourseByUserCreatedPositiveTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("courses/created/Test1")
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
        }
        softAssert.assertAll();
    }

    @Test(description = "API: Get all purchased Courses of user Positive Test")
    public void getCoursesPurchasedByUserPositiveTest() {

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("courses/available/Test1")
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

}

