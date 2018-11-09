package coffeetea_shop.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffeetea_shop.model.*;
import coffeetea_shop.repository.*;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	
	@Autowired
	public void setOrderRepository(OrderRepository orderRepository)
	{
		this.orderRepository = orderRepository;
	}
	
	public Order addOrder(User user)
	{
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setStatus("waiting");
		order.setUser(user);
		return orderRepository.save(order);
	}
	
	public void updateOrder(Order order)
	{
		orderRepository.save(order);
	}
	
	public Order getOrderById(Long id)
	{
		return orderRepository.getOrderById(id);
	}
	
	public List<Order> getOrderByUser(User user)
	{
		return orderRepository.getOrdersByUser(user);
	}
	
	public void deleteOrder(Order order)
	{
		orderRepository.delete(order);
	}
}
