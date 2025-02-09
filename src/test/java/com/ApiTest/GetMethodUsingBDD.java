package com.ApiTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GetMethodUsingBDD {
  @Test
  public void listOfUsersGET() {
	  Response res =given()
	   .when().get("https://reqres.in/api/users?page=2");
	   
	   //validate status code
	   Assert.assertEquals(res.getStatusCode(), 200);
	   System.out.println("Status code matched: "+res.getStatusCode());
	   
	   //first_name": "George"
	   String firstname= res.jsonPath().getString("data[4].first_name");
	   Assert.assertEquals(firstname, "George");
	   System.out.println("First Name matched!!!");
	   
	   //get all ids and print it in console
	   
	   List <Integer> allIds=res.jsonPath().getList("data.id");
	   Assert.assertEquals(allIds.size(), 6);
	   System.out.println("Total Ids are:"+ allIds.size());
	   
	   for(Integer id:allIds)
	   {
		   System.out.println(id);
	   }
	   
	   
	   
  }
}
