package com.learn.RAtests.UserController;

import com.learn.RAtests.TestBase;
import com.learn.dto.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class ChangePasswordPositiveTests extends TestBase {

    ChangePasswordDto passwordDto = new ChangePasswordDto();

    @Test(description = "API: Change password with Boundary Value 8 Password Test", priority = 1)
    public void changePasswordBoundaryValue8Test(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("Test123!");
        passwordDto.setConfirmNewPassword("Test123!");

        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    @Test(description = "API: Change Password Positive Test", priority = 2)
    public void changePasswordPositiveTest(){
        passwordDto.setOldPassword("Test123!");
        passwordDto.setNewPassword("Test1test1!");
        passwordDto.setConfirmNewPassword("Test1test1!");

        given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }


}

