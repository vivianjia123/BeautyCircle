package Facade;

import domain.Order;
import domain.OrderAssembler;
import domain.OrderDTO;
import mapper.OrderMapper;

public class OrderServiceBean {

	public OrderDTO getOrder(long orderId) {
		Order order = new Order();
		order.setOrderId(orderId);
		return new OrderAssembler().writeDTO(new OrderMapper().findWithOrderId(order));
	}

	public String getOrderString(long orderId) {
		return getOrder(orderId).toString();
	}

}
