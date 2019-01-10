package coffeetea_shop.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import coffeetea_shop.model.*;
import coffeetea_shop.repository.*;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderItemService {

  private OrderItemRepository orderItemRepository;

  public void addOrderItem(Product product, Order order, int count) {
    OrderItem orderItem = new OrderItem();
    orderItem.setProduct(product);
    orderItem.setOrder(order);
    orderItem.setBuyPrice(product.getPrice());
    orderItem.setProductCount(count);
    orderItem.setTotalprice(orderItem.getBuyPrice() * orderItem.getProductCount());
    orderItemRepository.save(orderItem);
  }

  public Set<OrderItem> getOrderItemsByOrder(Order order) {
    return orderItemRepository.getItemsByOrder(order);
  }
}
