package domain;

import java.util.ArrayList;

public class WishList extends DomainObject {

	private ArrayList<Product> wishlist;
	private RegisteredUser user;
	private Product toDelete;

	public WishList(RegisteredUser user) {
		this.user = user;
		wishlist = new ArrayList<Product>();
	}

	public WishList() {
	}

	@Override
	public long getId() {
		return this.user.getId();
	};

	public ArrayList<Product> getWishlist() {
		return wishlist;
	}

	public void setWishlist(Product product) {
		if (!wishlist.contains(product)) {
			this.wishlist.add(product);
		}
	}

	public Product getToDelete() {
		return toDelete;
	}

	public void setToDelete(Product product) {
		this.toDelete = product;
	}

	public void removeItem(Product product) {
		wishlist.remove(product);
	}

	public RegisteredUser getUser() {
		return this.user;
	}

	public void setId(long id) {
		user.setId(id);
	}

}
