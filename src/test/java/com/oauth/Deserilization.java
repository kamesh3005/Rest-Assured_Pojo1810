package com.oauth;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.AddPlacePojo;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Deserilization {

	@Test

	void tc1() throws JsonMappingException, JsonProcessingException {

		String jsonResponse="{\r\n"
				+ "\"location\": {\r\n"
				+ "\"lat\": -38.383494,\r\n"
				+ "\"lng\": 33.427362\r\n"
				+ "},\r\n"
				+ "\"accuracy\": 50,\r\n"
				+ "\"name\": \"Frontline house\",\r\n"
				+ "\"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "\"address\": \"6/2,kasthuribai Street pallikonda,vellore\",\r\n"
				+ "\"types\": [\r\n"
				+ "\"shoe park\",\r\n"
				+ "\"shop\"\r\n"
				+ "],\r\n"
				+ "\"website\": \"http://google.com\",\r\n"
				+ "\"language\": \"French-IN\"\r\n"
				+ "}";
		
		ObjectMapper obj=new ObjectMapper();
		
		AddPlacePojo addpojo = obj.readValue(jsonResponse, AddPlacePojo.class);
		System.out.println(addpojo.getAddress());
		System.out.println(addpojo.getLaunguage());
		System.out.println(addpojo.getWebSite());
		
				
		
		

	}

}
 