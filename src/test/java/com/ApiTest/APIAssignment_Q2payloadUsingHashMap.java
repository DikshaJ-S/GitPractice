package com.ApiTest;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

public class APIAssignment_Q2payloadUsingHashMap {
  @Test
  public void postingProductDetailsUsingHashMap() {
	  
	  HashMap<String, Object> data = new HashMap<String, Object>();
	  data.put("year", 2001);
	  data.put("price", 1786.55);
	  data.put("CPUmodel", "Intel Core i9");
	  data.put("Harddisksize", "1 TB");
	  
	  HashMap<String, Object> prodDetails = new HashMap<String, Object>();
	  prodDetails.put("name", "LapConfiguration");
	  prodDetails.put("data", data);
	  
	  Response res =given()
			  	.header("Content-Type", "application/json")
			  	.body(prodDetails)
			  	
			  	.when().post("https://api.restful-api.dev/objects");
			  	res.then()
			  	.statusCode(200)
	  	.body("name",equalTo("LapConfiguration"))
	  	.body("data.year",equalTo(2001))
	  	.body("data.price", equalTo(1786.55F))
	  	.body("data.CPUmodel", equalTo("Intel Core i9"))
	  	.body("data.Harddisksize", equalTo("1 TB"))
	    .extract()
	  	.response();
	  	String id= res.jsonPath().getString("id");
	  	System.out.println("Id of the Product created is"+ id);
	  	System.out.println(res.asPrettyString());
	  
	  
			  
			  
	  
	  
	  
  }
}
