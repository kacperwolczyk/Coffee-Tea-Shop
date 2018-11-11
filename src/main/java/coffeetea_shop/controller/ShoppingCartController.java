package coffeetea_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coffeetea_shop.shoppingcart.ShoppingCart;
import coffeetea_shop.model.*;
import coffeetea_shop.service.*;


@Controller
public class ShoppingCartController {

	private ProductService productService;
	private ShoppingCart shoppingCart;
	
	@Autowired
	public void setProductService(ProductService productService)
	{
		this.productService = productService;
	}
	
	@Autowired
	public void setShoppingCart(ShoppingCart shoppingCart)
	{
		this.shoppingCart = shoppingCart;
	}
	
	@PostMapping("/addtocart")
	public String addToCart(@RequestParam Long id, @RequestParam int quantity)
	{
		Product product = productService.getProductById(id);
		String name = product.getName();
		shoppingCart.addProduct(product, quantity);
		return"redirect:/list/"+name;
	}
	
	@PostMapping("/removefromcart")
	public String removeFromCart(@RequestParam Long id)
	{
		Product product = productService.getProductById(id);
		String name = product.getName();
		shoppingCart.removeProduct(product);
		return"redirect:/list/"+name;
	}
}
