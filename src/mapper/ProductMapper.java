package mapper;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.DomainObject;
import domain.Product;
import utility.DBConnection;
import utility.IdentityMap;

public class ProductMapper extends DataMapper {
	private static final String insertStatementString = "INSERT INTO product (id, name, category, description, price, rating) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String updateStatementString = "UPDATE product SET name = ?, category = ?, description = ?, price = ?, rating = ? WHERE id = ?";
	private static final String deleteStatementString = "DELETE FROM product WHERE id =?";
	private static final String findStatementString = "SELECT * FROM product";
	private static final String findStatementById = "SELECT id, name, category, description, price, rating FROM product WHERE id = ?";
	private static final String findStatementByName = "SELECT id, name, category, description, price, rating FROM product WHERE name = ?";

	@Override
	public void insert(DomainObject object) {
		Product product = (Product) object;

		PreparedStatement insertStatement = null;
		try {
			Connection dbConnection = DBConnection.getDbConnection();
			Array ratingSQLArray = dbConnection.createArrayOf("float", (product.getRating()).toArray());
			insertStatement = DBConnection.prepare(insertStatementString);
			insertStatement.setLong(1, product.getId());
			insertStatement.setString(2, product.getName());
			insertStatement.setString(3, product.getCategory());
			insertStatement.setString(4, product.getDescription());
			insertStatement.setFloat(5, product.getPrice());
			insertStatement.setArray(6, ratingSQLArray);
			insertStatement.executeUpdate();

			DBConnection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(DomainObject object) {
		Product product = (Product) object;
		IdentityMap<Product> identityMap = IdentityMap.getInstance(product);

		PreparedStatement updateStatement = null;
		try {
			Connection dbConnection = DBConnection.getDbConnection();
			Array ratingSQLArray = dbConnection.createArrayOf("float", (product.getRating()).toArray());
			updateStatement = DBConnection.prepare(updateStatementString);
			updateStatement.setString(1, product.getName());
			updateStatement.setString(2, product.getCategory());
			updateStatement.setString(3, product.getDescription());
			updateStatement.setFloat(4, product.getPrice());
			updateStatement.setArray(5, ratingSQLArray);
			updateStatement.setLong(6, product.getId());
			updateStatement.executeUpdate();
			identityMap.put(product.getId(), product);

			DBConnection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(DomainObject object) {
		Product product = (Product) object;
		try {
			PreparedStatement deleteStatement = DBConnection.prepare(deleteStatementString);
			deleteStatement.setLong(1, product.getId());
			deleteStatement.executeUpdate();
			DBConnection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Product> findAllProducts() {

		List<Product> result = new ArrayList<Product>();

		try {
			PreparedStatement preparedStatement = DBConnection.prepare(findStatementString);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				long productId = resultSet.getLong(1);
				String productName = resultSet.getString(2);
				String productCategory = resultSet.getString(3);
				String productDescription = resultSet.getString(4);
				float productPrice = resultSet.getFloat(5);

				Object[] ratingList = (Object[]) (resultSet.getArray(6)).getArray();
				ArrayList<Float> productRating = new ArrayList<>();
				for (Object rating : ratingList) {
					productRating.add(((Double) rating).floatValue());
				}

				Product newProduct = new Product(productId, productName, productCategory, productDescription,
						productPrice, productRating);
				result.add(newProduct);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Product> findProductByName(Product product) {
		List<Product> result = new ArrayList<Product>();

		IdentityMap<Product> identityMap = IdentityMap.getInstance(product);

		if (identityMap.get(product.getId()) != null) {
			result.add(identityMap.get(product.getId()));
			return result;
		}

		try {
			PreparedStatement preparedStatement = DBConnection.prepare(findStatementByName);
			preparedStatement.setString(1, product.getName());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long productId = resultSet.getLong(1);
				String productName = resultSet.getString(2);
				String productCategory = resultSet.getString(3);
				String productDescription = resultSet.getString(4);
				float productPrice = resultSet.getFloat(5);

				Object[] ratingList = (Object[]) (resultSet.getArray(6)).getArray();
				ArrayList<Float> productRating = new ArrayList<>();
				for (Object rating : ratingList) {
					productRating.add(((Double) rating).floatValue());
				}

				Product newProduct = new Product(productId, productName, productCategory, productDescription,
						productPrice, productRating);
				result.add(newProduct);
				identityMap.put(newProduct.getId(), newProduct);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static Product findProductById(Product product) {
		Product result = null;

		IdentityMap<Product> identityMap = IdentityMap.getInstance(product);

		if (identityMap.get(product.getId()) != null) {
			return identityMap.get(product.getId());
		}

		try {
			PreparedStatement preparedStatement = DBConnection.prepare(findStatementById);
			preparedStatement.setLong(1, product.getId());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long productId = resultSet.getLong(1);
				String productName = resultSet.getString(2);
				String productCategory = resultSet.getString(3);
				String productDescription = resultSet.getString(4);
				float productPrice = resultSet.getFloat(5);

				Object[] ratingList = (Object[]) (resultSet.getArray(6)).getArray();
				ArrayList<Float> productRating = new ArrayList<>();
				for (Object rating : ratingList) {
					productRating.add(((Double) rating).floatValue());
				}

				result = new Product(productId, productName, productCategory, productDescription, productPrice,
						productRating);
				identityMap.put(result.getId(), result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
