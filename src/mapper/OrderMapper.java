package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import domain.DomainObject;
import domain.Order;
import domain.Product;
import domain.RegisteredUser;
import utility.DBConnection;
import utility.IdentityMap;

public class OrderMapper extends DataMapper {

	private static final String updateStatementString = "UPDATE shoppingorder SET status = ?, price = ? WHERE orderid = ? AND productid = ? AND registeredUserId = ?";

	private static final String insertStatementString = "INSERT INTO shoppingorder (price, registeredUserId, timeCreated, status, productid, orderid) VALUES"
			+ "(?, ?, ?, ?, ?, ?)";

	private static final String deleteStatementString = "DELETE FROM shoppingorder WHERE orderid =?";

	private static final String findStatementStringWithUserId = "SELECT price, registeredUserId, timeCreated, status, productid, orderid FROM shoppingorder WHERE registeredUserId = ?";
	private static final String findStatementStringwithUserAndProductId = "SELECT price, registeredUserId, timeCreated, status, productid, orderid FROM shoppingorder WHERE registeredUserId =? and productid = ?";
	private static final String findStatementStringWithOrderId = "SELECT price, registeredUserId, timeCreated, status, productid, orderid FROM shoppingorder WHERE orderid = ?";
	private static final String findStatementStringWithProductId = "SELECT price, registeredUserId, timeCreated, status, productid, orderid FROM shoppingorder WHERE productid = ?";

