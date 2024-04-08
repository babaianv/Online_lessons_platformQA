package com.learn.RAtests.AuthorizationController;

import com.learn.RAtests.TestBase;
import com.learn.dto.UserNotAuthenticated;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.given;

public class AuthorizationInfoTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

                                  ///POSITIVE

                                  ///PASS

    @Test
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

        System.out.println(responseBody);
    }

                        ///PASS

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

        System.out.println(userNotAuthenticated.getMessage());
    }

                        //PASS

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

        System.out.println(responseBody);
    }

}

