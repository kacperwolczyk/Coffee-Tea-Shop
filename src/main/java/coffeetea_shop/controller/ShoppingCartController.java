package coffeetea_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coffeetea_shop.shoppingcart.ShoppingCart;
import lombok.AllArgsConstructor;
import coffeetea_shop.model.*;
import coffeetea_shop.service.*;

@Controller
@AllArgsConstructor
public class ShoppingCartController {

  private ProductService productService;
  private ShoppingCart shoppingCart;

  @PostMapping("/addtocart")
  public String addToCart(@RequestParam Long id, @RequestParam int quantity, RedirectAttributes redirectAttributes) {
    Product product = productService.getProductById(id);
    String name = product.getName();
    if (!shoppingCart.addProduct(product, quantity))
      redirectAttributes.addFlashAttribute("noProductMessage", "No product in warehouse! Only " + product.getCount()
          + " left");
    return "redirect:/list/" + name + "_" + product.getCategory() + "?weight=" + product.getWeight();
  }

  @PostMapping("/removefromcart")
  public String removeFromCart(@RequestParam Long id) {
    Product product = productService.getProductById(id);
    product.setCount(product.getCount() + 1);
    productService.updateProduct(product);
    shoppingCart.removeProduct(product);
    return "redirect:/list";
  }
}
