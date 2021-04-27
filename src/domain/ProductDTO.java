package domain;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private long productId;
	private String name;
	private String category;
	private String description;
	private float price;
	private ArrayList<Float> rating;

	public long getId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ArrayList<Float> getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating.add(rating);
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);

	}

	public static ProductDTO readString(String s) {
		Gson gson = new Gson();
		return gson.fromJson(s, ProductDTO.class);
	}

}
