package com.learn.RAtests.UserController;

import com.learn.RAtests.TestBase;
import com.learn.dto.ForbiddenError;
import com.learn.dto.TokenResponseDto;
import com.learn.dto.UserDto;
import com.learn.dto.UserNotFoundError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserNegativeTests extends TestBase {

    String accessToken;
    String nickname;
    @BeforeMethod
    public void precondition(){
       UserDto userDto =  given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Testdell")
                        .email("testdell@gmail.com")
                        .password("Testes1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(201)
                .extract().as(UserDto.class);

       nickname = userDto.getNickname();

        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .email("testdell@gmail.com")
                        .password("Testes1!").build())
                .when()
                .post("auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        TokenResponseDto dto = response.as(TokenResponseDto.class);
        accessToken = dto.getAccessToken();
    }


    @Test(description = "API: Delete User Without AUTH Test")
    public void deleteUserWithoutAUTHNegTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .delete("users/delete/Testdell")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        Assert.assertEquals(forbiddenError.getError(), "Forbidden");
    }


    @Test(description = "API: Delete Not Exists User Neg Test")
    public void deleteNotExistsUserNegTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete("users/delete/Tes")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        UserNotFoundError userNotFoundError = response.as(UserNotFoundError.class);
        Assert.assertEquals(userNotFoundError.getMessage(),"User with this username not found");
    }


    @Test(description = "API: Delete User With Invalid Path Neg Test")
    public void deleteUserWithInvalidPathNegTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete("users/del/Testdell")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));
    }

    @AfterMethod
    public void cleanup() {
        given()
                .contentType("application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete("users/delete/" +nickname)
                .then()
                .assertThat().statusCode(200);
    }

}

