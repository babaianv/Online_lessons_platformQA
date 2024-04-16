package com.learn.RAtests.CourseController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CourseDto;
import com.learn.dto.CourseValidationError;
import com.learn.dto.ForbiddenError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class UpdateCourseNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    private int courseId;
    CourseDto courseResponseDto;
    @BeforeMethod
    public void precondition(){

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(CourseDto.builder()
                        .title("Devops from begin to advance")
                        .price(600)
                        .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                        .description(
                                "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                        .build())
                .when()
                .post("courses/Test1")
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        courseResponseDto = response.as(CourseDto.class);
        courseId = courseResponseDto.getId();
    }


    @Test(description = "API: Update Course Title To Empty Neg Test", priority = 1)
    public void updateCourseTitleToEmptyNegTest () {

        courseResponseDto.setTitle("");
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseResponseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains(
                "The title field must contain only letters and be a minimum of 5 characters and a maximum of 80 characters."));
        softAssert.assertAll();

    }


    @Test(description = "API: Update Course Title To Short Neg Test", priority = 2)
    public void updateCourseTitleToShortNegTest () {

        courseResponseDto.setTitle("Dev");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseResponseDto)
                .when()
                .put("courses/" +courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains
                ("The title field must contain only letters and be a minimum of 5 characters and a maximum of 80 characters."));
        softAssert.assertAll();
    }


    @Test(description = "API: Update Course Title to only numbers Neg Test", priority = 3)
    public void updateCourseTitleNumbersNegTest () {

        courseResponseDto.setTitle("1234567");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseResponseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains
                ("The title field must contain only letters and be a minimum of 5 characters and a maximum of 80 characters."));

        softAssert.assertAll();
    }


    @Test(description = "API: Update Course negative Price Neg Test", priority = 4)
    public void updateCourseNegativePriceNegTest () {

        courseResponseDto.setPrice(-300);

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseResponseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains("The price must be positive"));
        softAssert.assertAll();
    }

    @Test(description = "API: Update Course empty Photo Neg Test", priority = 5)
    public void updateCourseEmptyPhotoNegTest () {

        courseResponseDto.setPhotoPath("");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseResponseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains("must not be blank"));
        softAssert.assertAll();
    }

    @Test(description = "API: Set short Course Description Neg Test", priority = 6)
    public void setShortCourseDescriptionNegTest () {

        courseResponseDto.setDescription("In a DevOps course, students can learn about the fundamental principles of DevOps, including collaboration, automation, and continuous improvement.");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseResponseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains
                ("The description field must contain minimum 300 and maximum 1800 characters."));

        softAssert.assertAll();
    }


    @Test(description = "API: Update Course Without Auth Neg Test", priority = 7)
    public void updateCourseWithoutAuthNegTest () {

        courseResponseDto.setPrice(800);

        Response response = given()
                .contentType("application/json")
                .when()
                .put("courses/" +courseId)
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertAll();
    }


    @Test(description = "API: Update Course with Wrong Path Neg Test", priority = 8)
    public void updateCourseWithWrongPathNegTest () {
        courseResponseDto.setPrice(650);

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseResponseDto)
                .when()
                .put("cous/2230")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));
    }

    @AfterMethod
    public void cleanup() {
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("courses/"+courseId);

    }
}

