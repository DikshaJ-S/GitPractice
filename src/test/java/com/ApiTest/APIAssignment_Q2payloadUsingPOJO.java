package com.ApiTest;

import org.testng.annotations.Test;

import com.POJO.DataPOJO;
import com.POJO.productDetailsPOJO;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;




public class APIAssignment_Q2payloadUsingPOJO {
	Response res;
  @Test
  public void productDetailsUsingPOJO() {
	  	DataPOJO data = new DataPOJO();
	  	data.setYear(1995);
	  	data.setPrice(1849.99);
	  	data.setCPUmodel("Intel Core i9");
	  	data.setHarddisksize("1 TB");
	  	
	  	productDetailsPOJO prodDetails = new productDetailsPOJO();
	  	prodDetails.setName("LaptopConfig");
	  	prodDetails.setData(data);
	  	
	  	Response res =given()
	  	.header("Content-Type", "application/json")
	  	.body(prodDetails)
	  	
	  	.when().post("https://api.restful-api.dev/objects");
	  	res.then()
	  	.statusCode(200)
	  	.body("name",equalTo("LaptopConfig"))
	  	.body("data.year",equalTo(1995))
	  	.body("data.price", equalTo(1849.99F))
	  	.body("data.cpumodel", equalTo("Intel Core i9"))
        .body("data.harddisksize", equalTo("1 TB"))
	    .extract()
	  	.response();
	  	String id= res.jsonPath().getString("id");
	  	System.out.println("Id of the Product created is"+ id);
	  	
	    System.out.println(res.asPrettyString());
	  	
	  
	  
  }


}
