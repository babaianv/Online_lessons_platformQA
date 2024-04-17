package com.learn.RAtests.UserController;

import com.learn.RAtests.TestBase;
import com.learn.dto.TokenResponseDto;
import com.learn.dto.UserDto;
import com.learn.dto.UserLoginDto;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class RegistrationPositiveTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();
    UserLoginDto userLoginDto;
    int id;
    String nickname;
    String email;
    String password;

    @Test(description = "API: Registration Success")
    public void registrationSuccessTest(){

        Response response =  given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("TestReg")
                        .email("testregpos@gmail.com")
                        .password("Test1test1!")
                        .build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        UserDto userDto = response.as(UserDto.class);
        softAssert.assertEquals(response.contentType(), "application/json");
        softAssert.assertNotNull(userDto.getId());
        softAssert.assertNotNull(userDto.getNickname());
        softAssert.assertNotNull(userDto.getEmail());
        softAssert.assertNotNull(userDto.getPassword());
        softAssert.assertNotNull(userDto.getRoles());
        softAssert.assertAll();

        UserLoginDto userLoginDto = response.as(UserLoginDto.class);
        id = userDto.getId();
        nickname = userDto.getNickname();
        email = userLoginDto.getEmail();
        password =  "Test1test1!";
    }


    @Test(description = "API: Reg with Boundary Value 10 Nickname")
    public void regWithBoundaryValue10NicknameTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Testleng10")
                        .email("lenghtnick15@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        UserDto userDto = response.as(UserDto.class);
        softAssert.assertNotNull(userDto.getId());
        softAssert.assertNotNull(userDto.getNickname());
        softAssert.assertNotNull(userDto.getEmail());
        softAssert.assertNotNull(userDto.getPassword());
        softAssert.assertNotNull(userDto.getRoles());
        softAssert.assertAll();

        UserLoginDto userLoginDto = response.as(UserLoginDto.class);
        id = userDto.getId();
        nickname = userDto.getNickname();
        email = userLoginDto.getEmail();
        password =  "Test1test1!";
    }


    @Test(description = "API: Reg with Boundary Value 3 Nickname")
    public void regWithBoundaryValue3NicknameTest(){

        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Testleng3")
                        .email("lenghtnick3@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        UserDto userDto = response.as(UserDto.class);
        softAssert.assertNotNull(userDto.getId());
        softAssert.assertNotNull(userDto.getNickname());
        softAssert.assertNotNull(userDto.getEmail());
        softAssert.assertNotNull(userDto.getPassword());
        softAssert.assertNotNull(userDto.getRoles());
        softAssert.assertAll();

        UserLoginDto userLoginDto = response.as(UserLoginDto.class);
        id = userDto.getId();
        nickname = userDto.getNickname();
        email = userLoginDto.getEmail();
        password =  "Test1test1!";
    }


    @Test(description = "API: Reg with Boundary Value 8 Password Test ")
    public void regWithBoundaryValue8PasswordTest(){

        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Testpass8")
                        .email("lenghtpswd8@gmail.com")
                        .password("Testes1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        UserDto userDto = response.as(UserDto.class);
        softAssert.assertNotNull(userDto.getId());
        softAssert.assertNotNull(userDto.getNickname());
        softAssert.assertNotNull(userDto.getEmail());
        softAssert.assertNotNull(userDto.getPassword());
        softAssert.assertNotNull(userDto.getRoles());
        softAssert.assertAll();

        UserLoginDto userLoginDto = response.as(UserLoginDto.class);
        email = userLoginDto.getEmail();
        password =  "Testes1!";
        id = userDto.getId();
        nickname = userDto.getNickname();
    }

    @AfterMethod
    public void cleanup() {

        Response response = given()
                .contentType("application/json")
                .body(UserLoginDto.builder()
                        .email(email)
                        .password(password)
                        .build())
                .when()
                .post("auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        TokenResponseDto dto = response.as(TokenResponseDto.class);
        String accessToken = dto.getAccessToken();
        given()
                .contentType("application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete("users/delete/"+nickname);
    }

}

