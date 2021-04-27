package domain;

public class ProductAssembler {

	public static ProductDTO writeDTO(Product product) {
		ProductDTO result = new ProductDTO();
		result.setCategory(product.getCategory());
		result.setDescription(product.getDescription());
		result.setName(product.getName());
		result.setPrice(product.getPrice());
		result.setProductId(product.getId());
		for (float i : product.getRating()) {
			result.setRating(i);
		}

		return result;
	}

}
