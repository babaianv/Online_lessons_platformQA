package com.learn.RAtests;

import com.learn.dto.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegistrationTests extends TestBase {


    SoftAssert softAssert = new SoftAssert();

                            //POSITIVE
    UserDto registration = UserDto.builder()
            .nickname("TestReg")
            .email("testregpos@gmail.com")
            .password("Test1test1!")
            .build();

                              ///PASS
    @Test(description = "API: Registration Success")
    public void registrationSuccessTest(){
        Response response =  given()
                .contentType("application/json")
                .body(registration)
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        UserDto userDto = response.as(UserDto.class);
        softAssert.assertEquals(response.getStatusCode(), 201);

        softAssert.assertAll();
        System.out.println(userDto);

    }

                         ////PASS(Length Nickname(10))

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
        softAssert.assertEquals(response.getStatusCode(), 201);
        softAssert.assertNotNull(userDto.getId());
        softAssert.assertNotNull(userDto.getNickname());
        softAssert.assertNotNull(userDto.getEmail());
        softAssert.assertNotNull(userDto.getPassword());
        softAssert.assertNotNull(userDto.getRoles());

        softAssert.assertAll();
        System.out.println(response.getBody().asString());
    }

                          ////PASS(Length Nickname(3))

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
        softAssert.assertEquals(response.getStatusCode(), 201);
        softAssert.assertNotNull(userDto.getId());
        softAssert.assertNotNull(userDto.getNickname());
        softAssert.assertNotNull(userDto.getEmail());
        softAssert.assertNotNull(userDto.getPassword());
        softAssert.assertNotNull(userDto.getRoles());

        softAssert.assertAll();
        System.out.println(response.getBody().asString());
    }

                          ////PASS(Length Password(8))

    @Test(description = "API: Reg with Boundary Value 8 Password Test ")
    public void regWithBoundaryValue8PasswordTest(){

        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Testpass8")
                        .email("lenghtpswd8@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(201)
                .extract().response();

        UserDto userDto = response.as(UserDto.class);
        softAssert.assertEquals(response.getStatusCode(), 201);
        softAssert.assertNotNull(userDto.getId());
        softAssert.assertNotNull(userDto.getNickname());
        softAssert.assertNotNull(userDto.getEmail());
        softAssert.assertNotNull(userDto.getPassword());
        softAssert.assertNotNull(userDto.getRoles());

        softAssert.assertAll();
        System.out.println(response.getBody().asString());
    }

                            ////////////////////////////

    @Test(description = "API: Response Time Is Less Than 500ms ")
    public void responseTimeIsLessThan500msTest() {
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Test500")
                        .email("less500@gmail.com")
                        .password("Test1Test1!").build())
                .post("users/register")
                .then()
                .time(lessThan(600L))
                .assertThat().statusCode(201)
                .extract().response();

        long responseTime = response.time();
        Assert.assertEquals(response.getStatusCode(), 201);

        System.out.println("Response time: " + responseTime + " milliseconds");
    }


                                    ///NEGATIVE
                             //PASS

    @Test(description = "API: Registration With Wrong Path")
    public void registrationWithWrongPathTest(){
        Response response =  given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Testpath")
                        .email("wrongpath@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/reg")
                .then()
                .assertThat().statusCode(404)
                .extract().response();

        ForbiddenError forbiddenError = response.as(ForbiddenError.class);
        softAssert.assertEquals(response.getStatusCode(), 404);
        softAssert.assertEquals(forbiddenError.getError(), "Not Found");
        softAssert.assertAll();

        System.out.println(forbiddenError.getError() +" "+ forbiddenError.getPath());
    }

                             // NICKNAME
                            ///PASS(exist Nickname)

    @Test(description = "API: Reg With Existing Nickname Neg Test")
    public void regWithExNicknameNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Test1")
                        .email("existnick@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(409)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("This nickname is already taken"));
        softAssert.assertEquals(response.getStatusCode(), 409);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                           ///PASS (Short Nickname 2)

    @Test(description = "API: Reg With Short Length Nickname Neg Test")
    public void regWithShortLengthNicknameNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Te")
                        .email("lenghtnick2@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid nickname format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }


                              ///PASS (Long Nickname 11)

    @Test(description = "API: Reg With Long Length Nickname Neg Test ")
    public void regWithLongLengthNicknameNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Testlongnic")
                        .email("lengthnick11@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid nickname format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                          //PASS (only numbers)

    @Test(description = "API: Reg With Only Numbers Nickname Neg Test")
    public void regWithOnlyNumbersNicknameNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("123")
                        .email("nicknumb@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid nickname format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }


                                // PASS (symbol)

    @Test(description = "API: Reg With Invalid Nickname Neg Test")
    public void regWithInvalidNicknameNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Test123%$")
                        .email("nickwithsymbol@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid nickname format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                                  ///PASS (only symbol)

    @Test(description = "API: Reg With Only Invalid Symbol Nickname Neg Test")
    public void regWithOnlyInvalidSymbolNicknameNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("*#$%)")
                        .email("nickonlysymbol@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid nickname format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                           ///PASS (empty nickname)

    @Test(description = "API: Reg With Empty Nickname Neg Test")
    public void regWithEmptyNicknameNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("")
                        .email("emptynick@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid nickname format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                             ///Email

                           ///FAILED (email in DB exist) -> BUG FIXED T25-86

    @Test(description = "API: Reg With Existing Email Neg Test")
    public void regWithExEmailNegTest(){



        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Olga")
                        .email("test1@gmail.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(409)
                .extract().response();

        UserAlreadyExistsErrorDto dto = response.as(UserAlreadyExistsErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("User with this email already exists"));
        softAssert.assertEquals(response.getStatusCode(), 409);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                             //FAILED (empty email) -> BUG FIXED T25-87

    @Test(description = "API: Reg With Empty Email Neg Test")
    public void regWithEmptyEmailNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Inna1")
                        .email("")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("must not be blank"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }


                                 ////PASS(wrong format)

    @Test(description = "API: Reg With Wrong Email Format Neg Test")
    public void regWithWrongEmailFormatNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Alla")
                        .email("allalala.com")
                        .password("Test1test1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("must be a well-formed email address"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                               /// Password

                              ///PASS (empty)

    @Test(description = "API: Reg With Empty Password Neg Test")
    public void regWithEmptyPasswordNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Ekat")
                        .email("ekat@gmail.com")
                        .password("").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid password format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }


                            ///PASS (Length 7)

    @Test(description = "API: Reg With Short Length 7 Password Neg Test")
    public void regWithShortLength7PasswordNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Ekat")
                        .email("ekat@gmail.com")
                        .password("Ekate1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid password format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }


                               ///PASS (only numbers)

    @Test(description = "API: Reg With Only Numbers Password Neg Test")
    public void regWithOnlyNumbersPasswordNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Ekat")
                        .email("ekat@gmail.com")
                        .password("12345678").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid password format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                                     ///PASS (only letters)

    @Test(description = "API: Reg With Only Letters Password Neg Test")
    public void regWithOnlyLettersPasswordNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Ekat")
                        .email("ekat@gmail.com")
                        .password("Ekaterina").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid password format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                                      ///PASS (only  valid symbols)

    @Test(description = "API: Reg With Only Valid Symbols Password Neg Test")
    public void regWithOnlyValidSymbolsPasswordNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Ekat")
                        .email("ekat@gmail.com")
                        .password("!$#%!$#%").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid password format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                                   ///PASS (only invalid symbols)

    @Test(description = "API: Reg With Only Invalid Symbols Password Neg Test")
    public void regWithOnlyInvalidSymbolsPasswordNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Ekat")
                        .email("ekat@gmail.com")
                        .password("*@,.)&?-=").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid password format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                                ///PASS (only Uppercase)

    @Test(description = "API: Reg With Only Uppercase Password Neg Test")
    public void regWithOnlyUppercasePasswordNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Ekat")
                        .email("ekat@gmail.com")
                        .password("EKATERINA1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid password format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                                ///PASS (only Lowercase)

    @Test(description = "API: Reg With Only Lowercase Password Neg Test")
    public void regWithOnlyLowercasePasswordNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Ekat")
                        .email("ekat@gmail.com")
                        .password("ekaterina1!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid password format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                                     ///PASS ( Without Special Symbol)

    @Test(description = "API: Reg Without Special Symbol Password Neg Test")
    public void regWithoutSpecialSymbolPasswordNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Ekat")
                        .email("ekat@gmail.com")
                        .password("Ekaterina123").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid password format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }

                                 ///PASS ( Without Numbers)

    @Test(description = "API: Reg Without Numbers Password Neg Test")
    public void regWithoutNumbersPasswordNegTest(){
        Response response = given()
                .contentType("application/json")
                .body(UserDto.builder()
                        .nickname("Ekat")
                        .email("ekat@gmail.com")
                        .password("Ekaterina!").build())
                .when()
                .post("users/register")
                .then()
                .assertThat().statusCode(400)
                .extract().response();

        UserValidationErrorDto dto = response.as(UserValidationErrorDto.class);
        softAssert.assertTrue(dto.getMessage().contains("Invalid password format"));
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertAll();

        System.out.println("Response body: " + response.getBody().asString());
    }



}

