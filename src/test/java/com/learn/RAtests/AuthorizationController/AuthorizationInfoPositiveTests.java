package com.learn.RAtests.AuthorizationController;

import com.learn.RAtests.TestBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class AuthorizationInfoPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "API: Get authorized Info Positive Test ")
    public void authorizedInfoPositiveTest(){

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("auth/auth_info")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        String responseBody = response.getBody().asString();

        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertTrue(responseBody.contains("authenticated"));
        softAssert.assertTrue(responseBody.contains("cartId"));
        softAssert.assertTrue(responseBody.contains("name"));
        softAssert.assertTrue(responseBody.contains("authority"));
        softAssert.assertAll();
    }
}

