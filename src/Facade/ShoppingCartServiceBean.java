package Facade;

import domain.ShoppingCart;
import domain.ShoppingCartAssembler;
import domain.ShoppingCartDTO;
import mapper.ShoppingCartMapper;

public class ShoppingCartServiceBean {

	public ShoppingCartDTO getShoppingCart(long id) {
		ShoppingCart cart = new ShoppingCart();
		cart.setId(id);
		return ShoppingCartAssembler.writeDTO(new ShoppingCartMapper().findById(cart).get(0));
	}

	public String getShoppingCartString(long id) {
		return getShoppingCart(id).toString();
	}

}
