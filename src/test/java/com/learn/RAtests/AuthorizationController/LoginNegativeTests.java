package com.learn.RAtests.AuthorizationController;

import com.learn.RAtests.TestBase;
import com.learn.dto.TokenResponseDto;
import com.learn.dto.UserLoginDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class LoginNegativeTests extends TestBase {
    SoftAssert softAssert = new SoftAssert();

    UserLoginDto login = UserLoginDto.builder()
            .email("test1@gmail.com")
            .password("Test1test1!")
            .build();

    @Test(description = "API: Login without Content-Type")
    public void loginWithoutContentTypeTest() {
        Response response = given()
                .body(login)
                .post("auth/login")
                .then()
                .statusCode(415)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Unsupported Media Type"));

        System.out.println("Response body: " + responseBody);
    }



    @Test(description = "API: Login with wrong Path")
    public void loginWithWrongPathTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserLoginDto.builder()
                        .email("test1@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("auth/log")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        softAssert.assertTrue(responseBody.contains("Not Found"));
        softAssert.assertTrue(responseBody.contains("/api/auth/log"));
        softAssert.assertAll();

        System.out.println(responseBody);
    }



    @Test(description = "API: Login with wrong Email ")
    public void loginWithWrongEmailTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserLoginDto.builder()
                        .email("test@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("auth/login")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        TokenResponseDto dto = response.as(TokenResponseDto.class);
        softAssert.assertEquals(dto.getAccessToken(),null);
        softAssert.assertEquals(dto.getRefreshToken(), null);
        softAssert.assertTrue(dto.getMessage().contains("Email or password is incorrect"));

        softAssert.assertAll();

        System.out.println("Response body: " +dto);
    }



    @Test(description = "API: Login with empty Email")
    public void loginWithEmptyEmailTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserLoginDto.builder()
                        .email("")
                        .password("Test1test1!").build())
                .when()
                .post("auth/login")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        TokenResponseDto dto = response.as(TokenResponseDto.class);
        softAssert.assertEquals(dto.getAccessToken(),null);
        softAssert.assertEquals(dto.getRefreshToken(), null);
        softAssert.assertTrue(dto.getMessage().contains("Email or password is incorrect"));
        softAssert.assertAll();

        System.out.println("Response body: " +dto);
    }


    @Test(description = "API: Login with wrong Password")
    public void loginWithWrongPasswordTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserLoginDto.builder()
                        .email("test1@gmail.com")
                        .password("Wrongpassword1!").build())
                .when()
                .post("auth/login")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        TokenResponseDto dto = response.as(TokenResponseDto.class);
        softAssert.assertEquals(dto.getAccessToken(),null);
        softAssert.assertEquals(dto.getRefreshToken(), null);
        softAssert.assertTrue(dto.getMessage().contains("Email or password is incorrect"));
        softAssert.assertAll();

        System.out.println("Response body: " +dto);
    }


    @Test(description = "API: Login with empty Password")
    public void loginWithEmptyPasswordTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserLoginDto.builder()
                        .email("test1@gmail.com")
                        .password("").build())
                .when()
                .post("auth/login")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        TokenResponseDto dto = response.as(TokenResponseDto.class);
        softAssert.assertEquals(dto.getAccessToken(),null);
        softAssert.assertEquals(dto.getRefreshToken(), null);
        softAssert.assertTrue(dto.getMessage().contains("Email or password is incorrect"));
        softAssert.assertAll();

        System.out.println("Response body: " +dto);
    }
}

