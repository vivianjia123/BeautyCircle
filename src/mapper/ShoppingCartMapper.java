package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.DomainObject;
import domain.Product;
import domain.RegisteredUser;
import domain.ShoppingCart;
import utility.DBConnection;
import utility.IdentityMap;

public class ShoppingCartMapper extends DataMapper {

	private static final String insertStatementString = "INSERT INTO shoppingcart (registeredUserid, productid, quantity) VALUES (?, ?, ?);";
	private static final String updateStatementString = "UPDATE shoppingcart SET quantity = ? WHERE registeredUserId = ? AND productid = ?";
	private static final String deleteStatementStringbyProductid = "DELETE FROM shoppingcart WHERE registeredUserId = ? AND productid = ?";
	private static final String findCartStatementbyUserId = "SELECT registeredUserid, productid, quantity FROM shoppingcart WHERE registeredUserid = ?";
	private static final String findCartStatementbyProductId = "SELECT registeredUserid, productid, quantity FROM shoppingcart WHERE registeredUserid = ? AND productid = ?";

	@Override
	public void insert(DomainObject object) {
		ShoppingCart shoppingCart = (ShoppingCart) object;
		IdentityMap<ShoppingCart> identityMap = IdentityMap.getInstance(shoppingCart);

		if (identityMap.get(shoppingCart.getId()) != null) {
			ShoppingCart temporary = identityMap.get(shoppingCart.getId());
			for (Product product : shoppingCart.getProductMap().keySet()) {
				temporary.setProductMap(product, shoppingCart.getProductMap().get(product));
			}
			identityMap.put(temporary.getId(), temporary);
		}

		else {
			identityMap.put(shoppingCart.getId(), shoppingCart);
		}

		try {
			PreparedStatement insertStatement = DBConnection.prepare(insertStatementString);

			for (Product product : shoppingCart.getProductMap().keySet()) {
				insertStatement.setLong(1, shoppingCart.getUser().getId());
				insertStatement.setLong(2, product.getId());
				insertStatement.setInt(3, shoppingCart.getProductMap().get(product));
			}
			insertStatement.executeUpdate();
			DBConnection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(DomainObject object) {
		ShoppingCart shoppingCart = (ShoppingCart) object;
		try {

			PreparedStatement updateStatement = DBConnection.prepare(updateStatementString);
			for (Product product : shoppingCart.getProductMap().keySet()) {

				updateStatement.setInt(1, shoppingCart.getProductMap().get(product));
				updateStatement.setLong(2, shoppingCart.getUser().getId());
				updateStatement.setLong(3, product.getId());
				updateStatement.executeUpdate();

			}
			DBConnection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(DomainObject object) {
		ShoppingCart shoppingCart = (ShoppingCart) object;

		try {
			PreparedStatement deleteStatement = DBConnection.prepare(deleteStatementStringbyProductid);
			deleteStatement.setLong(1, shoppingCart.getUser().getId());
			deleteStatement.setLong(2, shoppingCart.getToDelete().getId());
			deleteStatement.executeUpdate();

			IdentityMap<ShoppingCart> identityMap = IdentityMap.getInstance(shoppingCart);
			ShoppingCart temporary = identityMap.get(shoppingCart.getId());
			temporary.removeItem(shoppingCart.getToDelete());
			identityMap.put(temporary.getId(), temporary);

			DBConnection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ShoppingCart> findById(ShoppingCart shoppingCart) {

		List<ShoppingCart> newShoppingCart = new ArrayList<>();
		ShoppingCart identityCart = new ShoppingCart(
				RegisteredUserMapper.findwithId(new RegisteredUser(shoppingCart.getId())));
		IdentityMap<ShoppingCart> identityMap = IdentityMap.getInstance(shoppingCart);

		if (identityMap.get(shoppingCart.getId()) != null) {
			newShoppingCart.add(identityMap.get(shoppingCart.getId()));
			return newShoppingCart;
		}

		try {
			PreparedStatement findStatement = DBConnection.prepare(findCartStatementbyUserId);
			findStatement.setLong(1, shoppingCart.getId());
			ResultSet resultSet = findStatement.executeQuery();
			while (resultSet.next()) {
				long shoppingCartId = resultSet.getLong(1);
				long productId = resultSet.getLong(2);
				int quantity = resultSet.getInt(3);

				RegisteredUser registeredUser = new RegisteredUser(shoppingCartId);
				RegisteredUser user = RegisteredUserMapper.findwithId(registeredUser);
				ShoppingCart temporaryShoppingCart = new ShoppingCart(user);

				Product product = new Product(productId);
				Product foundProduct = ProductMapper.findProductById(product);
				temporaryShoppingCart.setProductMap(foundProduct, quantity);
				identityCart.setProductMap(foundProduct, quantity);
				newShoppingCart.add(temporaryShoppingCart);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		identityMap.put(identityCart.getId(), identityCart);

		return newShoppingCart;
	}

	public ShoppingCart findByProductAndUserId(ShoppingCart shoppingCart, Product foundProduct) {
		IdentityMap<ShoppingCart> identityMap = IdentityMap.getInstance(shoppingCart);
		ShoppingCart temporaryShoppingCart = null;

		try {
			PreparedStatement findStatement = DBConnection.prepare(findCartStatementbyProductId);
			findStatement.setLong(1, shoppingCart.getId());
			findStatement.setLong(2, foundProduct.getId());
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				long shoppingCartId = resultSet.getLong(1);
				long productId = resultSet.getLong(2);
				int quantity = resultSet.getInt(3);

				RegisteredUser registeredUser = new RegisteredUser(shoppingCartId);
				RegisteredUser user = RegisteredUserMapper.findwithId(registeredUser);
				temporaryShoppingCart = new ShoppingCart(user);

				Product product = new Product(productId);

				Product temporaryProduct = ProductMapper.findProductById(product);
				temporaryShoppingCart.setProductMap(temporaryProduct, quantity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return temporaryShoppingCart;
	}

}