	@Override
	public void insert(DomainObject object) {
		Order order = (Order) object;

		IdentityMap<Order> identityMap = IdentityMap.getInstance(order);

		if (identityMap.get(order.getId()) != null) {
			Order temporary = identityMap.get(order.getId());
			for (Product product : order.getProducts()) {
				if (!temporary.getProducts().contains(product)) {
					temporary.setProducts(product);
				}
			}
			identityMap.put(temporary.getId(), temporary);
		}

		else {
			identityMap.put(order.getId(), order);
		}

		try {
			PreparedStatement insertStatement = DBConnection.prepare(insertStatementString);
			for (Product product : order.getProducts()) {
				insertStatement.setFloat(1, order.getTotalPrice());
				insertStatement.setLong(2, (order.getUser()).getId());
				insertStatement.setTimestamp(3, order.getTimeCreated());
				insertStatement.setString(4, order.getStatus().toString());
				insertStatement.setLong(5, product.getId());
				insertStatement.setLong(6, order.getId());

				insertStatement.executeUpdate();
				DBConnection.commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(DomainObject object) {
		Order order = (Order) object;
		IdentityMap<Order> identityMap = IdentityMap.getInstance(order);

		try {
			PreparedStatement updateStatement = DBConnection.prepare(updateStatementString);

			for (Product product : order.getProducts()) {
				updateStatement.setString(1, order.getStatus().toString());
				updateStatement.setFloat(2, order.getTotalPrice());
				updateStatement.setLong(3, order.getId());
				updateStatement.setLong(4, product.getId());
				updateStatement.setLong(5, (order.getUser()).getId());

				updateStatement.executeUpdate();
				DBConnection.commit();
				identityMap.put(order.getId(), order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(DomainObject object) {
		Order order = (Order) object;

		try {
			PreparedStatement deleteStatement = DBConnection.prepare(deleteStatementString);
			deleteStatement.setLong(1, order.getId());
			deleteStatement.executeUpdate();
			DBConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Order> findWithUserId(Order order) {
		List<Order> newOrders = new ArrayList<>();

		IdentityMap<Order> identityMap = IdentityMap.getInstance(order);
		Order identityOrder = new Order(RegisteredUserMapper.findwithId(new RegisteredUser(order.getUser().getId())));
		Order temporaryOrder = null;

		if (identityMap.get(order.getUser().getId()) != null) {
			newOrders.add(identityMap.get(order.getUser().getId()));
			return newOrders;
		}

		try {
			PreparedStatement findStatement = DBConnection.prepare(findStatementStringWithUserId);
			findStatement.setLong(1, order.getUser().getId());
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				float orderPrice = resultSet.getFloat(1);
				long userId = resultSet.getLong(2);
				Timestamp orderTime = resultSet.getTimestamp(3);
				String orderStatusString = resultSet.getString(4);
				long productid = resultSet.getLong(5);
				long orderid = resultSet.getLong(6);
				Order.Status orderStatus = Order.Status.valueOf(orderStatusString);

				RegisteredUser registeredUser = new RegisteredUser(userId);
				RegisteredUser orderUser = RegisteredUserMapper.findwithId(registeredUser);
				ArrayList<Product> productList = new ArrayList<>();

				temporaryOrder = new Order(orderid, orderPrice, orderUser, orderTime, orderStatus, productList);

				Product product = new Product(productid);
				Product foundProduct = ProductMapper.findProductById(product);

				temporaryOrder.setProducts(foundProduct);
				newOrders.add(temporaryOrder);
				identityOrder.setProducts(foundProduct);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newOrders;

	}

	public List<Order> findWithProductId(Product product) {
		List<Order> newOrders = new ArrayList<>();
		Order temporaryOrder;

		try {
			PreparedStatement findStatement = DBConnection.prepare(findStatementStringWithProductId);
			findStatement.setLong(1, product.getId());
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				float orderPrice = resultSet.getFloat(1);
				long userId = resultSet.getLong(2);
				Timestamp orderTime = resultSet.getTimestamp(3);
				String orderStatusString = resultSet.getString(4);
				long foundProductId = resultSet.getLong(5);
				long orderid = resultSet.getLong(6);
				Order.Status orderStatus = Order.Status.valueOf(orderStatusString);

				RegisteredUser registeredUser = new RegisteredUser(userId);
				RegisteredUser orderUser = RegisteredUserMapper.findwithId(registeredUser);
				ArrayList<Product> productList = new ArrayList<>();

				temporaryOrder = new Order(orderid, orderPrice, orderUser, orderTime, orderStatus, productList);

				Product temporary = new Product(foundProductId);
				Product foundProduct = ProductMapper.findProductById(temporary);

				temporaryOrder.setProducts(foundProduct);
				newOrders.add(temporaryOrder);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newOrders;

	}

	public Order findwithUserAndProductId(Order order) {
		Order newOrder = null;
		IdentityMap<Order> identityMap = utility.IdentityMap.getInstance(order);

		try {
			PreparedStatement findStatement = DBConnection.prepare(findStatementStringwithUserAndProductId);
			findStatement.setLong(1, order.getUser().getId());
			for (Product product : order.getProducts()) {
				findStatement.setLong(2, product.getId());
			}
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {

				float orderPrice = resultSet.getFloat(1);
				long userId = resultSet.getLong(2);
				Timestamp orderTime = resultSet.getTimestamp(3);
				String orderStatusString = resultSet.getString(4);
				long productId = resultSet.getLong(5);
				long orderid = resultSet.getLong(6);

				RegisteredUser registeredUser = new RegisteredUser(userId);
				RegisteredUser orderUser = RegisteredUserMapper.findwithId(registeredUser);

				Order.Status orderStatus = Order.Status.valueOf(orderStatusString);

				Product product = new Product(productId);
				Product foundProduct = ProductMapper.findProductById(product);
				ArrayList<Product> productList = new ArrayList<>();
				productList.add(foundProduct);

				newOrder = new Order(orderid, orderPrice, orderUser, orderTime, orderStatus, productList);
				identityMap.put(newOrder.getId(), newOrder);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newOrder;
	}

	public Order findWithOrderId(Order order) {
		Order newOrder = null;
		IdentityMap<Order> identityMap = utility.IdentityMap.getInstance(order);

		try {
			PreparedStatement findStatement = DBConnection.prepare(findStatementStringWithOrderId);
			findStatement.setLong(1, order.getId());

			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				float orderPrice = resultSet.getFloat(1);
				long userId = resultSet.getLong(2);
				Timestamp orderTime = resultSet.getTimestamp(3);
				String orderStatusString = resultSet.getString(4);
				long productId = resultSet.getLong(5);
				long orderid = resultSet.getLong(6);

				if (resultSet.isFirst()) {
					RegisteredUser registeredUser = new RegisteredUser(userId);
					RegisteredUser orderUser = RegisteredUserMapper.findwithId(registeredUser);

					Order.Status orderStatus = Order.Status.valueOf(orderStatusString);
					ArrayList<Product> productList = new ArrayList<>();
					newOrder = new Order(orderid, orderPrice, orderUser, orderTime, orderStatus, productList);
				}

				Product product = new Product(productId);
				Product foundProduct = ProductMapper.findProductById(product);

				newOrder.setProducts(foundProduct);

				identityMap.put(newOrder.getId(), newOrder);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newOrder;
	}

}
