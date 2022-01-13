package pojo.response;

public class Token {

	private String token;
	private String expires;
	private String status;
	private String result;

	public String getToken() {
		return token;
	}

	public String getExpires() {
		return expires;
	}

	public String getStatus() {
		return status;
	}

	public String getResult() {
		return result;
	}

//	{
//		"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFwaV91c2VyIiwicGFzc3dvcmQiOiJUZXN0QDEyMyIsImlhdCI6MTY0MjA0Nzg1OH0.6t3cm-8OZBfByeQPIfnPKP--oLvhgePBL2bpSOhMG8U",
//		"expires": "2022-01-20T04:24:18.331Z",
//		"status": "Success",
//		"result": "User authorized successfully."
//		}

}
