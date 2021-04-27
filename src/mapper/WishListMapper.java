package mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.DomainObject;
import domain.Product;
import domain.RegisteredUser;
import domain.WishList;
import utility.DBConnection;
import utility.IdentityMap;

public class WishListMapper extends DataMapper {

	private static final String updateStatementString = "UPDATE wishlist SET registeredUserid = ?, productid = ? WHERE registeredUserId = ?";
	private static final String insertStatementString = "INSERT INTO wishlist (registeredUserid, productid) VALUES (?, ?)";
	private static final String deleteStatementStringbyProductid = "DELETE FROM wishlist WHERE registeredUserId = ? AND productid = ?";
	private static final String findStatementbyUserId = "SELECT registeredUserid, productid FROM wishlist WHERE registeredUserid = ?";
	private static final String findCartStatementbyProductId = "SELECT registeredUserid, productid FROM wishList WHERE registeredUserid = ? AND productid = ?";

	@Override
	public void insert(DomainObject object) {

		WishList wishList = (WishList) object;
		IdentityMap<WishList> identityMap = IdentityMap.getInstance(wishList);

		if (identityMap.get(wishList.getId()) != null) {
			WishList temporary = identityMap.get(wishList.getId());
			for (int i = 0; i < wishList.getWishlist().size(); i++) {
				temporary.setWishlist(wishList.getWishlist().get(i));
			}
			identityMap.put(temporary.getId(), temporary);
		}

		else {
			identityMap.put(wishList.getId(), wishList);
		}

		try {
			PreparedStatement insertStatement = DBConnection.prepare(insertStatementString);

			for (int i = 0; i < wishList.getWishlist().size(); i++) {
				insertStatement.setLong(1, wishList.getId());
				insertStatement.setLong(2, wishList.getWishlist().get(i).getId());
				insertStatement.executeUpdate();
			}

			DBConnection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(DomainObject object) {
		WishList wishList = (WishList) object;

		try {
			PreparedStatement updateStatement = DBConnection.prepare(updateStatementString);
			for (int i = 0; i < wishList.getWishlist().size(); i++) {
				updateStatement.setLong(1, wishList.getId());
				updateStatement.setLong(2, wishList.getWishlist().get(i).getId());
				updateStatement.executeUpdate();
			}
			DBConnection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(DomainObject object) {
		WishList wishList = (WishList) object;

		try {
			PreparedStatement deleteStatement = DBConnection.prepare(deleteStatementStringbyProductid);
			deleteStatement.setLong(1, wishList.getId());
			deleteStatement.setLong(2, wishList.getToDelete().getId());
			deleteStatement.executeUpdate();

			IdentityMap<WishList> identityMap = IdentityMap.getInstance(wishList);
			WishList temporary = identityMap.get(wishList.getId());
			temporary.removeItem(wishList.getToDelete());
			identityMap.put(temporary.getId(), temporary);

			DBConnection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public WishList findWithId(WishList wishList) {
		WishList newWishList = null;

		IdentityMap<WishList> identityMap = IdentityMap.getInstance(wishList);

		if (identityMap.get(wishList.getId()) != null) {
			return identityMap.get(wishList.getId());
		}

		try {
			PreparedStatement findStatement = DBConnection.prepare(findStatementbyUserId);
			findStatement.setLong(1, wishList.getId());
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				long wishListId = resultSet.getLong(1);
				long productId = resultSet.getLong(2);

				RegisteredUser registeredUser = new RegisteredUser(wishListId);
				RegisteredUser user = RegisteredUserMapper.findwithId(registeredUser);
				newWishList = new WishList(user);

				Product product = new Product(productId);
				Product foundProduct = ProductMapper.findProductById(product);
				newWishList.setWishlist(foundProduct);
				identityMap.put(newWishList.getId(), newWishList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newWishList;
	}

	public WishList findByProductAndUserId(WishList wishList, Product foundProduct) {
		WishList newWishList = null;

		try {
			PreparedStatement findStatement = DBConnection.prepare(findCartStatementbyProductId);
			findStatement.setLong(1, wishList.getId());
			findStatement.setLong(2, foundProduct.getId());
			ResultSet resultSet = findStatement.executeQuery();

			while (resultSet.next()) {
				long wishListId = resultSet.getLong(1);
				long productId = resultSet.getLong(2);

				RegisteredUser registeredUser = new RegisteredUser(wishListId);
				RegisteredUser user = RegisteredUserMapper.findwithId(registeredUser);
				newWishList = new WishList(user);

				Product product = new Product(productId);
				Product temporaryProduct = ProductMapper.findProductById(product);
				newWishList.setWishlist(foundProduct);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newWishList;
	}

}
