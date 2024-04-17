package com.learn.RAtests.CartController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CartNotFoundError;
import com.learn.dto.ForbiddenError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class DeleteCoursesFromCartNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "API: Remove course from cart without Auth neg test")
    public void removeCourseFromCartWithoutAuthNegTest(){

        Response response = given()
                .contentType("application/json")
                .when()
                .delete("cart/830/1")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/cart/830/1");
        softAssert.assertAll();
    }


    @Test(description = "API: Remove course from not exist cart neg test")
    public void removeCourseFromNotExistCartNegTest(){
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("cart/1667/1")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Cart with ID 1667 not found");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();
    }

    @Test(description = "API: Remove all courses from not exist cart neg test")
    public void removeAllCoursesFromNotExistCartNegTest(){

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("cart/clear/1900")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Cart with ID 1900 not found");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();
    }

    @Test(description = "API: Remove all courses from cart Without Auth neg test")
    public void removeAllCoursesFromCartWithoutAuthNegTest(){

        Response response = given()
                .contentType("application/json")
                .when()
                .delete("cart/clear/830")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        Assert.assertEquals(forbiddenError.getError(), "Forbidden");
    }

}

