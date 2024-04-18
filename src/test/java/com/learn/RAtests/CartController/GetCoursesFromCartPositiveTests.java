package com.learn.RAtests.CartController;

import com.learn.RAtests.TestBase;
import com.learn.dto.CourseDto;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetCoursesFromCartPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    @BeforeMethod
    public void precondition(){

        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("cart/add/1/2")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    @Test(description = "API: Get courses in cart cart Positive test")
    public void getCoursesInCartPositiveTest(){

        Response response =  given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("cart/1")
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
        }
        softAssert.assertAll();
    }

    @AfterMethod
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

