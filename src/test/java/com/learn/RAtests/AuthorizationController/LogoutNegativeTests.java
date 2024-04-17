package com.learn.RAtests.AuthorizationController;

import com.learn.RAtests.TestBase;
import com.learn.dto.ForbiddenError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class LogoutNegativeTests extends TestBase {
    SoftAssert softAssert = new SoftAssert();

    @Test(description = "API: Logout without AUTH Neg Test")
    public void logoutWithoutAuthNegTest(){

        Response response = given()
                .contentType("application/json")
                .when()
                .get("auth/logout")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/auth/logout");
        softAssert.assertAll();
    }


    @Test(description = "API: Logout with invalid PATH Neg Test")
    public void logoutWithInvalidPathNegTest(){

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("auth/logoutuser")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));
    }
}

