package coffeetea_shop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coffeetea_shop.shoppingcart.ShoppingCart;
import coffeetea_shop.model.*;
import coffeetea_shop.service.*;


@Controller
public class OrderController {

	private ShoppingCart shoppingCart;
	private OrderService orderService;
	private UserService userService;
	private OrderItemService orderItemService;
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void setShoppingCart(ShoppingCart shoppingCart)
	{
		this.shoppingCart = shoppingCart;
	}
	
	@Autowired
	public void setOrderService(OrderService orderService)
	{
		this.orderService = orderService;
	}
	
	@Autowired
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	@Autowired
	public void setOrderItemService(OrderItemService orderItemService)
	{
		this.orderItemService = orderItemService;
	}
	
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@GetMapping("/confirmorder")
	public String confirmOrder(Model model, Principal principal)
	{
		User user = userService.getUserByUsername(principal.getName());
		model.addAttribute("shoppingcart", shoppingCart.getProducts());
		model.addAttribute("shoppingcartvalue", shoppingCart.getTotalPrice());
		model.addAttribute("user", user);
		model.addAttribute("userdetails", userDetailsService.getUserDetailsByUser(user));
		return "confirmOrder";
	}
	
	@GetMapping("/orders")
	public String getOrderDetails(@RequestParam long orderid, Model model)
	{
		Order order = orderService.getOrderById(orderid);
		model.addAttribute("order", order);
		model.addAttribute("orderItems", orderItemService.getOrderItemsByOrder(order));
		return "/orderDetails";
	}

	@PostMapping("/createorder")
	public String checkOut(Principal principal)
	{
		
		User user = userService.getUserByUsername(principal.getName());
		Order order = orderService.addOrder(user);
		for(Product product : shoppingCart.getProducts().keySet())
		{
			orderItemService.addOrderItem(product, order, shoppingCart.getProducts().get(product));
		}
		order.setTotalPrice(shoppingCart.getTotalPrice());
		orderService.updateOrder(order);
		shoppingCart.resetCard();
		return "redirect:/userpanel";
	}
}
