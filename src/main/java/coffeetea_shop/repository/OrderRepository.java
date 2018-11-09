package coffeetea_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import coffeetea_shop.model.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	Order getOrderById(Long id);
	List<Order> getOrdersByUser(User user);
	
}
