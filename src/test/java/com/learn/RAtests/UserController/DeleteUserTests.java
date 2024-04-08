package com.learn.RAtests.UserController;

import com.learn.RAtests.TestBase;
import com.learn.dto.ForbiddenError;
import com.learn.dto.UserDto;
import com.learn.dto.UserNotFoundError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class DeleteUserTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    String accessToken;
    @Test(description = "API: Delete user Positive test", priority = 2)
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
                                       ///NEGATIVE

                                  ///FAILED -> Bug T25-92 FIXED

    @Test(description = "API: Delete User Without AUTH Test", priority = 1)
    public void deleteUserWithoutAUTHNegTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .delete("users/delete/TestDel")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        Assert.assertEquals(forbiddenError.getError(), "Forbidden");

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

                                //PASS (not exist user)

    @Test(description = "API: Delete Not Exists User Neg Test", priority = 3)
    public void deleteNotExistsUserNegTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenD)
                .when()
                .delete("users/delete/Tes")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        UserNotFoundError userNotFoundError = response.as(UserNotFoundError.class);
        Assert.assertEquals(userNotFoundError.getMessage(),"User with this username not found");

        System.out.println(userNotFoundError.getMessage());
    }

                                /// PATH invalid
    @Test(description = "API: Delete User With Invalid Path Neg Test", priority = 4)
    public void deleteUserWithInvalidPathNegTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenD)
                .when()
                .delete("users/del/TestdelA")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));

        System.out.println(responseBody);
    }

}

