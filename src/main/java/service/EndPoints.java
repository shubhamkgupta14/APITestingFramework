package service;

import constants.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.request.AddBookRequest;
import pojo.request.AuthRequest;
import pojo.request.RemoveBookRequest;

public class EndPoints {

	public static Response authenticateUser(AuthRequest authRequest) {
		RestAssured.baseURI = Constants.BASE_URL;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		Response response = request.body(authRequest).post(Route.generateToken());
		return response;
	}

	public static Response getBooks() {
		RestAssured.baseURI = Constants.BASE_URL;
		RequestSpecification request = RestAssured.given();

		Response response = request.get(Route.books());
		return response;
	}

	public static Response addBook(AddBookRequest addRequest, String token) {
		RestAssured.baseURI = Constants.BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

		Response response = request.body(addRequest).post(Route.books());
		return response;
	}

	public static Response removeBook(RemoveBookRequest removeRequest, String token) {
		RestAssured.baseURI = Constants.BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

		Response response = request.body(removeRequest).delete(Route.book());
		System.out.println(response.getBody().asPrettyString());
		return response;
	}

	public static Response getUserAccount(String userId, String token) {

		RestAssured.baseURI = Constants.BASE_URL;
		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

		Response response = request.get(Route.userAccount(userId));
		return response;
	}
}
