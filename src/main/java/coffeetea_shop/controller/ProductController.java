package coffeetea_shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coffeetea_shop.shoppingcart.ShoppingCart;
import coffeetea_shop.model.*;
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
	public String productList(Model model, @RequestParam (defaultValue = "1")int page)
	{
		model.addAttribute("productList", productService.getProductsPageable(page-1, 8));
		model.addAttribute("shoppingcart", shoppingCart.getProducts());
		model.addAttribute("shoppingcartvalue", shoppingCart.getTotalPrice());
		return "productList";
	}
	
	@GetMapping("/list/{name}_{category}")
	public String productDetails(@PathVariable String name, @PathVariable String category, Model model, @RequestParam int weight)
	{
		model.addAttribute("product", productService.getProductByNameAndCategoryAndWeight(name, category, weight));
		model.addAttribute("shoppingcart", shoppingCart.getProducts());
		model.addAttribute("shoppingcartvalue", shoppingCart.getTotalPrice());
		return "productDetails";
	}
	
	@GetMapping("/productform")
	public String productForm(Model model)
	{
		model.addAttribute("product", new Product());
		return "/productForm";
	}
	
	@PostMapping("/addproduct")
	public String addProduct(@ModelAttribute @Valid Product product, BindingResult bindResult, 
			RedirectAttributes redirectAttributes)
	{
		if(bindResult.hasErrors())
		{
			return "productForm";
		}		
		else
		{
			if(!productService.getProducts().contains(product))
			{
				productService.addProduct(product);
				redirectAttributes.addFlashAttribute("correctMessage", "Product added successfully");
			}
			else
			{
				redirectAttributes.addFlashAttribute("invalidMessage", "Product already exist");
			}
			return"redirect:/adminpanel";
		}
	}
}
