package coffeetea_shop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import coffeetea_shop.shoppingcart.ShoppingCart;
import coffeetea_shop.model.*;
import coffeetea_shop.service.*;


@Controller
public class OrderController {

	private ShoppingCart shoppingCart;
	private OrderService orderService;
	private UserService userService;
	private OrderItemService orderItemService;
	
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
	
	@PostMapping("/pay")
	public String checkOut(Principal principal)
	{
		
		User user = userService.getUserByUsername(principal.getName());
		userService.addUser(user);
		Order order = orderService.addOrder(user);
		for(Product product : shoppingCart.getProducts().keySet())
		{
			orderItemService.addOrderItem(product, order, shoppingCart.getProducts().get(product));
		}
		order.setTotalPrice(shoppingCart.getTotalPrice());
		orderService.updateOrder(order);
		shoppingCart.resetCard();
		return "redirect:/list/";
	}
}
