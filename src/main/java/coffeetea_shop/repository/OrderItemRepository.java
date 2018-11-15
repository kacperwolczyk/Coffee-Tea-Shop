package coffeetea_shop.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import coffeetea_shop.model.*;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	Set<OrderItem> getItemsByOrder(Order order);
}
