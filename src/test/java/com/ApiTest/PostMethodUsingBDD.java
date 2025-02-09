package com.ApiTest;


import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.POJO.AuthenticatePOJO;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PostMethodUsingBDD {
  @Test
  public void basicPayload() {
	  
	  //https://reqres.in/api/users
	  Response res = given()
	   .header("Content-Type", "application/json")
	  .body("{\n"
	  		+ "    \"username\" : \"admin\",\n"
	  		+ "    \"password\" : \"password123\"\n"
	  		+ "}")
	   .when().post("https://restful-booker.herokuapp.com/auth");
	  
	  //validate status code
	  Assert.assertEquals(res.getStatusCode(), 200);
	  System.out.println("Status Code Matched!!! "+ res.getStatusCode());
	  
	  //validate token
	  String tokenvalue= res.jsonPath().getString("token");
	  System.out.println("Token value is:" +tokenvalue);
	  
	  
	  
	  res.then().log().all();
	  //res.then().log().body();
	  
	  
  }
  
  @Test
  public void postPayloadUsingHashMap() {
	  
	  HashMap<String, Object> data = new HashMap<String, Object>();
	  data.put("username", "admin");
	  data.put("password", "password123");
	  
	  Response res= given()
	   .header("Content-Type", "application/json")
	   	.body(data)
	   	.when().post("https://restful-booker.herokuapp.com/auth");
	  
	  String tokenValue= res.jsonPath().getString("token");
	  System.out.println("Token value is :"+ tokenValue);
	   
  }
  
  @Test
  public void postPayloadUsingPOJO()
  {
	  AuthenticatePOJO pojo = new AuthenticatePOJO();
	  pojo.setUsername("admin");
	  pojo.setPassword("password123");
	  
	  Response res = given()
	  .header("Content-Type", "application/json")
	  .body(pojo)
	  .when().post("https://restful-booker.herokuapp.com/auth");
	  
	  System.out.println("Username is :"+ pojo.getUsername());
	  String tokenValue= res.jsonPath().getString("token");
	  System.out.println("Token value is :"+ tokenValue);
  }
}

