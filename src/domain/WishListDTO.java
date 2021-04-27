package domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;

public class WishListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<ProductDTO> wishlist;
	private RegisteredUserDTO user;
	private ProductDTO toDelete;

	public long getId() {
		return this.user.getId();
	}

	public RegisteredUserDTO getUser() {
		return this.user;
	}

	public void setUser(RegisteredUserDTO user) {
		this.user = user;
	}

	public ArrayList<ProductDTO> getWishlist() {
		return wishlist;
	}

	public void setWishlist(ProductDTO product) {
		if (!wishlist.contains(product)) {
			this.wishlist.add(product);
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

	public static WishListDTO readString(String s) {
		Gson gson = new Gson();
		return gson.fromJson(s, WishListDTO.class);
	}

}
