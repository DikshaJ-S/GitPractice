package com.ApiTest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Auth2BDD {
    private static final String ACCESS_TOKEN = "dummy-oauth-token-12345";
  @Test
  public void verifyAuth2UsingBDD() {

	          //RestAssured.baseURI = BASE_URI;

	          // Access the protected resource with OAuth token
	          given()
	          .auth().oauth2(ACCESS_TOKEN)
	          .when()
              .get("https://postman-echo.com")
              .then()
              .statusCode(200)
              
              .log().body();
	          
	          
//	          given()
//	                  .header("Authorization", "Bearer " + ACCESS_TOKEN)
//	                  .when()
//	                  .get("/get?test=OAuth2Success")
//	                  .then()
//	                  .statusCode(200)
//	                  .body("args.test", equalTo("OAuth2Success"))
//	                  .log().body();
	      }
	  
  }

