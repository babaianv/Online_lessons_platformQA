package com.learn.RAtests.CartController;

import com.learn.RAtests.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddCourseToCartPositiveTests extends TestBase {

    @Test(description = "API: Add course to cart Positive test")
    public void addCourseToCartPositiveTest(){

        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/add/1/4")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    @AfterMethod
    public void cleanup() {
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("cart/clear/1");
    }

}

