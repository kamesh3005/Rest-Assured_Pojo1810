package com.oauth;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.pojo.ApiPojo;
import com.pojo.GetCourseDetials;
import com.pojo.WebAutomationPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class Oauth20 {

	public static void main(String[] args) throws InterruptedException {
		
		String[] courseTitles = { "Selenium Webdriver Java", "Cypress", "Protractor" };
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\kamesh p\\eclipse-workspace\\Assignment_RestAssured1810\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(
				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("kameshkam3094@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Kamesh@30");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		String url = driver.getCurrentUrl();
		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		System.out.println(code);

		String accessTokenResponse = given().queryParams("code", code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");

		GetCourseDetials gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourseDetials.class);

		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCoursepojo().getApiPojo().get(1).getCourseTitle());

		java.util.List<ApiPojo> apiPojo = gc.getCoursepojo().getApiPojo();
		for (int i = 0; i < apiPojo.size(); i++) {
			if (apiPojo.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiPojo.get(i).getPrices());
			}
		}

		ArrayList<String> a = new ArrayList<String>();

		java.util.List<WebAutomationPojo> webAutomation = gc.getCoursepojo().getWebAutomation();

		for (int j = 0; j < webAutomation.size(); j++) {
			a.add(webAutomation.get(j).getCourseTitle());
		}

		java.util.List<String> expectedList = Arrays.asList(courseTitles);

		Assert.assertTrue(a.equals(expectedList));

	}

}
