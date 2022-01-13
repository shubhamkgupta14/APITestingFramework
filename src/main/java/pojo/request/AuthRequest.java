package pojo.request;

public class AuthRequest {

	private String userName;
	private String password;

	public AuthRequest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	
}