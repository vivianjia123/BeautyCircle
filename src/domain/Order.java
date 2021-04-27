package domain;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order extends DomainObject {

	public enum Status {
		PENDING, PROCESSING, DELIVERING, COMPLETE, CANCELLED;

	}

	private long orderid;
	private static long universalId = 1;
	private RegisteredUser user;
	private float totalPrice;
	private Timestamp timeCreated;
	private Status status;
	private ArrayList<Product> products = new ArrayList<>();

	public Order(float totalPrice, RegisteredUser user, ArrayList<Product> products) {
		this.orderid = universalId;
		universalId++;
		this.totalPrice = totalPrice;
		this.user = user;
		this.timeCreated = new Timestamp(System.currentTimeMillis());
		this.status = Status.PENDING;
		this.products = products;
	}

	public Order(long orderid, float totalPrice, RegisteredUser user, Timestamp orderTime, Status orderStatus,
			ArrayList<Product> products) {
		user.getId();
		this.orderid = orderid;
		this.totalPrice = totalPrice;
		this.user = user;
		this.timeCreated = orderTime;
		this.status = orderStatus;
		this.products = products;
	}

	public Order(RegisteredUser user) {
		this.user = user;
	}

	public Order(Long orderId) {
		this.orderid = orderId;
	}

	public Order() {
	}

	@Override
	public long getId() {
		return this.orderid;
	}

	public void setOrderId(long orderId) {
		this.orderid = orderId;
	}

	public RegisteredUser getUser() {
		return this.user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}

	public float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Timestamp getTimeCreated() {
		return this.timeCreated;
	}

	public void setTimeCreated(Timestamp timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		if (status.equals("PENDING")) {
			this.status = Status.PENDING;
		} else if (status.equals("PROCESSING")) {
			this.status = Status.PROCESSING;
		} else if (status.equals("DELIVERING")) {
			this.status = Status.DELIVERING;
		} else if (status.equals("COMPLETE")) {
			this.status = Status.COMPLETE;
		} else if (status.equals("CANCELLED")) {
			this.status = Status.CANCELLED;
		}
	}

	public ArrayList<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Product product) {
		this.products.add(product);
	}

	public void removeProducts(Product product) {
		this.products.remove(product);
	}

}
