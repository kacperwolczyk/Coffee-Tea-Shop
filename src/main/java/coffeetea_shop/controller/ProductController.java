package coffeetea_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import coffeetea_shop.shoppingcart.ShoppingCart;
import coffeetea_shop.service.*;


@Controller
public class ProductController {

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
	
	@GetMapping("/list")
	public String productList(Model model)
	{
		model.addAttribute("productList", productService.getProducts());
		model.addAttribute("shoppingcart", shoppingCart.getProducts());
		model.addAttribute("shoppingcartvalue", shoppingCart.getTotalPrice());
		return "productList";
	}
	
	@GetMapping("/list/{name}")
	public String productDetails(@PathVariable String name, Model model)
	{
		model.addAttribute("product", productService.getProductByName(name));
		model.addAttribute("shoppingcart", shoppingCart.getProducts());
		model.addAttribute("shoppingcartvalue", shoppingCart.getTotalPrice());
		return "productDetails";
	}
}
