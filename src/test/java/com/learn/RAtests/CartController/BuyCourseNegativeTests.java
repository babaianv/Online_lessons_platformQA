package com.learn.RAtests.CartController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CartNotFoundError;
import com.learn.dto.ForbiddenError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class BuyCourseNegativeTests extends TestBase {
    SoftAssert softAssert = new SoftAssert();

    @Test(description = "API: Course has already been purchased neg test")
    public void courseAlreadyPurchasedNegTest(){

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/buy/1")
                .then()
                .assertThat().statusCode(409)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Enrollment with that course already exists");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();
    }

    @Test(description = "API: Purchase course with a non-existent cart neg test")
    public void purchaseCourseInNonExistentCartNegTest(){
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/buy/1800")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Cart with ID 1800 not found");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();
    }

    @Test(description = "API: Purchase course without Auth neg test")
    public void purchaseCourseWithoutAuthNegTest(){
        Response response = given()
                .contentType("application/json")
                .when()
                .put("cart/buy/1")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        Assert.assertEquals(forbiddenError.getError(), "Forbidden");
    }

    @AfterSuite
    public void clean(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("cart/clear/1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }
}

