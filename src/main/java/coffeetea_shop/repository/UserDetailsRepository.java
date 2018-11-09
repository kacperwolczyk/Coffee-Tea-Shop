package coffeetea_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import coffeetea_shop.model.*;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

	UserDetails getUserDetailsByUser(User user);
}
