package com.learn.RAtests.CourseController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CourseDto;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class DeleteCoursePositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    private int courseId;


    @BeforeMethod
    public void precondition(){

        Response course= given()
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

        CourseDto courseResponseDto = course.as(CourseDto.class);
        courseId = courseResponseDto.getId();
        RestAssured.defaultParser = Parser.JSON;
    }


    @Test(description = "API: Delete course by Id Positive Test")
    public void deleteCourseByIdPositiveTest(){

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("courses/"+courseId)
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        CourseDto courseDto = response.as(CourseDto.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.header("content-length"), "0");
    }


}

