package com.learn.RAtests.CartController;

import com.learn.RAtests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BuyCoursePositiveTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/add/830/1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    @Test(description = "API: Pay course from cart Positive test")
    public void payCourseFromCartPositiveTest(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/buy/830")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

}

