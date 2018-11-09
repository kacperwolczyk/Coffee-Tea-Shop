package coffeetea_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffeetea_shop.model.User;
import coffeetea_shop.model.UserDetails;
import coffeetea_shop.repository.UserDetailsRepository;

@Service
public class UserDetailsService {

	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	public void setUserDetailsRepository (UserDetailsRepository userDetailsRepository)
	{
		this.userDetailsRepository = userDetailsRepository;
	}
	
	public UserDetails getUserDetailsByUser(User user)
	{
		return userDetailsRepository.getUserDetailsByUser(user);
	}
	
	public void updateUserDetails(UserDetails userDetails)
	{
		userDetailsRepository.save(userDetails);
	}
}
