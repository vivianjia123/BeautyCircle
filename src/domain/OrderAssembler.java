package domain;

public class OrderAssembler {

	public OrderDTO writeDTO(Order order) {
		OrderDTO result = new OrderDTO();
		result.setOrderId(order.getId());
		result.setStatus(order.getStatus());
		result.setTimeCreated(order.getTimeCreated());
		result.setTotalPrice(order.getTotalPrice());
		writeRegisteredUserDTO(result, order);
		writeProductDTO(result, order);
		return result;

	}

	private void writeProductDTO(OrderDTO result, Order order) {
		for (Product p : order.getProducts()) {
			ProductDTO pdto = new ProductDTO();
			pdto.setCategory(p.getCategory());
			pdto.setDescription(p.getDescription());
			pdto.setName(p.getName());
			pdto.setPrice(p.getPrice());
			pdto.setProductId(p.getId());
			for (float i : p.getRating()) {
				pdto.setRating(i);
			}
			result.setProducts(pdto);
		}
	}

	private void writeRegisteredUserDTO(OrderDTO result, Order order) {
		RegisteredUserDTO user = new RegisteredUserDTO();
		user.setUsername(order.getUser().getUsername());
		user.setPassword(order.getUser().getPassword());
		user.setShippingAddress(order.getUser().getAddress());
		user.setId(order.getUser().getId());

		result.setUser(user);

	}

}
