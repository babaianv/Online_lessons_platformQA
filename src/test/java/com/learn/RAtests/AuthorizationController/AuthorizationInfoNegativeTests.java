package com.learn.RAtests.AuthorizationController;

import com.learn.RAtests.TestBase;
import com.learn.dto.ForbiddenError;
import com.learn.dto.UserNotAuthenticated;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.given;

public class AuthorizationInfoNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    @Test(description = "Get Authorized Info Without Auth Neg Test")
    public void getAuthInfoWithoutAuthNegTest(){

        Response response = given()
                .contentType("application/json")
                .when()
                .get("auth/auth_info")
                .then()
                .assertThat().statusCode(401)
                .extract().response();

        UserNotAuthenticated userNotAuthenticated = response.as(UserNotAuthenticated.class);

        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(userNotAuthenticated.getMessage().contains("User is not authenticated"));
        softAssert.assertAll();
    }


    @Test(description = "Get Authorized Info With invalid Path Neg Test")
    public void getAuthInfoWithInvalidPathNegTest(){

        Response response = given()
                .contentType("application/json")
                .when()
                .get("auth/auinfo")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();

        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(responseBody.contains("Not Found"));
        softAssert.assertTrue(responseBody.contains("/api/auth/auinfo"));
        softAssert.assertAll();
    }

}

