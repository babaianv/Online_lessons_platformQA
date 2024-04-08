package com.learn.RAtests.AuthorizationController;

import com.learn.RAtests.TestBase;
import com.learn.dto.ForbiddenError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class LogoutTests extends TestBase {
    SoftAssert softAssert = new SoftAssert();

                              ///POSITIVE

                              ///Pass

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
                                        //NEGATIVE
                                        //PASS (Without AUTH)

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
        softAssert.assertEquals(response.getStatusCode(), 403);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/auth/logout");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

                                      ///PASS

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
        softAssert.assertEquals(response.getStatusCode(), 404);
        softAssert.assertTrue(responseBody.contains("Not Found"));
        softAssert.assertAll();

        System.out.println(responseBody);
    }
}

