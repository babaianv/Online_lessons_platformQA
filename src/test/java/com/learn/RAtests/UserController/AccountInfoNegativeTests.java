package com.learn.RAtests.UserController;

import com.learn.RAtests.TestBase;
import com.learn.dto.UserDto;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class AccountInfoNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "API: Get User info Positive test")
    public void getAllContactsSuccessTest() {
        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("users/account_info/Test1")
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
    }

}

