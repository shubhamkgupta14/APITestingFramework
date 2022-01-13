package sample.test;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Registration {
	
	static String username = "api_user";
	static String password = "Test@123";
	static String userID = "7c5a06f3-ec97-4491-ba57-ea4054313616";
	static String token = "";

//	@Test
	public void registration() {
		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.contentType(ContentType.JSON);
		request.accept(ContentType.JSON);

		JSONObject params = new JSONObject();
		params.put("userName", username);
		params.put("password", password);

		request.body(params.toJSONString());
		Response response = request.post("/Account/v1/User");
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 201);
		userID = response.getBody().jsonPath().getString("userID");

	}
	
	@Test
	public void login() {
		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.contentType(ContentType.JSON);
		request.accept(ContentType.JSON);
		
		JSONObject params = new JSONObject();
		params.put("userName", username);
		params.put("password", password);

		request.body(params.toJSONString());
		Response response = request.post("/Account/v1/GenerateToken");
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(response.asString().contains("token"));
		token = JsonPath.from(response.asString()).get("token");
	}
	
//	@Test
	public void addBook() {
		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		RequestSpecification request = RestAssured.given();
		
		request.header("Authorization", "Bearer " + token)
        .header("Content-Type", "application/json");
		
//		response = request.body("{ \"userId\": \"" + userID + "\", " +
//                "\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
//                .post("/BookStore/v1/Books");
	}
	

//	@Test
	public void deleteUser() {
		RestAssured.baseURI = "https://bookstore.toolsqa.com";
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");
		request.contentType(ContentType.JSON);
		request.accept(ContentType.JSON);
		
		
	}
}
