package coffeetea_shop.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import coffeetea_shop.model.User;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Long id;
	private LocalDate orderDate;
	private LocalDate sendDate;
	@NotEmpty
	private String status;
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	@OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;
	private int totalPrice;
    
    
}