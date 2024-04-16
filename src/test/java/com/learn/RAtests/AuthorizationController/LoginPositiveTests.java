package com.learn.RAtests.AuthorizationController;

import com.learn.RAtests.TestBase;
import com.learn.dto.TokenResponseDto;
import com.learn.dto.UserLoginDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LoginPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    UserLoginDto login = UserLoginDto.builder()
            .email("test1@gmail.com")
            .password("Test1test1!")
            .build();


    @Test(description = "API: Test for successful login")
    public void loginSuccessTest(){

        Response response = given()
                .contentType("application/json")
                .body(login)
                .when()
                .post("auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        TokenResponseDto dto = response.as(TokenResponseDto.class);

        softAssert.assertNotNull(dto.getAccessToken(), "Access token should not be null");
        softAssert.assertNotNull(dto.getRefreshToken(), "Refresh token should not be null");
        softAssert.assertEquals(dto.getMessage(), null,"Message should be null for successful login");
        softAssert.assertAll();
    }


    @Test(description = "API: Positive test for Content-Type")
    public void positiveContentTypeTest() {
        Response response = given()
                .contentType("application/json")
                .body(login)
                .post("auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        Assert.assertEquals(response.contentType(),"application/json");
    }


    @Test(description = "API: Response Time Is Less Than 500ms ")
    public void responseTimeIsLessThan500msTest() {
        Response response = given()
                .contentType("application/json")
                .body(login)
                .post("auth/login")
                .then()
                .time(lessThan(500L))
                .assertThat().statusCode(200)
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);

    }

}

