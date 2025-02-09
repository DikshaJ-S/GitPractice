package com.ApiTest;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FirstNonBDD {
  @Test
  public void testSingleUserGetReq() {
	  Response res=RestAssured.get("https://reqres.in/api/users/2");
	  System.out.println("Status Code is :"+res.getStatusCode()); 
	  System.out.println("Status StatusLine is :"+res.getStatusLine()); 
	  System.out.println("Content Type  is :"+res.getHeader("Content-Type"));
	  System.out.println("Response Time is :"+res.getTimeIn(TimeUnit.MILLISECONDS)); 
	  System.out.println("Raw Response :"+res.asString()); 
	  System.out.println("Json Response :"+res.asPrettyString()); 
	  
	  //validating status code
	  int actualCode= res.getStatusCode();
	  Assert.assertEquals(actualCode,200,"Status Code not matched!!!");
	  System.out.println("Status Code matched!!!");
		 
	  
	  
  }
}
