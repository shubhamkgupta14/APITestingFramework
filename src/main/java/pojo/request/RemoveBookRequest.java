package pojo.request;

public class RemoveBookRequest {

	private String isbn;
	private String userId;

	public RemoveBookRequest(String isbn, String userId) {
		this.isbn = isbn;
		this.userId = userId;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getUserId() {
		return userId;
	}

}
