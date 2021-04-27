package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Product;
import domain.ShoppingCart;
import mapper.ShoppingCartMapper;
import utility.UnitOfWork;

public class ShoppingCartService {

	private ShoppingCartMapper shoppingCartMapper = new ShoppingCartMapper();

	public void createCart(ShoppingCart shoppingCart) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerNew(shoppingCart);
		UnitOfWork.getCurrent().commit();
	}

	public void updateCart(ShoppingCart shoppingCart) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDirty(shoppingCart);
		UnitOfWork.getCurrent().commit();
	}

	public void deleteFromCart(ShoppingCart shoppingCart, Product product) {
		shoppingCart.setToDelete(product);
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDeleted(shoppingCart);
		UnitOfWork.getCurrent().commit();
	}

	public void changeQuantity(ShoppingCart shoppingCart, Product product, int quantity) {
		shoppingCart.setProductMap(product, quantity);
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDirty(shoppingCart);
		UnitOfWork.getCurrent().commit();
	}

	public List<ShoppingCart> findByUserId(ShoppingCart shoppingCart) {

		return shoppingCartMapper.findById(shoppingCart);
	}

	public ShoppingCart findByUserAndProductId(ShoppingCart shoppingCart, Product product) {

		return shoppingCartMapper.findByProductAndUserId(shoppingCart, product);
	}

	public float calculateTotalPrice(ShoppingCart cart) {
		float totalPrice = 0;
		HashMap<Product, Integer> map = cart.getProductMap();
		for (Map.Entry<Product, Integer> entry : map.entrySet()) {
			totalPrice += entry.getKey().getPrice() * entry.getValue();
		}
		return totalPrice;
	}

}
