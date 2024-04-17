package com.learn.RAtests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    public static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0MSIsImV4cCI6MTcxMzkxMjkzMiwicm9sZXMiOlt7ImlkIjoxLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sIm5hbWUiOiJUZXN0MSIsImNhcnRJZCI6MX0.MW1af56xcDAU3FX4fPw2j42mqqNt7or7_5adLfwqkQo";

    @BeforeMethod
    public void init(){
        RestAssured.baseURI = "http://localhost:8069";
        RestAssured.basePath = "api";
    }

    @BeforeMethod
    public void startTest(Method method){
        logger.info("Start test: " + method.getName());
    }

    @AfterMethod
    public void stopTest(ITestResult result){
        if (result.isSuccess()){
            logger.info("PASSED: "+ result.getMethod().getMethodName());
        }else {
            logger.error("FAILED: " +result.getMethod().getMethodName());
        }
        logger.info("Stop test");
        logger.info("*******************************");
    }

//    public static String loginAndGetToken(String email, String password) {
//        Response response = given()
//                .contentType("application/json")
//                .body("{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }")
//                .when()
//                .post("auth/login")
//                .then()
//                .extract().response();
//
//        return response.getBody().jsonPath().getString("accessToken");
//    }
}

