package com.learn.RAtests;

import com.learn.dto.ForbiddenError;
import com.learn.dto.TokenResponseDto;
import com.learn.dto.UserLoginDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LoginTests extends TestBase{

    SoftAssert softAssert = new SoftAssert();

    UserLoginDto login = UserLoginDto.builder()
            .email("test1@gmail.com")
            .password("Test1test1!")
            .build();

                               ///PASS

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

        softAssert.assertEquals(response.getStatusCode(), 200);

        softAssert.assertAll();

        System.out.println("Access Token: " + dto.getAccessToken());
        System.out.println("Refresh Token: " + dto.getRefreshToken());
        System.out.println("Message: " + dto.getMessage());

    }

                   ///PASS (Content-Type)

    @Test(description = "API: Positive test for Content-Type")
    public void positiveContentTypeTest() {
        Response response = given()
                .contentType("application/json")
                .body(login)
                .post("auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        softAssert.assertEquals(response.contentType(),"application/json");
        softAssert.assertEquals(response.getStatusCode(), 200);

        softAssert.assertAll();

        System.out.println("Content-Type: " + response.contentType());
    }

                        //PASS (Performance)

    @Test(description = "API: Login Performance for 100 users")
    public void loginPerformanceTest() {
        int requestCount = 100;
        long startTime = System.currentTimeMillis();
        List<Response> responses = new ArrayList<>();

        for (int i = 0; i < requestCount; i++) {
            Response response = RestAssured.given()
                    .contentType("application/json")
                    .body(login)
                    .when()
                    .post("auth/login")
                    .then()
                    .assertThat().statusCode(200)
                    .extract().response();
            responses.add(response);
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        softAssert.assertTrue((totalTime / requestCount) < 100,
                "Average time per request should be less than 100 milliseconds");

        for (Response response : responses) {
            softAssert.assertEquals(response.getStatusCode(), 200);
        }

        softAssert.assertAll();

        System.out.println("Total time for " + requestCount + " requests: " + totalTime + " milliseconds");
        System.out.println("Average time per request: " + (totalTime / requestCount) + " milliseconds");
    }

                         //PASS (Response time)

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

        long responseTime = response.time();
        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println("Response time: " + responseTime + " milliseconds");
    }

                                     ///NEGATIVE

                                    ///PASS

    @Test(description = "API: Login without Content-Type")
    public void loginWithoutContentTypeTest() {
        Response response = given()
                .body(login)
                .post("auth/login")
                .then()
                .statusCode(415)
                .extract().response();

        String responseBody = response.getBody().asString();

        softAssert.assertTrue(responseBody.contains("Unsupported Media Type"));
        softAssert.assertEquals(response.getStatusCode(), 415);

        softAssert.assertAll();

        System.out.println("Response body: " + responseBody);
    }

                              //PASS (Wrong PATH)

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

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.getStatusCode(), 404);
        softAssert.assertEquals(forbiddenError.getError(), "Not Found");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getPath());
    }

                                 // PASS (Wrong EMAIL)

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
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " +dto);
    }

                        //PASS (EMPTY EMAIL)

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
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " +dto);
    }

                                 //PASS (Wrong Password)

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
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " +dto);
    }

                                   //PASS (EMPTY PASSWORD)

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
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " +dto);
    }


}

