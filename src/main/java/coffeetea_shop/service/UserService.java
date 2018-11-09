package coffeetea_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import coffeetea_shop.repository.*;
import coffeetea_shop.model.*;


@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	public void addUser(User user)
	{
		user.setStatus("unactive");
		userRepository.save(user);
	}
	
	public User getUserByUsername(String username)
	{
		return userRepository.findUserByUsername(username);
	}
	
	public void updateUser(User user)
	{
		userRepository.save(user);
	}
	
	public void deleteUser(User user)
	{
		userRepository.delete(user);
	}
}
