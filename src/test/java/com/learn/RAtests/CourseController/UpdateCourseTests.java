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

public class UpdateCourseTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    private int courseId;

    @BeforeMethod
    public void precondition(){

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(CourseDto.builder()
                        .title("Devops from begin to advance")
                        .price(600)
                        .photoPath("https://plus.unsplash.com/premium_photo-1661281350976-59b9514e5364?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
//            .presentationPath("https://images.unsplash.com/photo-1515378791036-0648a3ef77b2?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
                        .description(
                                "A DevOps course can teach participants various essential skills and concepts related to software development, IT operations, and collaboration. Students can learn about continuous integration and continuous delivery (CI/CD) pipelines, automation tools like Docker and Kubernetes, cloud computing platforms such as AWS or Azure, infrastructure as code (IaC) principles with tools like Terraform, configuration management tools like Ansible, monitoring and logging techniques, and best practices for collaboration and communication among development and operations teams. Additionally, they may gain insights into agile methodologies, DevOps culture, and the importance of feedback loops for iterative improvement. Overall, a DevOps course equips learners with the knowledge and skills needed to streamline development processes, enhance deployment efficiency, and foster a culture of collaboration and innovation within organizations.")
                        .build())
                .when()
                .post("courses/Testcc")
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto courseResponseDto = response.as(CourseDto.class);
        courseId = courseResponseDto.getId();
    }

                                      ///PASS (Title)

    @Test(description = "API: Change Course Title Positive Test", priority = 1)
    public void changeCourseTitlePositiveTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto courseResponseDto = getResponse.as(CourseDto.class);
        courseResponseDto.setTitle("Testte change Title");

        Response updateResponse = given()
                .contentType("application/json")
                .body(courseResponseDto)
                .auth().oauth2(tokenCC)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        CourseDto updatedCourseDto = updateResponse.as(CourseDto.class);

        softAssert.assertEquals(updateResponse.contentType(), "application/json");
        softAssert.assertNotNull(updatedCourseDto.getTitle());

        softAssert.assertAll();

        System.out.println(updatedCourseDto);
    }

                          //    ///PASS (Price)

    @Test(description = "API: Change Course Price Positive Test", priority = 2)
    public void changeCoursePricePositiveTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/" +courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto courseResponseDto = getResponse.as(CourseDto.class);
        courseResponseDto.setPrice(300);

        Response updateResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
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

        System.out.println(updatedCourseDto);
    }


//    //PASS (PhotoPath)

    @Test(description = "API: Change Course Photo Positive Test", priority = 3)
    public void changeCoursePhotoPositiveTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto courseResponseDto = getResponse.as(CourseDto.class);
        courseResponseDto.setPhotoPath("https://images.unsplash.com/photo-1448932223592-d1fc686e76ea?q=80&w=869&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");

        Response updateResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
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

        System.out.println(updatedCourseDto);
    }

                              //PASS (Description)

    @Test(description = "API: Change Course Description Positive Test", priority = 4)
    public void changeCourseDescriptionPositiveTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto courseResponseDto = getResponse.as(CourseDto.class);
        courseResponseDto.setDescription("In a DevOps course, students can learn about the fundamental principles of DevOps, including collaboration, automation, and continuous improvement. They can gain practical experience with various DevOps tools and technologies such as version control systems like Git, build automation tools like Jenkins, infrastructure automation tools like Chef and Puppet, and monitoring tools like Prometheus and Grafana. Additionally, they may explore topics such as microservices architecture, containerization with Docker, infrastructure provisioning on cloud platforms, and security practices in DevOps. The course may also cover DevOps culture and organizational change management, emphasizing the importance of teamwork, communication, and shared responsibility. ");

        Response updateResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
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

        System.out.println(updatedCourseDto);
    }

                                      //NEGATIVE
                                    ///TITLE

    @Test(description = "API: Update Course Title To Empty Neg Test", priority = 5)
    public void updateCourseTitleToEmptyNegTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/" +courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto courseResponseDto = getResponse.as(CourseDto.class);
        courseResponseDto.setTitle("");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(courseResponseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains(
                "The title field must contain only letters and be a minimum of 5 characters."));
        softAssert.assertAll();

        System.out.println(courseValidationError.getMessage());
    }

                            ///PASS

    @Test(description = "API: Update Course Title To Short Neg Test", priority = 6)
    public void updateCourseTitleToShortNegTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/" +courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto currentCourseDto = getResponse.as(CourseDto.class);
        currentCourseDto.setTitle("Dev");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(currentCourseDto)
                .when()
                .put("courses/" +courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains
                ("The title field must contain only letters and be a minimum of 5 characters."));
        softAssert.assertAll();

        System.out.println(courseValidationError.getMessage());
    }

                         ///PASS

    @Test(description = "API: Update Course Title to only numbers Neg Test", priority = 7)
    public void updateCourseTitleNumbersNegTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto currentCourseDto = getResponse.as(CourseDto.class);
        currentCourseDto.setTitle("1234567");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(currentCourseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains
                ("The title field must contain only letters and be a minimum of 5 characters."));

        softAssert.assertAll();
        System.out.println(courseValidationError.getMessage());
    }

                              ///FAILED (Fixed)

    @Test(description = "API: Update Course negative Price Neg Test", priority = 8)
    public void updateCourseNegativePriceNegTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/" +courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto currentCourseDto = getResponse.as(CourseDto.class);
        currentCourseDto.setPrice(-300);

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(currentCourseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains("The price must be positive"));
        softAssert.assertAll();
        System.out.println(courseValidationError.getMessage());
    }

                                ///PhotoPath
                                   ///Bug

    @Test(description = "API: Update Course empty Photo Neg Test", priority = 9)
    public void updateCourseEmptyPhotoNegTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto currentCourseDto = getResponse.as(CourseDto.class);
        currentCourseDto.setPhotoPath("");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(currentCourseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains("must not be blank"));
        softAssert.assertAll();

        System.out.println(courseValidationError.getMessage());
    }

                                              /////PASS
    @Test(description = "API: Set short Course Description Neg Test", priority = 10)
    public void setShortCourseDescriptionNegTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto currentCourseDto = getResponse.as(CourseDto.class);
        currentCourseDto.setDescription("In a DevOps course, students can learn about the fundamental principles of DevOps, including collaboration, automation, and continuous improvement.");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(currentCourseDto)
                .when()
                .put("courses/"+courseId)
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        CourseValidationError courseValidationError = response.as(CourseValidationError.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(courseValidationError.getMessage().contains
                ("The description field must contain minimum 300 characters."));

        softAssert.assertAll();
        System.out.println(courseValidationError.getMessage());
    }


                                     ///PASS
    @Test(description = "API: Update Course Without Auth Neg Test", priority = 11)
    public void updateCourseWithoutAuthNegTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto currentCourseDto = getResponse.as(CourseDto.class);
        currentCourseDto.setPrice(800);

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

        System.out.println(forbiddenError.getError() + " " + forbiddenError.getStatus());
    }

                                             ///PASS

    @Test(description = "API: Update Course with Wrong Path Neg Test", priority = 12)
    public void updateCourseWithWrongPathNegTest () {

        Response getResponse = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("courses/"+courseId)
                .then()
                .assertThat().statusCode(201)
                .extract().response();
        CourseDto currentCourseDto = getResponse.as(CourseDto.class);
        currentCourseDto.setPrice(650);

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .body(currentCourseDto)
                .when()
                .put("cous/2230")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));

        System.out.println(responseBody);
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

