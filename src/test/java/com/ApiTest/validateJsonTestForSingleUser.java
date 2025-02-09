package com.ApiTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class validateJsonTestForSingleUser {
  @Test
  public void validateSingleUserJsonAttributes() {
	  Response res=RestAssured.get("https://reqres.in/api/users/2");
	  
	  //validate id
	  int id=res.jsonPath().getInt("data.id");
	  Assert.assertEquals(id, 2, "Json path id is not matched!!!!");
	  System.out.println("Json path id is matched");
	  
	//validate firstname
	  String firstname=res.jsonPath().getString("data.first_name");
	  Assert.assertEquals(firstname, "Janet", "Json path firstname is not matched!!!!");
	  System.out.println("Json path firstname is matched");
	  
	  //validate support.text
	  String text=res.jsonPath().getString("support.text");
	  Assert.assertTrue(text.contains("social media"));
	  System.out.println("Text validated");
	  System.out.println("Test Passed: "+text);
  }
}
