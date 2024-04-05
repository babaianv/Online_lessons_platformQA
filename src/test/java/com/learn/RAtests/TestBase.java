package com.learn.RAtests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0MiIsImV4cCI6MTcxMjMxNjk3Nywicm9sZXMiOlt7ImlkIjoxLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sIm5hbWUiOiJUZXN0MiIsImNhcnRJZCI6NjB9.dKFs3-MJ0B5kFOgVbbzeJnYEldQkUAO4if3ttI0ZOn0";
    public static final String tokenD = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUZXN0ZGVsIiwiZXhwIjoxNzEyMzU4MDgzLCJyb2xlcyI6W3siaWQiOjEsImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwibmFtZSI6IlRlc3RkZWwiLCJjYXJ0SWQiOjk0fQ.rJxkh3x1YWDvvseb0PuHUBrlNFN9NcXNyMwOU9OIZUQ";

    @BeforeMethod
    public void init(){
        RestAssured.baseURI = "http://localhost:8069";
        RestAssured.basePath = "api";
    }
}

