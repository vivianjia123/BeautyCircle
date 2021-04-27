package domain;

import java.io.Serializable;

import com.google.gson.Gson;

public class RegisteredUserDTO extends UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private String username;
	private String password;

	private String shippingAddress;

	private WishListDTO wishList;
	private ShoppingCartDTO shoppingCart;

	@Override
	public long getId() {
		return this.userId;
	}

	@Override
	public void setId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public WishListDTO getWishlist() {
		return wishList;
	}

	public void setWishlist(WishListDTO wishlist) {
		this.wishList = wishlist;
	}

	public ShoppingCartDTO getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCartDTO shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);

	}

	public static RegisteredUserDTO readString(String s) {
		Gson gson = new Gson();
		return gson.fromJson(s, RegisteredUserDTO.class);
	}

}
