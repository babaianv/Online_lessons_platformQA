package com.learn.RAtests.LessonController;

import com.learn.RAtests.TestBase;
import com.learn.dto.ForbiddenError;
import com.learn.dto.LessonDto;
import com.learn.dto.LessonNotFoundError;
import com.learn.dto.LessonValidationError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class CreateLessonNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    int lessonId;

    @Test(description = "API: Create Lesson with Empty Title Neg Test")
    public void createLessonWithEmptyTitleTest() {
        LessonDto lessonDto = LessonDto.builder()
                .number("1")
                .photoPath("https://kanzlei-baumfalk.de/wp-content/uploads/2022/12/programmers-cooperating-at-information-technology-2021-08-28-19-22-29-utc-1024x665.jpg")
                .content("GitHub is a developer platform that allows developers to create, store, manage and share their code. It uses Git software, providing the distributed version control of Git plus access control, bug tracking.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .post("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        LessonValidationError lessonValidationError = response.as(LessonValidationError.class);
        Assert.assertTrue(lessonValidationError.getMessage().contains("must not be null"));
    }

    @Test(description = "API: Create Lesson with Empty Content Neg Test")
    public void createLessonWithEmptyContentTest() {
        LessonDto lessonDto = LessonDto.builder()
                .number("1")
                .title("GitHub basic")
                .photoPath("https://kanzlei-baumfalk.de/wp-content/uploads/2022/12/programmers-cooperating-at-information-technology-2021-08-28-19-22-29-utc-1024x665.jpg")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .post("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        LessonValidationError lessonValidationError = response.as(LessonValidationError.class);
        Assert.assertTrue(lessonValidationError.getMessage().contains("must not be null"));
    }

    @Test(description = "API: Create Lesson with Empty Photo Neg Test")
    public void createLessonWithEmptyPhotoTest() {
        LessonDto lessonDto = LessonDto.builder()
                .number("1")
                .title("GitHub basic")
                .content("GitHub is a developer platform that allows developers to create, store, manage and share their code. It uses Git software, providing the distributed version control of Git plus access control, bug tracking.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(lessonDto)
                .when()
                .post("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        LessonValidationError lessonValidationError = response.as(LessonValidationError.class);
        Assert.assertTrue(lessonValidationError.getMessage().contains("must not be null"));
    }


    @Test(description = "API: Create Lesson Without Auth Neg Test")
    public void createLessonWithoutAuthNegTest() {
        LessonDto lessonDto = LessonDto.builder()
                .number("1")
                .title("GitHub basic")
                .photoPath("https://kanzlei-baumfalk.de/wp-content/uploads/2022/12/programmers-cooperating-at-information-technology-2021-08-28-19-22-29-utc-1024x665.jpg")
                .content("GitHub is a developer platform that allows developers to create, store, manage and share their code. It uses Git software, providing the distributed version control of Git plus access control, bug tracking.")
                .build();

        Response response = given()
                .contentType("application/json")
                .body(lessonDto)
                .when()
                .post("lessons/"+lessonId)
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertAll();
    }

    @Test(description = "API: Create Lesson with wrong Path Neg Test")
    public void createLessonWithWrongPathNegTest() {
        LessonDto lessonDto = LessonDto.builder()
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
                .post("les/"+lessonId)
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));
    }

    @Test(description = "API: Create Lesson for NOT exist Course Neg Test")
    public void createLessonForNotExistCourseNegTest() {
        LessonDto lessonDto = LessonDto.builder()
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
                .post("lessons/6060")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        LessonNotFoundError lessonNotFoundError = response.as(LessonNotFoundError.class);
        Assert.assertEquals(lessonNotFoundError.getMessage(), "Course not found with id 6060");
    }


}

