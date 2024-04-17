package com.learn.RAtests.CartController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CartNotFoundError;
import com.learn.dto.ForbiddenError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class GetCoursesFromCartNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "API: Get course from NOT exists Cart Neg test")
    public void getCourseFromNotExistsCartNegTest(){
        Response response =  given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("cart/8300")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Cart with ID 8300 not found");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();
    }


    @Test(description = "API: Get courses in cart without Auth neg test")
    public void getCoursesInCartWithoutAUTHTest(){
        Response response =  given()
                .contentType("application/json")
                .when()
                .get("cart/1")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/cart/1");
        softAssert.assertAll();
    }


    @Test(description = "API: Get courses in cart with wrong Path neg test")
    public void getCoursesInCartWithWrongPathTest(){
        Response response =  given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("carts/1")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));
    }
}

