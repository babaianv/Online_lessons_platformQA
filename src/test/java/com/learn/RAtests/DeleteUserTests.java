package com.learn.RAtests;

import com.learn.dto.ForbiddenError;
import com.learn.dto.UserDto;
import com.learn.dto.UserNotFoundError;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class DeleteUserTests extends TestBase{

    SoftAssert softAssert = new SoftAssert();
    private String accessToken;

//    @BeforeMethod
//    public void precondition(){
//        given()
//                .contentType("application/json")
//                .body(UserDto.builder()
//                        .nickname("TestDel")
//                        .email("testdel@gmail.com")
//                        .password("TestDelPass1!")
//                        .build())
//                .when()
//                .post("users/register")
//                .then()
//                .assertThat().statusCode(201)
//                .extract().as(UserDto.class);
//
//        Response loginResponse = given()
//                .contentType("application/json")
//                .body(UserDto.builder()
//                        .email("testdel@gmail.com")
//                        .password("TestDelPass1!")
//                        .build())
//                .when()
//                .post("auth/login")
//                .then()
//                .assertThat().statusCode(200)
//                .extract().response();
//         accessToken = loginResponse.jsonPath().getString("accessToken");
//
//    }
//
//                             ///POSITIVE
//
//    @Test(description = "API: Delete user Positive test")
//    public void deleteUserSuccessTest() {
//
//        Response deleteResponse = given()
//                .contentType("application/json")
//                .auth().oauth2(accessToken)
//                .when()
//                .delete("users/delete/TestDel")
//                .then()
//                .assertThat().statusCode(200)
//                .extract().response();
//
//        softAssert.assertEquals(deleteResponse.getStatusCode(), 200);
//        softAssert.assertEquals(deleteResponse.header("content-length"), "0");
//
//        softAssert.assertAll();
//    }


    @Test(description = "API: Delete user Positive test")
    public void deleteUserSuccessTest() {

       given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("TestDel")
                        .email("testdel@gmail.com")
                        .password("TestDelPass1!")
                        .build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(201)
                .extract().as(UserDto.class);

        Response loginResponse = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .email("testdel@gmail.com")
                        .password("TestDelPass1!")
                        .build())
                .when()
                .post("auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
        accessToken = loginResponse.jsonPath().getString("accessToken");

        Response deleteResponse = given()
                .contentType("application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete("users/delete/TestDel")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        softAssert.assertEquals(deleteResponse.getStatusCode(), 200);
        softAssert.assertEquals(deleteResponse.header("content-length"), "0");

        softAssert.assertAll();
    }
                                       ///NEGATIVE

                                  ///FAILED -> Bug T25-92 ???????

    @Test(description = "API: Delete User Without AUTH Test")
    public void deleteUserWithoutAUTHNegTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .delete("users/delete/TestDel")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.getStatusCode(), 403);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

                                //PASS (not exist user)

    @Test(description = "API: Delete Not Exists User Neg Test")
    public void deleteNotExistsUserNegTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(tokenD)
                .when()
                .delete("users/delete/Tes")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        UserNotFoundError userNotFoundError = response.as(UserNotFoundError.class);
        softAssert.assertEquals(response.getStatusCode(), 404);
        softAssert.assertEquals(userNotFoundError.getMessage(),"User with this username not found");
        softAssert.assertAll();

        System.out.println(userNotFoundError.getMessage());

        softAssert.assertAll();
    }

                                /// ????????????????????????
    @Test(description = "API: Delete User With Invalid Path Neg Test")
    public void deleteUserWithInvalidPathNegTest() {
        Response response = given()
                .contentType("application/json")
                .when()
                .delete("users/del/TestDel")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.getStatusCode(), 404);
        softAssert.assertEquals(forbiddenError.getError(), "Not Found");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

}

