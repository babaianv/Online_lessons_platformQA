package com.learn.RAtests.LessonController;

import com.learn.RAtests.TestBase;
import com.learn.dto.*;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class UpdateLessonsNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    private  int lessonId;
    private LessonDto lessonDto;

    @BeforeMethod
    public void precondition() {
        lessonDto = LessonDto.builder()
                .number("1")
                .title("GitHub basic")
                .photoPath("https://kanzlei-baumfalk.de/wp-content/uploads/2022/12/programmers-cooperating-at-information-technology-2021-08-28-19-22-29-utc-1024x665.jpg")
                .content("GitHub is a developer platform that allows developers to create, store, manage and share their code. It uses Git software, providing the distributed version control of Git plus access control, bug tracking.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .post("lessons/2")
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

    @Test(description = "API: Set Empty Lesson Title Neg Test")
    public void setEmptyLessonTitleNegTest(){

        lessonDto.setTitle("");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        LessonValidationError lessonValidationError = response.as(LessonValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(lessonValidationError.getMessage().contains("The title field must contain only letters and be a minimum of 5 characters and a maximum of 80 characters"));
        softAssert.assertAll();
    }


    @Test(description = "API: Set Short Lesson Title Neg Test")
    public void setShortLessonTitleNegTest(){

        lessonDto.setTitle("Git");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        LessonValidationError lessonValidationError = response.as(LessonValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(lessonValidationError.getMessage().contains("The title field must contain only letters and be a minimum of 5 characters and a maximum of 80 characters"));
        softAssert.assertAll();
    }

    @Test(description = "API: Set numbers Lesson Title Neg Test")
    public void setOnlyNumbersLessonTitleNegTest(){

        lessonDto.setTitle("12345");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        LessonValidationError lessonValidationError = response.as(LessonValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(lessonValidationError.getMessage().contains("The title field must contain only letters and be a minimum of 5 characters and a maximum of 80 characters"));
        softAssert.assertAll();
    }

    @Test(description = "API: Set Empty Lesson Content Neg Test")
    public void setEmptyLessonContentNegTest(){

        lessonDto.setContent("");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        LessonValidationError lessonValidationError = response.as(LessonValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(lessonValidationError.getMessage().contains("The content field must contain minimum 5 and maximum 300 characters."));
        softAssert.assertAll();
    }

    @Test(description = "API: Set short Lesson Content Neg Test")
    public void setShortLessonContentNegTest(){

        lessonDto.setContent("Git");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        LessonValidationError lessonValidationError = response.as(LessonValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(lessonValidationError.getMessage().contains("The content field must contain minimum 5 and maximum 300 characters."));
        softAssert.assertAll();
    }

    @Test(description = "API: Set empty Lesson Photo Neg Test")
    public void setEmptyLessonPhotoNegTest(){

        lessonDto.setPhotoPath("");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        LessonValidationError lessonValidationError = response.as(LessonValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(lessonValidationError.getMessage().contains("must not be blank"));
        softAssert.assertAll();
    }

    @Test(description = "API: Update Lesson Title Without Auth Neg Test")
    public void updateLessonTitleWithoutAuthNegTest(){

        lessonDto.setTitle("JavaScript");
        Response response = given()
                .contentType("application/json")
                .body(lessonDto)
                .when()
                .put("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertAll();
    }

    @Test(description = "API: Update Lesson Title with Invalid Path Neg Test")
    public void updateLessonTitleWithInvalidPathNegTest(){

        lessonDto.setTitle("JavaScript");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("less/"+lessonId)
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();

        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(responseBody.contains("Not Found"));
        softAssert.assertAll();
    }

    @Test(description = "API: For NOT exist Lesson update title Neg Test")
    public void updateTitleFprNotExistLessonNegTest(){

        lessonDto.setTitle("JavaScript");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .put("lessons/1000")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        LessonNotFoundError lessonNotFoundError = response.as(LessonNotFoundError.class);

        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(lessonNotFoundError.getMessage().contains("Lesson not found with id 1000"));
        softAssert.assertAll();
    }

}



