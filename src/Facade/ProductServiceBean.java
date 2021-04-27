package Facade;

import domain.Product;
import domain.ProductAssembler;
import domain.ProductDTO;
import mapper.ProductMapper;

public class ProductServiceBean {

	public ProductDTO getProduct(long id) {
		Product product = new Product();
		product.setProductId(id);
		return ProductAssembler.writeDTO(ProductMapper.findProductById(product));
	}

	public String getProductString(long id) {
		return getProduct(id).toString();
	}

}
