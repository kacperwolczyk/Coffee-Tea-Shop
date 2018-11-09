package coffeetea_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import coffeetea_shop.model.*;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
