package service;

import java.util.ArrayList;
import java.util.List;

import domain.Order;
import domain.Product;
import mapper.OrderMapper;
import utility.UnitOfWork;

public class OrderService {

	private OrderMapper orderMapper = new OrderMapper();

	public void createOrder(Order order) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerNew(order);
		UnitOfWork.getCurrent().commit();
	}

	public void updateOrder(Order order) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDirty(order);
		UnitOfWork.getCurrent().commit();
	}

	public void cancelOrder(Order order) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDeleted(order);
		UnitOfWork.getCurrent().commit();
	}

	public String viewOrder(Order order) {
		String detail = "Name: " + order.getUser().getFirstName() + order.getUser().getLastName() + " Price: "
				+ order.getTotalPrice() + " Time: " + order.getTimeCreated() + " Status: " + order.getStatus()
				+ " Products: " + order.getProducts().toString();
		return detail;
	}

	public List<Order> findWithUserId(Order order) {
		return orderMapper.findWithUserId(order);
	}

	public Order findwithUserAndProductId(Order order) {
		return orderMapper.findwithUserAndProductId(order);
	}

	public float getTotalPrice(ArrayList<Product> products) {
		float total = 0;
		for (Product product : products) {
			total += product.getPrice();
		}

		return total;
	}

	public Order findwithOrderId(Order order) {
		return orderMapper.findWithOrderId(order);
	}

	public List<Order> findwithProductId(Product product) {
		return orderMapper.findWithProductId(product);
	}

}
