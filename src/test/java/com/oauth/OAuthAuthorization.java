package com.oauth;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuthAuthorization {

	public static void main(String[] args) throws InterruptedException {
		

		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\kamesh p\\eclipse-workspace\\Assignment_RestAssured1810\\Driver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.get(
				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");

		driver.findElement(By.id("identifierId")).sendKeys("kameshkam3094@gmail.com");
		driver.findElement(By.xpath("//span[normalize-space()='Next']")).click();
		
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Kamesh@30");
		driver.findElement(By.xpath("//span[normalize-space()='Next']")).click();
		
		String url = driver.getCurrentUrl();
		System.out.println(url);
		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope")[0];

		System.out.println(code);

		String response = given()

				.queryParams("code", code)

				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("grant_type", "authorization_code").queryParams("state", "verifyfjdss")
				.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		System.out.println(accessToken);
		String s1 = given().contentType("application/json").queryParams("access_token", accessToken).expect()
				.defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(s1);

	}

}
