package domain;

import java.util.Map.Entry;

public class ShoppingCartAssembler {

	public static ShoppingCartDTO writeDTO(ShoppingCart cart) {
		ShoppingCartDTO result = new ShoppingCartDTO();
		result.setToDelete(ProductAssembler.writeDTO(cart.getToDelete()));
		result.setUser(RegisteredUserAssembler.writeDTO(cart.getUser()));
		for (Entry<Product, Integer> entry : cart.getProductMap().entrySet()) {
			result.setProductMap(ProductAssembler.writeDTO(entry.getKey()), entry.getValue());
		}
		return result;

	}

}
