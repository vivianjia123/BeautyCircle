package domain;

public class User extends DomainObject {

	private String firstName;
	private String lastName;
	private String emailAddress;
	private String username;
	private String address;
	private long id;
	private String password;
	private String shippingAddress;
	private String userType;

	private static long universalId = 12;

	public User(long id, String firstName, String lastName, String emailAddress, String address, String username,
			String password, String shippingAddress) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.emailAddress = emailAddress;
		this.address = address;
		this.password = password;
		this.shippingAddress = shippingAddress;
	}

	public static long universalId() {
		return User.universalId;
	}

	public static void increaseUniversalId() {
		User.universalId++;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return this.userType;
	}

	public User(String username) {
		this.username = username;
	}

	public User(long id) {
		this.id = id;
	}

	public User() {
	}

	@Override
	public long getId() {
		return this.id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getAddress() {
		return address;
	}

	public String getUsername() {
		return this.username;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public String getPassword() {
		return password;
	}

}
