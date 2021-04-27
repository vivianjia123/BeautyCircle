package service;

import domain.Product;
import domain.WishList;
import mapper.WishListMapper;
import utility.UnitOfWork;

public class WishListService {

	private WishListMapper wishListMapper = new WishListMapper();

	public void createWishList(WishList wishList) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerNew(wishList);
		UnitOfWork.getCurrent().commit();
	}

	public void updateWishList(WishList wishList) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerNew(wishList);
		UnitOfWork.getCurrent().commit();
	}

	public void DeleteFromWishList(WishList wishList, Product product) {
		wishList.setToDelete(product);
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDeleted(wishList);
		UnitOfWork.getCurrent().commit();
	}

	public WishList findByUserId(WishList wishList) {

		return wishListMapper.findWithId(wishList);
	}

	public WishList findByUserAndProductId(WishList wishList, Product product) {

		return wishListMapper.findByProductAndUserId(wishList, product);
	}

}
