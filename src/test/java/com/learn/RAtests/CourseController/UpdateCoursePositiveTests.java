package com.learn.RAtests.CourseController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CourseDto;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class UpdateCoursePositiveTests extends TestBase {

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


    @Test(description = "API: Change Course Title Positive Test")
    public void changeCourseTitlePositiveTest () {
        courseResponseDto.setTitle("Test change Title");

        Response updateResponse = given()
                .contentType("application/json")
                .body(courseResponseDto)
                .auth().oauth2(token)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        CourseDto updatedCourseDto = updateResponse.as(CourseDto.class);

        softAssert.assertEquals(updateResponse.contentType(), "application/json");
        softAssert.assertNotNull(updatedCourseDto.getTitle());
        softAssert.assertAll();
    }


    @Test(description = "API: Change Course Price Positive Test")
    public void changeCoursePricePositiveTest () {
        courseResponseDto.setPrice(300);

        Response updateResponse = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseResponseDto)
                .when()
                .put("courses/" +courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        CourseDto updatedCourseDto = updateResponse.as(CourseDto.class);
        softAssert.assertEquals(updateResponse.contentType(), "application/json");
        softAssert.assertNotNull(updatedCourseDto.getPrice());
        softAssert.assertAll();
    }


    @Test(description = "API: Change Course Photo Positive Test")
    public void changeCoursePhotoPositiveTest () {
        courseResponseDto.setPhotoPath("https://images.unsplash.com/photo-1448932223592-d1fc686e76ea?q=80&w=869&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");

        Response updateResponse = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseResponseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        CourseDto updatedCourseDto = updateResponse.as(CourseDto.class);

        softAssert.assertEquals(updateResponse.contentType(), "application/json");
        softAssert.assertNotNull(updatedCourseDto.getPhotoPath());
        softAssert.assertAll();
    }


    @Test(description = "API: Change Course Description Positive Test")
    public void changeCourseDescriptionPositiveTest () {
        courseResponseDto.setDescription("In a DevOps course, students can learn about the fundamental principles of DevOps, including collaboration, automation, and continuous improvement. They can gain practical experience with various DevOps tools and technologies such as version control systems like Git, build automation tools like Jenkins, infrastructure automation tools like Chef and Puppet, and monitoring tools like Prometheus and Grafana. Additionally, they may explore topics such as microservices architecture, containerization with Docker, infrastructure provisioning on cloud platforms, and security practices in DevOps. The course may also cover DevOps culture and organizational change management, emphasizing the importance of teamwork, communication, and shared responsibility. ");

        Response updateResponse = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(courseResponseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        CourseDto updatedCourseDto = updateResponse.as(CourseDto.class);

        softAssert.assertEquals(updateResponse.contentType(), "application/json");
        softAssert.assertNotNull(updatedCourseDto.getDescription());
        softAssert.assertAll();
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

