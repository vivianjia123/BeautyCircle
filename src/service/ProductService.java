package service;

import java.util.List;

import domain.Product;
import mapper.ProductMapper;
import utility.UnitOfWork;

public class ProductService {

	private ProductMapper productMapper = new ProductMapper();

	public String viewProductDetail(Product product) {
		String detail = "Name: " + product.getName() + " Price: " + product.getPrice() + " Category: "
				+ product.getCategory() + " Description: " + product.getDescription();
		return detail;
	}

	public String getProductName(Product product) {
		return product.getName();
	}

	public float getProductPrice(Product product) {
		return product.getPrice();
	}

	public String getProductCategory(Product product) {
		return product.getCategory();
	}

	public String getProductDescription(Product product) {
		return product.getDescription();
	}

	public float getAverageRating(Product product) {
		float totalRating = 0;

		for (int i = 0; i < product.getRating().size(); i++) {
			totalRating += product.getRating().get(i);
		}
		totalRating = totalRating / product.getRating().size();
		return Math.round(totalRating);
	}

	public Product searchProductById(Product product) {
		return ProductMapper.findProductById(product);
	}

	public List<Product> searchProductByName(Product product) {
		return productMapper.findProductByName(product);
	}

	public List<Product> showAllAvailableProducts() {
		return productMapper.findAllProducts();

	}

	public void createProduct(Product product) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerNew(product);
		UnitOfWork.getCurrent().commit();
	}

	public void deleteProductbyId(Product product) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDeleted(product);
		UnitOfWork.getCurrent().commit();
	}

	public void updateProduct(Product product) {
		UnitOfWork.newCurrent();
		UnitOfWork.getCurrent().registerDirty(product);
		UnitOfWork.getCurrent().commit();
	}

}
