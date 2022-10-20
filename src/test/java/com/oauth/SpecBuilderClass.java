package com.oauth;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.pojo.AddPlacePojo;
import com.pojo.LocationPojo;
import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderClass {

	@Test
	
	void tc1() {
		
		AddPlacePojo ap=new AddPlacePojo();
		ap.setAccuracy(50);
		ap.setAddress("6/2,kasthuribai Street pallikonda,vellore");
		ap.setLaunguage("Tamil-IN");
		ap.setPhoneNumber("(+91) 983 893 3937");
		ap.setWebSite("http://google.com");
		ap.setName("My Home");
		
		List<String>ls=new ArrayList<String>();
		ls.add("shoe park");
		ls.add("shop");
		
		ap.setType(ls);
		LocationPojo lp=new LocationPojo();
		lp.setLatitude(-38.383494);
		lp.setLantutid(33.427362);
		
		ap.setLocation(lp);
		
		RequestSpecification sp=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		
		ResponseSpecification req=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		
		
		RequestSpecification rse=given().spec(sp).body(ap);
		
		Response response = rse.when().post("/maps/api/place/add/json").then().spec(req).extract().response();
		
		
		String asString = response.asString();
		
		System.out.println(asString);
	
	}
}
