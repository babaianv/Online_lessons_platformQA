package com.learn.RAtests.CartController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CartNotFoundError;
import com.learn.dto.ForbiddenError;
import io.restassured.response.Response;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class DeleteCoursesFromCartTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition(){
        given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .put("cart/add/122/2")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

                                    //Pass
    @Test(description = "API: Remove course from cart by id Positive test", priority = 3)
    public void removeCourseFromCartByIdPositiveTest(){

        given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .delete("cart/122/1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

                                      //Negative
                                       //PASS
    @Test(description = "API: Remove course from cart without Auth neg test", priority = 1)
    public void removeCourseFromCartWithoutAuthNegTest(){

        Response response = given()
                .contentType("application/json")
                .when()
                .delete("cart/122/2")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/cart/122/2");
        softAssert.assertAll();
        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

                                      //PASS
    @Test(description = "API: Remove course from not exist cart neg test", priority = 2)
    public void removeCourseFromNotExistCartNegTest(){
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .delete("cart/1667/2")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Cart with ID 167 not found");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();

        System.out.println(cartNotFoundError.getMessage());
    }

                               //Delete all courses from cart

                                 //Negative
                                 //Pass
    @Test(description = "API: Remove all courses from not exist cart neg test", priority = 4)
    public void removeAllCoursesFromNotExistCartNegTest(){

        given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .put("cart/add/122/3")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .delete("cart/clear/1900")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Cart with ID 190 not found");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();

        System.out.println(cartNotFoundError.getMessage());
    }

    //Pass
    @Test(description = "API: Remove all courses from cart Without Auth neg test", priority = 5)
    public void removeAllCoursesFromCartWithoutAuthNegTest(){

        Response response = given()
                .contentType("application/json")
                .when()
                .delete("cart/clear/122")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/cart/clear/122");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

                               //Positive

    @AfterMethod
    public void removeAllCoursesFromCartPositiveTest(){
        given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .delete("cart/clear/122")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }
}

