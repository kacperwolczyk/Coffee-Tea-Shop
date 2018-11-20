package coffeetea_shop.shoppingcart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import coffeetea_shop.model.*;
import coffeetea_shop.service.ProductService;

@Component
@Scope(scopeName=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private Map<Product, Integer> productmap = new HashMap<>();
    private int totalprice = 0;
    
    private ProductService productService;
    
    @Autowired
    public void setProductService(ProductService productService)
    {
    	this.productService = productService;
    }
    
    
    public Map<Product, Integer> getProducts()
    {
    	return productmap;
    }
    
    public boolean addProduct(Product product, int quantity)
    {
    	boolean result = false;
    	if(product.getCount() - quantity >= 0 )
    	{
    		product.setCount(product.getCount()-quantity);
    		productService.updateProduct(product);
    		result = true;
    		
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
    	
    	return result;
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
