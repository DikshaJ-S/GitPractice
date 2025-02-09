package com.ApiTest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class ApiAssignment_Q1NonBDD {
  @Test(priority=1, enabled=false)
  public void findPETByGetMethod()
  {
	  Response res = RestAssured.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available&name=doggie");
	  System.out.println("Status Code is :"+res.getStatusCode()); 
	  System.out.println("Status StatusLine is :"+res.getStatusLine()); 
	  System.out.println("Content Type  is :"+res.getHeader("Content-Type"));
	  System.out.println("Response Time is :"+res.getTimeIn(TimeUnit.MILLISECONDS)); 
	  System.out.println("Json Response :"+res.asPrettyString());
	  
	  boolean foundDoggie=false;
	  List<Map<String, Object>> allObjects = res.jsonPath().getList("$");
	  for (Map<String, Object> obj : allObjects) 
	  {
          if ("doggie".equals(obj.get("name"))) 
          {
              Assert.assertEquals("doggie", obj.get("name"),"Name not matched!!!");
              System.out.println("name Matched!!!");
              Assert.assertEquals("available", obj.get("status"),"status not matched!!!");
              System.out.println("Status Matched!!!");
              foundDoggie = true;
              break; 
          }
	  }
	  
	  
  }

@Test(priority=2, enabled=true)
public void addNewPetByPostMethod()
{
	Response res = given()
	 .header("accept","application/json")
	 .header("Content-Type","application/json")
	 .body("{\n"
	 		+ "\"id\": 450,\n"
	 		+ "\"category\": {\n"
	 		+ "\"id\": 0,\n"
	 		+ "\"name\": \"string\"\n"
	 		+ "},\n"
	 		+ "\"name\": \"Tommy_SADIQA01\", \"photoUrls\": [\n"
	 		+ "\"string\"\n"
	 		+ "],\n"
	 		+ "\"tags\": [\n"
	 		+ "{\n"
	 		+ "\"id\": 0,\n"
	 		+ "\"name\": \"string\"\n"
	 		+ "}\n"
	 		+ "],\n"
	 		+ "\"status\": \"available\"\n"
	 		+ "}")
	 .when()
	 .post("https://petstore.swagger.io/v2/pet");
	
	

	  int actualCode= res.getStatusCode();
	  Assert.assertEquals(actualCode,200,"Status Code not matched!!!");
	  System.out.println("Status Code matched!!!");
	  System.out.println("New Pet added");
	}

@Test(priority=3, enabled=false)
	public void findThePETUsingGetMethod()
	{
		given()
	    .contentType("application/json")
	    .header("Content-Type","application/json")
	    .when()
	    .get("https://petstore.swagger.io/v2/pet/180")
	    .then()
	    .statusCode(200)
	    .body("id", equalTo(180));

	}

@Test(priority=4, enabled=true)
	public void addPETRecordUsingPUTMethod()
	{
	
		given()
	    .contentType("application/json")
	    .header("Content-Type","application/json")
	    .when()
	    .body("{\n"
	 		+ "\"id\": 180,\n"
	 		+ "\"category\": {\n"
	 		+ "\"id\": 0,\n"
	 		+ "\"name\": \"string\"\n"
	 		+ "},\n"
	 		+ "\"name\": \"Tommy_SADIQA02\", \"photoUrls\": [\n"
	 		+ "\"string\"\n"
	 		+ "],\n"
	 		+ "\"tags\": [\n"
	 		+ "{\n"
	 		+ "\"id\": 0,\n"
	 		+ "\"name\": \"string\"\n"
	 		+ "}\n"
	 		+ "],\n"
	 		+ "\"status\": \"available\"\n"
	 		+ "}")
	    .put("https://petstore.swagger.io/v2/pet")
	    .then()
	    .statusCode(200)
	    .body("name", equalTo("Tommy_SADIQA02"))
	    .body("status", equalTo("available"));
	
	}

	@Test(priority=5, enabled=true)
	public void updatePETusingPatchMethod()
	{
		 JSONObject petJson = new JSONObject();
         petJson.put("id", 185);
         petJson.put("name", "Tommy_SADIQA02_updated");
        
		 given()
		 .header("accept","application/json")
		 .header("Content-Type","application/json")
		 .body(petJson.toString())
     .when()
         .patch("https://petstore.swagger.io/v2/pet/185")
     .then()
        .statusCode(200)
         .body("id", equalTo(185))
         .body("name", equalTo("Tommy_SADIQA02_updated"));
	}
	
	@Test(priority=6, enabled=true)
	public void deletePETUsingdeleteMethod()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		int petIdToDelete=180;
		 
		 given()
		 .pathParam("id",petIdToDelete )
     .when()
         .delete("/pet/{id}")
     .then()
         .statusCode(200)
         .body("message", equalTo("180"));

     System.out.println("Pet deleted successfully!");
		 
	}

}
