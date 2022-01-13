package stepDef;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;

import constants.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.request.AddBookRequest;
import pojo.request.AuthRequest;
import pojo.request.ISBN;
import pojo.request.RemoveBookRequest;
import pojo.response.Book;
import pojo.response.Books;
import pojo.response.Token;
import pojo.response.UserAccount;
import service.EndPoints;

public class stepDefinitions {

	public static Token tokenResponse;
	public static Books booksResponse;
	public static Response response;
	public static String jsonString;
	public static String bookId;
	public static String addBook = "Programming JavaScript Applications";
	public static String removeBook = "Programming JavaScript Applications";

	@Given("I am an authorized user")
	public void i_am_an_authorized_user() {
		try {
			AuthRequest authRequest = new AuthRequest(Constants.USERNAME, Constants.PASSWORD);

			response = EndPoints.authenticateUser(authRequest);
			tokenResponse = response.getBody().as(Token.class);

			Assert.assertEquals(response.getStatusCode(), 200);

			if (tokenResponse.getToken() != null) {
				System.out.println("\n" + tokenResponse.getResult());
				assertTrue("\nMessage - " + tokenResponse.getResult(), true);
			} else {
				assertTrue("\nMessage - " + tokenResponse.getResult(), false);
			}

		} catch (Exception e) {
//			assertTrue("\ni_am_an_authorized_user - " + e.toString(), false);
		}
	}

	@Given("A list of books are available")
	public void a_list_of_books_are_available() {
		try {
			response = EndPoints.getBooks();
			booksResponse = response.getBody().as(Books.class);

			System.out.println("\nAvailable Books: ");
			int count = 1;
			for (Book book : booksResponse.getBooks()) {
				System.out.println(count++ + " - " + book.getTitle());
			}
		} catch (Exception e) {
			assertTrue("\na_list_of_books_are_available - " + e.toString(), false);
		}

	}

	@When("I add a book to my reading list")
	public void i_add_a_book_to_my_reading_list() {
		try {
			for (Book book : booksResponse.getBooks()) {
				if (book.getTitle().equalsIgnoreCase(addBook)) {
					bookId = book.getIsbn();
					break;
				} else {
					bookId = "NAVL";
				}
			}

			AddBookRequest addBook = new AddBookRequest(Constants.USER_ID, new ISBN(bookId));

			response = EndPoints.addBook(addBook, tokenResponse.getToken());
		} catch (Exception e) {
			assertTrue("\ni_add_a_book_to_my_reading_list - " + e.toString(), false);
		}

	}

	@Then("the book is added")
	public void the_book_is_added() {
		try {
			if (response.getStatusCode() == 201) {
				System.out.println("\nAdded Book : " + addBook);
				assertTrue("Added Book : " + addBook, true);
			} else {
				assertTrue("\nMessage - " + JsonPath.from(response.asString()).get("message"), false);
			}
		} catch (Exception e) {
			assertTrue("\nthe_book_is_added - " + e.toString(), false);
		}
	}

	@Then("check the available books")
	public void check_the_available_books() {
		try {
			response = EndPoints.getUserAccount(Constants.USER_ID, tokenResponse.getToken());
			
			UserAccount userAccount = response.getBody().as(UserAccount.class);

			List<Book> userBooks = userAccount.getBooks();
			int count = 1;
			if (userBooks.size() > 0) {
				System.out.println("\n\"" + userAccount.getUsername() + "\" has following available books - ");
				for (Book book : userBooks) {
					System.out.println(count++ + " - " + book.getTitle());
				}
			} else {
				System.out.println("\n\"" + userAccount.getUsername() + "\" has not any available books.");
			}
			System.out.println();
		} catch (Exception e) {
			assertTrue("\ncheck_the_available_books - " + e.toString(), false);
		}

	}

	@When("I remove a book from my reading list")
	public void i_remove_a_book_from_my_reading_list() {
		try {
		for (Book book : booksResponse.getBooks()) {
			if (book.getTitle().equalsIgnoreCase(removeBook)) {
				bookId = book.getIsbn();
				break;
			} else {
				bookId = "NAVL";
			}
		}

		RemoveBookRequest remBook = new RemoveBookRequest(bookId, Constants.USER_ID);

		response = EndPoints.removeBook(remBook, tokenResponse.getToken());
		} catch(Exception e) {
			assertTrue("\ni_remove_a_book_from_my_reading_list - " + e.toString(), false);
		}
	}

	@Then("the book is removed")
	public void the_book_is_removed() {
		try {
		if (response.getStatusCode() == 204) {
			System.out.println("\nRemoveded Book : " + removeBook);
			assertTrue("Removed Book : " + removeBook, true);
		} else {
			assertTrue("\nMessage - " + JsonPath.from(response.asString()).get("message"), false);
		}}catch(Exception e) {
			assertTrue("\nthe_book_is_removed - " + e.toString(), false);
		}
	}

}
