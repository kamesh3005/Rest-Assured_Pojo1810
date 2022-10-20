package com.oauth;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.pojo.AddPlacePojo;
import com.pojo.LocationPojo;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Serilization {
	
	@org.testng.annotations.Test
	
	void tc1() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
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
		
		Response responce=given().log().all().queryParam("key", "qaclick123").body(ap)
		.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();
		
		
		String asString = responce.toString();
		
		System.out.println(asString);
	}
	
	

}
