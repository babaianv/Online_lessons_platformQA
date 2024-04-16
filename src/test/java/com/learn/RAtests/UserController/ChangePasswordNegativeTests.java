package com.learn.RAtests.UserController;

import com.learn.RAtests.TestBase;
import com.learn.dto.ChangePasswordDto;
import com.learn.dto.ForbiddenError;
import com.learn.dto.PasswordMismatchError;
import com.learn.dto.UserNotFoundError;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class ChangePasswordNegativeTests extends TestBase {

    ChangePasswordDto passwordDto = new ChangePasswordDto();
    SoftAssert softAssert = new SoftAssert();

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
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertEquals(passwordMismatchError.getMessage(), "Current password is incorrect");

        System.out.println(passwordMismatchError.getMessage());
    }

    @Test(description = "API: Change Password With Empty field New Password Neg Test")
    public void changePassWithEmptyNewPasswordNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("");
        passwordDto.setConfirmNewPassword("Test4test4!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));
    }


    @Test(description = "API: Change Password With Empty field New Password Neg Test")
    public void changePassWithEmptyConfirmPasswordNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("Test4test4!");
        passwordDto.setConfirmNewPassword("");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertEquals(passwordMismatchError.getMessage(), "New password and confirm password mismatch");
    }


    @Test(description = "API: Reg With Short Length 7 Password Neg Test")
    public void changePassWithShortLength7NegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("Test01!");
        passwordDto.setConfirmNewPassword("Test01!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));
    }


    @Test(description = "API: Change Password With Only Numbers Neg Test")
    public void changePasswordWithOnlyNumbersNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("1234567890");
        passwordDto.setConfirmNewPassword("1234567890");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

    }



    @Test(description = "API: Change Password With Only Letters Neg Test")
    public void changePasswordWithOnlyLettersNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("Testtest");
        passwordDto.setConfirmNewPassword("Testtest");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));

        System.out.println(passwordMismatchError.getMessage());
    }



    @Test(description = "API: Change Password With Only Valid Symbols Neg Test")
    public void changePasswordWithOnlyValidSymbolsNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("!$#%!$#%!");
        passwordDto.setConfirmNewPassword("!$#%!$#%!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));
    }


    @Test(description = "API: Change Password With Only Invalid Symbols Neg Test")
    public void changePasswordWithOnlyInvalidSymbolsNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("*@,.)&?-==");
        passwordDto.setConfirmNewPassword("*@,.)&?-==");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));
    }


    @Test(description = "API: Change Password With Only Lowercase Neg Test")
    public void changePasswordWithOnlyLowercaseNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("testtest1!");
        passwordDto.setConfirmNewPassword("testtest1!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));
    }



    @Test(description = "API: Change Password With Only Uppercase Neg Test")
    public void changePasswordWithOnlyUppercaseNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("TESTTEST1!");
        passwordDto.setConfirmNewPassword("TESTTEST1!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));
    }

    @Test(description = "API: Change Password Without Special Symbol Neg Test")
    public void changePasswordWithoutSpecialSymbolNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("Test1test1");
        passwordDto.setConfirmNewPassword("Test1test1");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));
    }



    @Test(description = "API: Change Password Without Numbers Neg Test")
    public void changePasswordWithoutNumbersNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("Testtest!");
        passwordDto.setConfirmNewPassword("Testtest!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        PasswordMismatchError passwordMismatchError = response.as(PasswordMismatchError.class);
        Assert.assertTrue(passwordMismatchError.getMessage().contains("Invalid password format"));
    }


    @Test(description = "API: Change Password without AUTH Test")
    public void changePasswordWithoutAUTHNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("Test4test4!");
        passwordDto.setConfirmNewPassword("Test4test4!");

        Response response = given()
                .contentType("application/json")
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1")
                .then()
                .assertThat().statusCode(403)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(forbiddenError.getError(), "Forbidden");
    }


    @Test(description = "API: Change Password for not exist user Test")
    public void changePasswordForNotExistUserNegTest(){
        passwordDto.setOldPassword("Test1test1!");
        passwordDto.setNewPassword("Test4test4!");
        passwordDto.setConfirmNewPassword("Test4test4!");

        Response response = given()
                .contentType("application/json")
                .auth().oauth2(token)
                .body(passwordDto)
                .when()
                .put("/users/change_password/Test1111")
                .then()
                .assertThat().statusCode(404)
                .extract().response();


        UserNotFoundError userNotFoundError = response.as(UserNotFoundError.class);
        Assert.assertEquals(userNotFoundError.getMessage(),"User with this username not found");
    }


    @Test(description = "API: Change Password with wrong PATH Test")
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
        Assert.assertEquals(forbiddenError.getError(), "Not Found");
    }

}

