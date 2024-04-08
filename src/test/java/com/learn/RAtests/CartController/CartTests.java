package com.learn.RAtests.CartController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CartNotFoundError;
import com.learn.dto.CourseDto;
import com.learn.dto.ForbiddenError;
import io.restassured.response.Response;
import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CartTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();


                                 //Add to cart

                                    //Positive
                                    //Pass

    @Test(description = "API: Add course to cart Positive test", priority = 1)
    public void addCourseToCartPositiveTest(){

        given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .put("cart/add/122/1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

                                 //Negative

                                   //Pass

    @Test(description = "API: Add already exists course to cart neg test", priority = 2)
    public void addAlreadyExistsCourseToCartNegTest(){
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .put("cart/add/122/1")
                .then()
                .assertThat().statusCode(409)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Course is already in the cart");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();

        System.out.println(cartNotFoundError.getMessage());
    }


                                         //Pass
    @Test(description = "API: Add NOT exists course to cart neg test", priority = 3)
    public void addNotExistsCourseToCartNegTest(){
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .put("cart/add/122/30")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Course not found with id 30");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();

        System.out.println(cartNotFoundError.getMessage());
    }


                                    //Pass
    @Test(description = "API: Add course to cart without AUTH neg test", priority = 4)
    public void addCourseToCarWithoutAuthNegTest(){
        Response response = given()
                .contentType("application/json")
                .when()
                .put("cart/add/122/5")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/cart/add/122/5");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

                                        //Pass
    @Test(description = "API: Add course to cart with invalid path neg test", priority = 5)
    public void addCourseToCarWithInvalidPathNegTest(){
        Response response = given()
                .contentType("application/json")
                .when()
                .put("cart/adding/122/6")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));

        System.out.println(responseBody);
    }

                         /// Get all courses in the cart

                          ///Positive

    @Test(description = "API: Get courses in cart cart Positive test", priority = 6)
    public void getCoursesInCartPositiveTest(){
      Response response =  given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("cart/122")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        List<CourseDto> courseDtoList = response.jsonPath().getList(".", CourseDto.class);
        softAssert.assertNotNull(courseDtoList);

        for (CourseDto courseDto : courseDtoList) {
            softAssert.assertNotNull(courseDto.getId());
            softAssert.assertNotNull(courseDto.getTitle());
            softAssert.assertTrue(courseDto.getPrice() > 0);
            softAssert.assertNotNull(courseDto.getPhotoPath());
            softAssert.assertNull(courseDto.getPresentationPath());
            softAssert.assertNotNull(courseDto.getDescription());

            System.out.println(courseDtoList);
        }
        softAssert.assertAll();
    }

                                  //Negative

                                  //Pass
    @Test(description = "API: Get not exists course in cart neg test", priority = 7)
    public void getNotExistsCourseInCartNegTest(){
        Response response =  given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("cart/180")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Cart with ID 180 not found");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();

        System.out.println(cartNotFoundError.getMessage());
    }

                                          //Pass
    @Test(description = "API: Get courses in cart without Auth neg test", priority = 8)
    public void getCoursesInCartWithoutAUTHTest(){
        Response response =  given()
                .contentType("application/json")
                .when()
                .get("cart/122")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/cart/122");
        softAssert.assertAll();
        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

                                         //Pass
    @Test(description = "API: Get courses in cart with wrong Path neg test", priority = 9)
    public void getCoursesInCartWithWrongPathTest(){
        Response response =  given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .get("carts/122")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));

        System.out.println(responseBody);
    }

                                 //Purchasing all courses from cart
                                 //Negative
                                 //Pass
    @Test(description = "API: Course has already been purchased neg test", priority = 13)
    public void courseAlreadyPurchasedNegTest(){

        given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .put("cart/add/122/1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .put("cart/buy/122")
                .then()
                .assertThat().statusCode(409)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Enrollment with that course already exists");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();

        System.out.println(cartNotFoundError.getMessage());
    }

                                        //Pass
    @Test(description = "API: Purchase course with a non-existent cart neg test", priority = 10)
    public void purchaseCourseInNonExistentCartNegTest(){
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .put("cart/buy/180")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        CartNotFoundError cartNotFoundError = response.as(CartNotFoundError.class);
        softAssert.assertEquals(cartNotFoundError.getMessage(),"Cart with ID 180 not found");
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertEquals(response.getHeader("x-frame-options"), "DENY");
        softAssert.assertAll();

        System.out.println(cartNotFoundError.getMessage());
    }

                                       //Pass
    @Test(description = "API: Purchase course without Auth neg test", priority = 11)
    public void purchaseCourseWithoutAuthNegTest(){
        Response response = given()
                .contentType("application/json")
                .when()
                .put("cart/buy/122")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/cart/buy/122");
        softAssert.assertAll();
        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

                              ///Positive

                              //Pass
    @Test(description = "API: Pay course from cart Positive test", priority = 12)
    public void payCourseFromCartPositiveTest(){
        given()
                .contentType("application/json")
                .auth().oauth2(tokenCC)
                .when()
                .put("cart/buy/122")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

}

