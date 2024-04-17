package com.learn.RAtests.CourseController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CourseDto;
import com.learn.dto.CourseValidationError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateCourseNegativeTests extends TestBase {

    @Test(description = "API: Create Course Empty Title Neg Test")
    public void createCourseEmptyTitleNegTest() {

        CourseDto courseDto = CourseDto.builder()
                .title("")
                .price(600)
                .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .description(
                        "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseDto)
                .when()
                .post("courses/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        Assert.assertTrue(courseValidationError.getMessage().contains("The title field must contain only letters and be a minimum of 5 characters and a maximum of 80 characters."));
    }



    @Test(description = "API: Create Course Title with numbers Neg Test")
    public void createCourseTitleWithNumbersNegTest() {

        CourseDto courseDto = CourseDto.builder()
                .title("Devops 1234")
                .price(600)
                .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .description(
                        "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseDto)
                .when()
                .post("courses/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        Assert.assertTrue(courseValidationError.getMessage()
                .contains("The title field must contain only letters and be a minimum of 5 characters and a maximum of 80 characters."));
    }

    @Test(description = "API: Create Course short Title Neg Test")
    public void createCourseShortTitleNegTest() {

        CourseDto titleEmpty = CourseDto.builder()
                .title("Devo")
                .price(600)
                .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .description(
                        "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(titleEmpty)
                .when()
                .post("courses/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        Assert.assertTrue(courseValidationError.getMessage()
                .contains("The title field must contain only letters and be a minimum of 5 characters and a maximum of 80 characters."));
    }


    @Test(description = "API: Create Course negative price Neg Test")
    public void createCourseNegativePriceNegTest() {

        CourseDto titleEmpty = CourseDto.builder()
                .title("Devops from begin to advance")
                .price(-200)
                .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .description(
                        "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(titleEmpty)
                .when()
                .post("courses/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        Assert.assertTrue(courseValidationError.getMessage().contains("The price must be positive."));

    }

    @Test(description = "API: Create Course without photoPath Neg Test")
    public void createCourseWithoutPhotoNegTest() {

        CourseDto titleEmpty = CourseDto.builder()
                .title("Devops beginner")
                .price(600)
                .photoPath("")
                .description(
                        "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(titleEmpty)
                .when()
                .post("courses/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        Assert.assertNotNull(courseValidationError.getMessage());
    }

}

