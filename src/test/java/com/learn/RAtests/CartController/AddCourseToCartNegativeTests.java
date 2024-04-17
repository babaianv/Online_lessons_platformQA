package com.learn.RAtests.CartController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CartNotFoundError;
import com.learn.dto.ForbiddenError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class AddCourseToCartNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "API: Add already exists course to cart neg test")
    public void addAlreadyExistsCourseToCartNegTest(){

        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/add/1/1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/add/1/1")
                .then()
                .assertThat().statusCode(409)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Course is already in the cart");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();
    }


    @Test(description = "API: Add NOT exists course to cart neg test")
    public void addNotExistsCourseToCartNegTest(){
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/add/1/300")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Course not found with id 300");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();
    }


    @Test(description = "API: Add course to cart without AUTH neg test")
    public void addCourseToCarWithoutAuthNegTest(){
        Response response = given()
                .contentType("application/json")
                .when()
                .put("cart/add/1/5")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/cart/add/1/5");
        softAssert.assertAll();
    }

    @Test(description = "API: Add course to cart with invalid path neg test")
    public void addCourseToCarWithInvalidPathNegTest(){
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/adding/1/5")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));
    }

}

