package com.learn.RAtests.UserController;

import com.learn.RAtests.TestBase;
import com.learn.dto.ForbiddenError;
import com.learn.dto.UserDto;
import com.learn.dto.UserNotFoundError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class AccountInfoTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

                            ///POSITIVE

                        //FAILED -> BUG FIXED T25-89

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
        System.out.println(userDto);
    }

                                  //NEGATIVE
                                //PASS (Without AUTH)

    @Test(description = "API: Get User Info Without AUTH Test")
    public void getUserInfoWithoutAUTHTest() {
        Response response = given()
                .contentType("application/json")
                .get("users/account_info/Test1")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertEquals(forbiddenError.getPath(), "/api/users/account_info/Test1");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

                            //PASS (Wrong Path)

    @Test(description = "API: Get Users Info With Wrong Path")
    public void getUsersInfoWithWrongPath(){

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .put("users/info/Test1")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("Not Found"));

        System.out.println(responseBody);
    }

                         /// PASS (User info get for not exists user)

    @Test(description = "API: Get user info for not exist user Test")
    public void getUserInfoForNotExistUserNegTest(){

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .when()
                .get("users/account_info/Test23")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        UserNotFoundError userNotFoundError = response.as(UserNotFoundError.class);
        Assert.assertEquals(userNotFoundError.getMessage(),"User with this username not found");

        System.out.println(userNotFoundError.getMessage());
    }


}

