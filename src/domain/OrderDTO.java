package domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gson.Gson;

import domain.Order.Status;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private RegisteredUserDTO user;
	private float totalPrice;
	private long orderId;
	private Timestamp timeCreated;
	private Status status;
	private ArrayList<ProductDTO> products;

	public long getId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public RegisteredUserDTO getUser() {
		return this.user;
	}

	public void setUser(RegisteredUserDTO user) {
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

	public void setStatus(Status status) {
		this.status = status;
	}

	public ArrayList<ProductDTO> getProducts() {
		return this.products;
	}

	public void setProducts(ProductDTO product) {
		this.products.add(product);
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);

	}

	public static OrderDTO readString(String s) {
		Gson gson = new Gson();
		return gson.fromJson(s, OrderDTO.class);
	}

}
