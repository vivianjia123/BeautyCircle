package domain;

import java.util.ArrayList;

public class Product extends DomainObject {

	private long productId;
	private String name;
	private String category;
	private String description;
	private float price;
	private ArrayList<Float> rating;

	private static long universalId = 15;

	public Product(long productId, String name, String category, String description, float price,
			ArrayList<Float> rating) {
		this.productId = productId;
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
		this.rating = rating;
	}

	public static long getUniversalId() {
		return Product.universalId;
	}

	public static void increaseUniversalId() {
		Product.universalId++;
	}

	public Product(long productId) {
		this.productId = productId;
	}

	public Product(String name) {
		this.productId = 0;
		this.name = name;
	}

	public Product() {
	}

	@Override
	public long getId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
		this.markDirty(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.markDirty(this);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
		this.markDirty(this);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.markDirty(this);
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
		this.markDirty(this);
	}

	public ArrayList<Float> getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating.add(rating);
		this.markDirty(this);
	}

}
