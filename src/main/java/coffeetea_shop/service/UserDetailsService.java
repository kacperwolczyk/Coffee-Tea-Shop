package coffeetea_shop.service;

import org.springframework.stereotype.Service;

import coffeetea_shop.model.User;
import coffeetea_shop.model.UserDetails;
import coffeetea_shop.repository.UserDetailsRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsService {

  private UserDetailsRepository userDetailsRepository;

  public UserDetails getUserDetailsByUser(User user) {
    return userDetailsRepository.getUserDetailsByUser(user);
  }

  public void addUserDetails(UserDetails userDetails) {
    userDetailsRepository.save(userDetails);
  }

  public void updateUserDetails(UserDetails userDetails) {
    userDetailsRepository.save(userDetails);
  }
}
