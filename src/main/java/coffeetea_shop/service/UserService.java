package coffeetea_shop.service;

// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import coffeetea_shop.repository.*;
import lombok.AllArgsConstructor;
import coffeetea_shop.model.*;

@Service
@AllArgsConstructor
public class UserService {

  private UserRepository userRepository;

  public void addUser(User user) {
    user.setStatus("unactive");
    userRepository.save(user);
  }

  public User getUserByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }

  public void updateUser(User user) {
    userRepository.save(user);
  }

  public void deleteUser(User user) {
    userRepository.delete(user);
  }
}
