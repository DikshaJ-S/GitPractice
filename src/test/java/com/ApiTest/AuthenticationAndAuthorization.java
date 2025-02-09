package com.ApiTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class AuthenticationAndAuthorization {
  @Test
  public void BasicAuthentication() {
	  System.out.println("Basic Authentication Validation");
	  
	        Response res = given()
	        .auth().basic("postman", "password")
	        .when().get("https://www.postman-echo.com/basic-auth");
	        
	        Boolean status = res.jsonPath().getBoolean("authenticated");
	        
	        Assert.assertEquals(status, true);
	        
  }
  
  @Test
  public void DigestAuthentication()
  {
	  System.out.println("Digest Authentication Validation");
	  Response res = given()
		        .auth().digest("postman", "password")
		        .when().get("https://www.postman-echo.com/digest-auth");
		        
		        Boolean status = res.jsonPath().getBoolean("authenticated");
		        
		        Assert.assertEquals(status, true);
  }
  
  @Test
  public void BearerAuthentication()
  {
	  System.out.println("Baerer Authentication Validation");
	             given()
		        .header("Authorization","SA10010200")
		        .when().get("https://www.postman-echo.com")
		        .then().log().cookies();      
  }
  
  @Test
  public void OAuth2Authentication()
  {
	  System.out.println("OAth2.0 Authentication Validation");
	  Response  res= given()
	  .auth().oauth2("SA10010200")
	  
	  .when().get("https://www.postman-echo.com");
	  res.then().log().body();
  }
}
