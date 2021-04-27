package domain;

import java.io.Serializable;
import java.util.HashMap;

import com.google.gson.Gson;

public class ShoppingCartDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private RegisteredUserDTO user;
	private HashMap<ProductDTO, Integer> productMap;
	private ProductDTO toDelete;

	public RegisteredUserDTO getUser() {
		return this.user;
	}

	public void setUser(RegisteredUserDTO user) {
		this.user = user;
	}

	public HashMap<ProductDTO, Integer> getProductMap() {
		return productMap;
	}

	public void setProductMap(ProductDTO product, int quantity) {
		if (productMap.get(product) != null) {
			int newQuantity = productMap.get(product) + quantity;
			productMap.put(product, newQuantity);
		} else {
			this.productMap.put(product, quantity);
		}
	}

	public ProductDTO getToDelete() {
		return toDelete;
	}

	public void setToDelete(ProductDTO product) {
		this.toDelete = product;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);

	}

	public static ShoppingCartDTO readString(String s) {
		Gson gson = new Gson();
		return gson.fromJson(s, ShoppingCartDTO.class);
	}

}
