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
    public static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0MSIsImV4cCI6MTcxMzgyOTk5OSwicm9sZXMiOlt7ImlkIjoxLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sIm5hbWUiOiJUZXN0MSIsImNhcnRJZCI6ODMwfQ.-3vNutbUvDabQaXnSKV01ekc94s9p8EvEI41nzXt_kQ";
    public static final String tokenD = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0ZGVsQSIsImV4cCI6MTcxMjYyNjg4MSwicm9sZXMiOlt7ImlkIjoxLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sIm5hbWUiOiJUZXN0ZGVsQSIsImNhcnRJZCI6MTIwfQ.4UjtQ0o0H99ihpd_VqRwU7GUxh4jcqD2OOuUAmv5RX8";
    public static final String tokenCC = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0MiIsImV4cCI6MTcxMzgyOTYzMiwicm9sZXMiOlt7ImlkIjoxLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sIm5hbWUiOiJUZXN0MiIsImNhcnRJZCI6NjB9.tgY1jGUMsOLC7K4bGE0UdHsRCnW3-Dm8gALF-nUeaVo";



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

