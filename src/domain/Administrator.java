package domain;

public class Administrator extends User {

	private String adminLogin;
	private String adminPassword;
	private long adminId;
	private String Administrator = this.getClass().toString();

	public Administrator(long adminId, String firstName, String lastName, String emailAddress, String address,
			String adminLogin, String adminPassword) {
		super(adminId, firstName, lastName, emailAddress, address, adminLogin, adminPassword, "");
		this.adminId = adminId;
		this.adminLogin = adminLogin;
		this.adminPassword = adminPassword;
		this.setUserType(Administrator);
	}

	public Administrator() {
	}

	@Override
	public String getUserType() {
		return Administrator;
	}

	public String getAdminLogin() {
		return adminLogin;
	}

	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}

	public String getAdminpassword() {
		return adminPassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminPassword = adminpassword;
	}

	public long getUserId() {
		return adminId;
	}

	public void setUserId(long userId) {
		this.adminId = userId;
	}

}
