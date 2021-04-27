package domain;

public class WishListAssembler {

	public static WishListDTO writeDTO(WishList wishlist) {
		WishListDTO result = new WishListDTO();
		result.setToDelete(ProductAssembler.writeDTO(wishlist.getToDelete()));
		result.setUser(RegisteredUserAssembler.writeDTO(wishlist.getUser()));
		for (Product p : wishlist.getWishlist()) {
			result.setWishlist(ProductAssembler.writeDTO(p));
		}
		return result;

	}

}
