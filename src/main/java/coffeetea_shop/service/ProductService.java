package coffeetea_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffeetea_shop.model.Product;
import coffeetea_shop.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}
	
	public Product addProduct(Product product)
	{
		return productRepository.save(product);
	}
	
	public Product getProductById(Long id)
	{
		return productRepository.getProductById(id);
	}
	
	public List<Product> getProductsByCategory(String category)
	{
		return productRepository.getProductsByCategory(category);
	}
	
	public Product getProductByNameAndCategoryAndWeight(String name, String category, int weight)
	{
		return productRepository.getProductByNameAndCategoryAndWeight(name, category, weight);
	}
	
	public List<Product> getProducts()
	{
		return productRepository.findAll();
	}
	
	public void updateProduct(Product product)
	{
		productRepository.save(product);
	}
	
	public void deleteProduct(Product product)
	{
		productRepository.delete(product);
	}
}
