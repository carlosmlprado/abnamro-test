package com.assignment.abnamro.controller;


import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;

public class Testt {

    public static final String BASE_URL = "http://localhost:8080/api";

    @BeforeClass
    public static void setup() {

        RestAssured.basePath = BASE_URL;
        RestAssured.baseURI = "http://localhost";
    }
}
