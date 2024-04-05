package com.learn.RAtests;

import com.learn.dto.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class ChangePasswordTests extends TestBase{

    ChangePasswordDto passwordDto = new ChangePasswordDto();
    SoftAssert softAssert = new SoftAssert();

                              //POSITIVE

                                //PASS

    @Test(description = "API: Change Password Positive Test")
    public void changePasswordPositiveTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("Test2test2!");
        passwordDto.setConfirmNewPassword("Test2test2!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertAll();

    }

                            ////PASS(Length Password(8))

    @Test(description = "API: Change password with Boundary Value 8 Password Test ")
    public void changePasswordBoundaryValue8Test(){
        passwordDto.setOldPassword("Test2test2!");
        passwordDto.setNewPassword("Test3test3!");
        passwordDto.setConfirmNewPassword("Test3test3!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertAll();
    }

                         ///NEGATIVE

                        ///PASS (empty field old password)

    @Test(description = "API: Change Password With Empty field Old Password Neg Test")
    public void changePassWithEmptyOldPasswordNegTest(){
        passwordDto.setOldPassword("");
        passwordDto.setNewPassword("Test4test4!");
        passwordDto.setConfirmNewPassword("Test4test4!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();


        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertEquals(passwordMismatchError.getMessage(), "Current password is incorrect");
        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }

                               ///PASS (empty field new password)

    @Test(description = "API: Change Password With Empty field New Password Neg Test")
    public void changePassWithEmptyNewPasswordNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("");
        passwordDto.setConfirmNewPassword("Test4test4!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();


        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }

                             ///PASS (empty field confirm password)

    @Test(description = "API: Change Password With Empty field New Password Neg Test")
    public void changePassWithEmptyConfirmPasswordNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("Test4test4!");
        passwordDto.setConfirmNewPassword("");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();


        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertEquals(passwordMismatchError.getMessage(), "New password and confirm password mismatch");
        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }

                                       ///PASS (Length 7)

    @Test(description = "API: Reg With Short Length 7 Password Neg Test")
    public void changePassWithShortLength7NegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("Test01!");
        passwordDto.setConfirmNewPassword("Test01!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }

                                 ///PASS (Only numbers)

    @Test(description = "API: Change Password With Only Numbers Neg Test")
    public void changePasswordWithOnlyNumbersNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("1234567890");
        passwordDto.setConfirmNewPassword("1234567890");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }

                                     ///PASS (Only letters)

    @Test(description = "API: Change Password With Only Letters Neg Test")
    public void changePasswordWithOnlyLettersNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("Testtest");
        passwordDto.setConfirmNewPassword("Testtest");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }

                                      ///PASS (Only valid symbols)

    @Test(description = "API: Change Password With Only Valid Symbols Neg Test")
    public void changePasswordWithOnlyValidSymbolsNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("!$#%!$#%!");
        passwordDto.setConfirmNewPassword("!$#%!$#%!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }

                                 ///PASS (Only invalid symbols)

    @Test(description = "API: Change Password With Only Invalid Symbols Neg Test")
    public void changePasswordWithOnlyInvalidSymbolsNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("*@,.)&?-==");
        passwordDto.setConfirmNewPassword("*@,.)&?-==");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }


                                    ///PASS (only Lowercase)

    @Test(description = "API: Change Password With Only Lowercase Neg Test")
    public void changePasswordWithOnlyLowercaseNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("testtest1!");
        passwordDto.setConfirmNewPassword("testtest1!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }

                                      ///PASS (only Uppercase)

    @Test(description = "API: Change Password With Only Uppercase Neg Test")
    public void changePasswordWithOnlyUppercaseNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("TESTTEST1!");
        passwordDto.setConfirmNewPassword("TESTTEST1!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }


                                ///PASS (Without Special Symbol)

    @Test(description = "API: Change Password Without Special Symbol Neg Test")
    public void changePasswordWithoutSpecialSymbolNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("Test1test1");
        passwordDto.setConfirmNewPassword("Test1test1");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }

                               ///PASS (Without Numbers)

    @Test(description = "API: Change Password Without Numbers Neg Test")
    public void changePasswordWithoutNumbersNegTest(){
        passwordDto.setOldPassword("Testtest!");
        passwordDto.setNewPassword("Testtest!");
        passwordDto.setConfirmNewPassword("Test1test!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        softAssert.assertAll();

        System.out.println(passwordMismatchError.getMessage());
    }

                                ///PASS (without AUTH)

    @Test(description = "API: Change Password without AUTH Test")
    public void changePasswordWithoutAUTHNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("Test4test4!");
        passwordDto.setConfirmNewPassword("Test4test4!");

        Response response = given()
                .contentType("application/json")
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test2")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.getStatusCode(), 403);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getStatus());
    }

                                  ///PASS (for not exist user)

    @Test(description = "API: Change Password for not exist user Test")
    public void changePasswordForNotExistUserNegTest(){
        passwordDto.setOldPassword("Test3test3!");
        passwordDto.setNewPassword("Test4test4!");
        passwordDto.setConfirmNewPassword("Test4test4!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test11")
                .then()
                .assertThat().statusCode(404)
                .extract().response();


        UserNotFoundError userNotFoundError = response.as(UserNotFoundError.class);
        softAssert.assertEquals(response.getStatusCode(), 404);
        softAssert.assertEquals(userNotFoundError.getMessage(),"User with this username not found");
        softAssert.assertAll();

        System.out.println(userNotFoundError.getMessage());
    }

                     //PASS (Wrong PATH)

    @Test(description = "API: Change Password with wrong PATHTest")
    public void changePasswordWrongPathNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("Test4test4!");
        passwordDto.setConfirmNewPassword("Test4test4!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_pass/Test1")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.getStatusCode(), 404);
        softAssert.assertEquals(forbiddenError.getError(), "Not Found");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getPath());

    }

}

