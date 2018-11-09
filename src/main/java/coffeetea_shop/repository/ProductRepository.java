package coffeetea_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import coffeetea_shop.model.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> getProductsByCategory(String category);
	Product getProductByName(String name);
	Product getProductById(Long id);
}