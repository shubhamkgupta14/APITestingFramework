package pojo.response;

import java.util.List;

public class UserAccount {

	private String userId;
	private String username;
	private List<Book> books;

	public String getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public List<Book> getBooks() {
		return books;
	}

}
