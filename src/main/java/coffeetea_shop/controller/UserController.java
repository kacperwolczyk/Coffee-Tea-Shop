package coffeetea_shop.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coffeetea_shop.model.*;
import coffeetea_shop.service.*;



@Controller
public class UserController {

	private UserService userService;
	private UserDetailsService userDetailsService;
	private OrderService orderService;
	private ProductService productService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/register")
	public String register(Model model, Principal principal) {
		if(principal!=null)
			return "redirect:userpanel";
		model.addAttribute("user", new User());
		return "registerForm";
	}
	
	@GetMapping("/userpanel")
	public String userpanel(Model model, Principal principal) {
		User user = userService.getUserByUsername(principal.getName());
		model.addAttribute("user", user);
		if("unactive".equals(user.getStatus()))
		{
			model.addAttribute("userDetails", new UserDetails());
			return "afterFirstLogin";
		}
		else
		{
			model.addAttribute("userDetails", userDetailsService.getUserDetailsByUser(user));
			model.addAttribute("userorders", orderService.getOrderByUser(user));
			return "userPanel";
		}
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
				redirectAttributes.addFlashAttribute("invalidMessage", "This email or username is already used. Try again");
				return "redirect:/register";
			}
			redirectAttributes.addFlashAttribute("correctMessage", "You have been successfully registered!");
			return "redirect:/register";
		}
		
	}
	
	@GetMapping("/adminpanel")
	public String adminPanel(Model model)
	{
		model.addAttribute("products", productService.getProducts());
		model.addAttribute("orders", orderService.getAllOrders());
		return "adminPanel";
	}
}
