package com.learn.RAtests.CartController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CartNotFoundError;
import com.learn.dto.ForbiddenError;
import io.restassured.response.Response;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class DeleteCoursesFromCartPositiveTests extends TestBase {


    @Test(description = "API: Remove course from cart by id Positive test")
    public void removeCourseFromCartByIdPositiveTest(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .delete("cart/1/1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }


    @Test(description = "API: Remove all courses from cart Positive test")
    public void removeAllCoursesFromCartPositiveTest(){
        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/add/1/3")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/add/1/4")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

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

