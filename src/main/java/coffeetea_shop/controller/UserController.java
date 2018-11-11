package coffeetea_shop.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coffeetea_shop.model.User;
import coffeetea_shop.service.UserService;

@Controller
public class UserController {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String register(Model model, Principal principal) {
		if(principal!=null)
			return "redirect:userpanel";
		model.addAttribute("user", new User());
		return "registerForm";
	}

	@PostMapping("/register")
	public String addUser(@ModelAttribute @Valid User user,
			BindingResult bindResult, 
			RedirectAttributes redirectAttributes) {
		if(bindResult.hasErrors())
			return "registerForm";
		else {
			try {
				userService.addUser(user);
			} catch (DataIntegrityViolationException e) {
				return "redirect:/register";
			}
			return "redirect:/register";
		}
		
	}
}
