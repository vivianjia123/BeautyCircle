package domain;

import java.io.Serializable;

import com.google.gson.Gson;

public class AdministratorDTO extends UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String adminLogin;
	private String adminpassword;

	public String getAdminLogin() {
		return adminLogin;
	}

	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}

	public String getAdminpassword() {
		return adminpassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
		// return "Administrator: AdminLogin=" + adminLogin + " AdminPassword=" +
		// adminpassword;
		// return JSONObject.fromObject(this).toString();

	}

	public static AdministratorDTO readString(String s) {
		Gson gson = new Gson();
		return gson.fromJson(s, AdministratorDTO.class);
//		JSONObject json = JSONObject.fromObject(s);
//		return (AdministratorDTO) JSONObject.toBean(json, AdministratorDTO.class);
	}

}
