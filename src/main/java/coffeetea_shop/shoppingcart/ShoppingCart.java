package coffeetea_shop.shoppingcart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import coffeetea_shop.model.*;

@Component
@Scope(scopeName=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private Map<Product, Integer> productmap = new HashMap<>();
    private int totalprice = 0;
    
    
    public Map<Product, Integer> getProducts()
    {
    	return productmap;
    }
    
    public void addProduct(Product product, int quantity)
    {
    	if(productmap.containsKey(product))
    	{
    		int productCount = productmap.get(product);
    		int newProductCount = productCount+quantity;
    		productmap.replace(product, newProductCount);
    		totalprice = totalprice + (newProductCount - productCount) * product.getPrice();
    	}
    	else
    	{
    		productmap.put(product, quantity);
    		totalprice = totalprice + quantity * product.getPrice();
    	}
    }
    
    
    public void removeProduct(Product product)
    {
		int productCount = productmap.get(product);
		int newProductCount = productCount-1;
		if(productCount > 0)
		{
			productmap.replace(product, newProductCount);
			totalprice = totalprice - product.getPrice();
			if(productCount==1)
				productmap.remove(product);
		}
		else
		{
			productmap.remove(product);
		}
	}
    
    public int getTotalPrice()
    {
    	return totalprice;
    }
    
    public void resetCard()
    {
    	productmap.clear();
    	totalprice = 0;
    }
}
