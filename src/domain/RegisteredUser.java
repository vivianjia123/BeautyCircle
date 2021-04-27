package domain;

public class RegisteredUser extends User {
	private long userId;
	private String username;
	private String password;
	private String shippingAddress;
	private String RegisteredUser = this.getClass().toString();

	public RegisteredUser(long userId, String firstName, String lastName, String emailAddress, String address,
			String username, String password, String shippingAddress) {
		super(userId, firstName, lastName, emailAddress, address, username, password, shippingAddress);
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.shippingAddress = shippingAddress;
		this.setUserType(RegisteredUser);
	}

	@Override
	public String getUserType() {
		return RegisteredUser;
	}

	public RegisteredUser(long userId) {
		super(userId);
		this.userId = userId;
	}

	public RegisteredUser(String username, String password) {
		super(username);
		this.username = username;
		this.password = password;
	}

	public RegisteredUser() {
		super();
	}

	@Override
	public long getId() {
		return this.userId;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		this.markDirty(this);
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.markDirty(this);
	}

	@Override
	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
		this.markDirty(this);
	}

	public void setId(long id) {
		this.userId = id;
	}

}
