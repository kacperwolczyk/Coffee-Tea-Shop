package coffeetea_shop.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import coffeetea_shop.model.*;
import coffeetea_shop.service.*;

@Controller
public class UserDetailsController {

	private UserService userService;
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@PostMapping("/userdetails")
	public String addUser(@ModelAttribute @Valid UserDetails userDetails,
			BindingResult bindResult, 
			Principal principal) {
		if(bindResult.hasErrors())
		{
			return "afterFirstLogin";
		}		

		User user = userService.getUserByUsername(principal.getName());
		userDetailsService.addUserDetails(userDetails);
		user.setDetails(userDetails);
		user.setStatus("active");
		userService.updateUser(user);
		return "redirect:/userpanel";
	}
}
