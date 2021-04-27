package domain;

import java.util.HashMap;

public class ShoppingCart extends DomainObject {

	private RegisteredUser user;
	private HashMap<Product, Integer> productMap;
	private Product toDelete;

	public ShoppingCart(RegisteredUser user) {
		this.user = user;
		productMap = new HashMap<Product, Integer>();
	}

	public ShoppingCart() {
	}

	@Override
	public long getId() {
		return user.getId();
	}

	public RegisteredUser getUser() {
		return this.user;
	}

	public HashMap<Product, Integer> getProductMap() {
		return productMap;
	}

	public void setProductMap(Product product, int quantity) {
		if (productMap.get(product) != null) {
			int newQuantity = productMap.get(product) + quantity;
			productMap.put(product, newQuantity);
		} else {
			this.productMap.put(product, quantity);
		}
	}

	public void removeItem(Product product) {
		productMap.remove(product);
	}

	public Product getToDelete() {
		return toDelete;
	}

	public void setToDelete(Product product) {
		this.toDelete = product;
	}

	public void setId(long id) {
		user.setId(id);
	}

}
