package coffeetea_shop.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import coffeetea_shop.model.*;
import coffeetea_shop.repository.*;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

  private OrderRepository orderRepository;

  public Order addOrder(User user) {
    Order order = new Order();
    order.setOrderDate(LocalDate.now());
    order.setStatus("waiting");
    order.setUser(user);
    order.setCountry(user.getDetails().getCountry());
    order.setCode(user.getDetails().getCode());
    order.setCity(user.getDetails().getCity());
    order.setStreet(user.getDetails().getStreet());
    order.setNumber(user.getDetails().getNumber());

    return orderRepository.save(order);
  }

  public void updateOrder(Order order) {
    orderRepository.save(order);
  }

  public Order getOrderById(Long id) {
    return orderRepository.getOrderById(id);
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  public List<Order> getOrderByUser(User user) {
    return orderRepository.getOrdersByUser(user);
  }

  public void deleteOrder(Order order) {
    orderRepository.delete(order);
  }
}
