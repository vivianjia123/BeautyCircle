package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.User;
import utility.DBConnection;
import utility.IdentityMap;

public class UserMapper {

	private static final String findStatementStringwithUserId = "SELECT id, firstname, lastname, emailaddress, address, username, password, shippingAddress,userType FROM users WHERE id = ?";
	private static final String findStatementStringwithUsername = "SELECT id, firstname, lastname, emailaddress, address, username, password, shippingAddress, userType FROM users WHERE username = ?";
	private static final String findStatementStringwithUserEmail = "SELECT id, firstname, lastname, emailaddress, address, username, password, shippingAddress, userType FROM users WHERE emailaddress = ?";
	private static final String findStatementString = "SELECT * FROM users";

	public User findwithUsername(User user) {

		User result = null;

		IdentityMap<User> identityMap = IdentityMap.getInstance(user);

		if (identityMap.get(user.getId()) != null) {
			return identityMap.get(user.getId());
		}

		try {
			PreparedStatement findStatement = DBConnection.prepare(findStatementStringwithUsername);
			findStatement.setString(1, user.getUsername());
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				long userId = resultSet.getLong(1);
				String userFirstName = resultSet.getString(2);
				String userLastName = resultSet.getString(3);
				String userEmailAddress = resultSet.getString(4);
				String userAddress = resultSet.getString(5);
				String username = resultSet.getString(6);
				String userPassword = resultSet.getString(7);
				String userShippingAddress = resultSet.getString(8);
				String userType = resultSet.getString(9);

				result = new User(userId, userFirstName, userLastName, userEmailAddress, userAddress, username,
						userPassword, userShippingAddress);
				result.setUserType(userType);
				identityMap.put(result.getId(), result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User findwithUsername(String username) {

		User result = null;

		try {
			PreparedStatement findStatement = DBConnection.prepare(findStatementStringwithUsername);
			findStatement.setString(1, username);
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				long userId = resultSet.getLong(1);
				String userFirstName = resultSet.getString(2);
				String userLastName = resultSet.getString(3);
				String userEmailAddress = resultSet.getString(4);
				String userAddress = resultSet.getString(5);
				String foundUsername = resultSet.getString(6);
				String userPassword = resultSet.getString(7);
				String userShippingAddress = resultSet.getString(8);
				String userType = resultSet.getString(9);

				result = new User(userId, userFirstName, userLastName, userEmailAddress, userAddress, foundUsername,
						userPassword, userShippingAddress);
				result.setUserType(userType);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User findwithEmail(User user) {
		User result = null;

		IdentityMap<User> identityMap = IdentityMap.getInstance(user);

		if (identityMap.get(user.getId()) != null) {
			return identityMap.get(user.getId());
		}

		try {
			PreparedStatement findStatement = DBConnection.prepare(findStatementStringwithUserEmail);
			findStatement.setString(1, user.getEmailAddress());
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				long userId = resultSet.getLong(1);
				String userFirstName = resultSet.getString(2);
				String userLastName = resultSet.getString(3);
				String userEmailAddress = resultSet.getString(4);
				String userAddress = resultSet.getString(5);
				String username = resultSet.getString(6);
				String userPassword = resultSet.getString(7);
				String userShippingAddress = resultSet.getString(8);
				String userType = resultSet.getString(9);

				result = new User(userId, userFirstName, userLastName, userEmailAddress, userAddress, username,
						userPassword, userShippingAddress);
				result.setUserType(userType);
				identityMap.put(result.getId(), result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public User findwithId(User user) {
		User result = null;

		IdentityMap<User> identityMap = IdentityMap.getInstance(user);

		if (identityMap.get(user.getId()) != null) {
			return identityMap.get(user.getId());
		}

		try {
			PreparedStatement findStatement = DBConnection.prepare(findStatementStringwithUserId);
			findStatement.setLong(1, user.getId());
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				long userId = resultSet.getLong(1);
				String userFirstName = resultSet.getString(2);
				String userLastName = resultSet.getString(3);
				String userEmailAddress = resultSet.getString(4);
				String userAddress = resultSet.getString(5);
				String username = resultSet.getString(6);
				String userPassword = resultSet.getString(7);
				String userShippingAddress = resultSet.getString(8);
				String userType = resultSet.getString(9);

				result = new User(userId, userFirstName, userLastName, userEmailAddress, userAddress, username,
						userPassword, userShippingAddress);
				result.setUserType(userType);
				identityMap.put(result.getId(), result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<User> findAllUsers() {
		ArrayList<User> result = new ArrayList<>();

		try {
			PreparedStatement findStatement = DBConnection.prepare(findStatementString);
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				long userId = resultSet.getLong(1);
				String userFirstName = resultSet.getString(2);
				String userLastName = resultSet.getString(3);
				String userEmailAddress = resultSet.getString(4);
				String userAddress = resultSet.getString(5);
				String username = resultSet.getString(6);
				String userPassword = resultSet.getString(7);
				String userShippingAddress = resultSet.getString(8);
				String userType = resultSet.getString(9);
				User user = new User(userId, userFirstName, userLastName, userEmailAddress, userAddress, username,
						userPassword, userShippingAddress);
				user.setUserType(userType);
				result.add(user);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
