package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Administrator;
import domain.DomainObject;
import utility.DBConnection;
import utility.IdentityMap;

public class AdministratorMapper extends DataMapper {
	private static final String insertStatementString = "INSERT INTO users (id, firstname, lastname, emailaddress, "
			+ "address, username, password, shippingAddress, userType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String updateStatementString = "UPDATE users SET firstname = ?, "
			+ "lastname = ?, emailaddress = ?, address = ?, username = ?, password = ?, shippingAddress = ?, userType = ?  WHERE id = ?";

	private static final String deleteStatementString = "DELETE FROM users WHERE id =?";

	private static final String findStatementStringwithUserId = "SELECT id, firstname, lastname, emailaddress, address, username, password, shippingAddress, userType FROM users WHERE id = ?";

	private static final String findStatementStringwithUsername = "SELECT id, firstname, lastname, emailaddress, address, username, password, shippingAddress, userType FROM users WHERE username = ?";

	private static final String findStatementStringwithUserEmail = "SELECT id, firstname, lastname, emailaddress, address, username, password, shippingAddress, userType FROM users WHERE emailaddress = ?";

	private static final String findStatementString = "SELECT * FROM users";

	@Override
	public void insert(DomainObject object) {
		Administrator user = (Administrator) object;

		try {
			PreparedStatement insertStatement = DBConnection.prepare(insertStatementString);

			insertStatement.setLong(1, user.getId());
			insertStatement.setString(2, user.getFirstName());
			insertStatement.setString(3, user.getLastName());
			insertStatement.setString(4, user.getEmailAddress());
			insertStatement.setString(5, user.getAddress());
			insertStatement.setString(6, user.getUsername());
			insertStatement.setString(7, user.getPassword());
			insertStatement.setString(8, user.getShippingAddress());
			insertStatement.setString(9, user.getUserType());

			insertStatement.executeUpdate();

			DBConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(DomainObject object) {

		Administrator user = (Administrator) object;

		PreparedStatement updateStatement = null;
		try {

			updateStatement = DBConnection.prepare(updateStatementString);

			updateStatement.setString(1, user.getFirstName());
			updateStatement.setString(2, user.getLastName());
			updateStatement.setString(3, user.getEmailAddress());
			updateStatement.setString(4, user.getAddress());
			updateStatement.setString(5, user.getUsername());
			updateStatement.setString(6, user.getPassword());
			updateStatement.setString(7, user.getShippingAddress());
			updateStatement.setString(8, user.getUserType());

			updateStatement.setLong(9, user.getId());

			updateStatement.executeUpdate();
			DBConnection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(DomainObject object) {
		Administrator user = (Administrator) object;

		try {
			PreparedStatement deleteStatement = DBConnection.prepare(deleteStatementString);
			deleteStatement.setLong(1, user.getId());
			deleteStatement.executeUpdate();
			DBConnection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Administrator findwithUsername(Administrator user) {

		Administrator result = null;

		IdentityMap<Administrator> identityMap = IdentityMap.getInstance(user);

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

				result = new Administrator(userId, userFirstName, userLastName, userEmailAddress, userAddress, username,
						userPassword);
				identityMap.put(result.getId(), result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Administrator findwithEmail(Administrator user) {
		Administrator result = null;

		IdentityMap<Administrator> identityMap = IdentityMap.getInstance(user);

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

				result = new Administrator(userId, userFirstName, userLastName, userEmailAddress, userAddress, username,
						userPassword);
				identityMap.put(result.getId(), result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public static Administrator findwithId(Administrator user) {
		Administrator result = null;

		IdentityMap<Administrator> identityMap = IdentityMap.getInstance(user);

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

				result = new Administrator(userId, userFirstName, userLastName, userEmailAddress, userAddress, username,
						userPassword);
				identityMap.put(result.getId(), result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Administrator> findAllUsers() {
		List<Administrator> result = new ArrayList<Administrator>();
		try {
			PreparedStatement preparedStatement = DBConnection.prepare(findStatementString);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				long userId = resultSet.getLong(1);
				String userFirstName = resultSet.getString(2);
				String userLastName = resultSet.getString(3);
				String userEmailAddress = resultSet.getString(4);
				String userAddress = resultSet.getString(5);
				String username = resultSet.getString(6);
				String userPassword = resultSet.getString(7);

				result.add(new Administrator(userId, userFirstName, userLastName, userEmailAddress, userAddress,
						username, userPassword));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
