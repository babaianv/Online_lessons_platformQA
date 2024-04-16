package com.learn.RAtests.UserController;

import com.learn.RAtests.TestBase;
import com.learn.dto.UserDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserPositiveTests extends TestBase {

    String accessToken;

    @Test(description = "API: Delete user Positive test")
    public void deleteUserSuccessTest() {

        Response createResponse = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("TestDel")
                        .email("testdel@gmail.com")
                        .password("Test1test1!")
                        .build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        if (createResponse.getStatusCode() != 201) {
            Assert.fail("Failed to create user. Status code: " + createResponse.getStatusCode());
        }


        Response loginResponse = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .email("testdel@gmail.com")
                        .password("Test1test1!")
                        .build())
                .when()
                .post("auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
        accessToken = loginResponse.jsonPath().getString("accessToken");

        Response deleteResponse = given()
                .contentType("application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete("users/delete/TestDel")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        Assert.assertEquals(deleteResponse.header("content-length"), "0");
        String responseBody = deleteResponse.getBody().asString();
        System.out.println("Response body: " + responseBody);
    }
}

