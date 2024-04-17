package com.learn.RAtests.AuthorizationController;

import com.learn.RAtests.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LogoutPositiveTests extends TestBase {

    @Test(description = "API: Logout Positive Test")
    public void logoutPositiveTest(){

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("auth/logout")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        Assert.assertEquals(response.header("content-length"), "0");
    }
}

