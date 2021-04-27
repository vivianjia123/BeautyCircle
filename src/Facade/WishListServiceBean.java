package Facade;

import domain.WishList;
import domain.WishListAssembler;
import domain.WishListDTO;
import mapper.WishListMapper;

public class WishListServiceBean {

	public WishListDTO getWishList(long id) {
		WishList list = new WishList();
		list.setId(id);
		return WishListAssembler.writeDTO(new WishListMapper().findWithId(list));
	}

	public String getWishListString(long id) {
		return getWishList(id).toString();
	}

}
