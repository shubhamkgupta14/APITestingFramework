package pojo.request;

import java.util.ArrayList;
import java.util.List;

public class AddBookRequest {

	private String userId;
	private List<ISBN> collectionOfIsbns;

	public AddBookRequest(String userId, ISBN isbn) {
		this.userId = userId;
		collectionOfIsbns = new ArrayList<ISBN>();
		collectionOfIsbns.add(isbn);
	}

	public String getUserId() {
		return userId;
	}

	public List<ISBN> getCollectionOfIsbns() {
		return collectionOfIsbns;
	}

}
