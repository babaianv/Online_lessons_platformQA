package com.learn.RAtests;

import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);
    public static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0MSIsImV4cCI6MTcxMjYxOTkwNSwicm9sZXMiOlt7ImlkIjoxLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sIm5hbWUiOiJUZXN0MSIsImNhcnRJZCI6Mn0.12Z5TMquz4I0T1j02A6ENG-MdfzdjR9ku53Dc08UQLY";
    public static final String tokenD = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0ZGVsQSIsImV4cCI6MTcxMjYyNjg4MSwicm9sZXMiOlt7ImlkIjoxLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sIm5hbWUiOiJUZXN0ZGVsQSIsImNhcnRJZCI6MTIwfQ.4UjtQ0o0H99ihpd_VqRwU7GUxh4jcqD2OOuUAmv5RX8";
    public static final String tokenCC = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0Y2MiLCJleHAiOjE3MTI2OTUyNjQsInJvbGVzIjpbeyJpZCI6MSwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJuYW1lIjoiVGVzdGNjIiwiY2FydElkIjoxMjJ9.4Bqk4ZJ5Wb_L2MdObxxz-wGQXUI_s8p6jQlckFugQIA";

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
}

