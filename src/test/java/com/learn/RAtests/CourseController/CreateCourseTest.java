package com.learn.RAtests.CourseController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CourseDto;
import com.learn.dto.CourseValidationError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class CreateCourseTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    private int courseId;
                                   /////PASS

    @Test(description = "API: Create Course Positive Test", priority = 1)
    public void createCoursePositiveTest() {
        CourseDto courseDto = CourseDto.builder()
                .title("Devops from begin to advance")
                .price(600)
                .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
//            .presentationPath("https://images.unsplash.com/photo-1515378791036-0648a3ef77b2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .description(
                        "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(courseDto)
                .when()
                .post("courses/Testcc")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        CourseDto courseResponseDto = response.as(CourseDto.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseResponseDto.getPrice() > 0);

        softAssert.assertNotNull(courseResponseDto.getId());
        softAssert.assertNotNull(courseResponseDto.getTitle());
        softAssert.assertNotNull(courseResponseDto.getPrice());
        softAssert.assertNotNull(courseResponseDto.getPhotoPath());
        softAssert.assertNull(courseResponseDto.getPresentationPath());
        softAssert.assertNotNull(courseResponseDto.getDescription());
        softAssert.assertAll();

        System.out.println(courseResponseDto);
         courseId = courseResponseDto.getId();

    }

    ///Negative
    //PASS

    @Test(description = "API: Create Course Empty Title Neg Test", priority = 2)
    public void createCourseEmptyTitleNegTest() {

        CourseDto courseDto = CourseDto.builder()
                .title("")
                .price(600)
                .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
//            .presentationPath("https://images.unsplash.com/photo-1515378791036-0648a3ef77b2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .description(
                        "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(courseDto)
                .when()
                .post("courses/Testcc")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        Assert.assertTrue(courseValidationError.getMessage().contains("The title field must contain only letters and be a minimum of 5 characters."));

        System.out.println(courseValidationError.getMessage());
    }

    ///PASS

    @Test(description = "API: Create Course Title with numbers Neg Test", priority = 3)
    public void createCourseTitleWithNumbersNegTest() {

        CourseDto courseDto = CourseDto.builder()
                .title("Devops 1234")
                .price(600)
                .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
//            .presentationPath("https://images.unsplash.com/photo-1515378791036-0648a3ef77b2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .description(
                        "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(courseDto)
                .when()
                .post("courses/Testcc")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        Assert.assertTrue(courseValidationError.getMessage()
                .contains("The title field must contain only letters and be a minimum of 5 characters."));

        System.out.println(courseValidationError.getMessage());
    }

    ///PASS

    @Test(description = "API: Create Course short Title Neg Test", priority = 4)
    public void createCourseShortTitleNegTest() {

        CourseDto titleEmpty = CourseDto.builder()
                .title("Devo")
                .price(600)
                .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
//            .presentationPath("https://images.unsplash.com/photo-1515378791036-0648a3ef77b2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .description(
                        "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(titleEmpty)
                .when()
                .post("courses/Testcc")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        Assert.assertTrue(courseValidationError.getMessage()
                .contains("The title field must contain only letters and be a minimum of 5 characters."));

        System.out.println(courseValidationError.getMessage());
    }

    ///PASS

    @Test(description = "API: Create Course negative price Neg Test", priority = 5)
    public void createCourseNegativePriceNegTest() {

        CourseDto titleEmpty = CourseDto.builder()
                .title("Devops from begin to advance")
                .price(-200)
                .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
//            .presentationPath("https://images.unsplash.com/photo-1515378791036-0648a3ef77b2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                .description(
                        "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                .build();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(titleEmpty)
                .when()
                .post("courses/Testcc")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(courseValidationError.getMessage(),
                "The price must be positive.");

        System.out.println(courseValidationError.getMessage());
    }

    ///PhotoPath

    //Failed   Bug -> T25-87

    @Test(description = "API: Create Course without photoPath Neg Test", priority = 6)
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
                .auth().oauth2(tokenCC)
                .body(titleEmpty)
                .when()
                .post("courses/Testcc")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertNotNull(courseValidationError.getMessage());

        System.out.println(courseValidationError.getMessage());
    }

    @AfterMethod
    public void cleanup() {
        given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .delete("courses/"+courseId);

    }
}

