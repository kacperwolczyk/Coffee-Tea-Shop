package coffeetea_shop.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
	@OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;
	
	private int totalPrice;
	
	// IT IS NECESSERY TO HARD DEFINE ADDRESS IN ORDER / CAN'T MAP TO UserDetails BECAUSE USER
	// CAN CHANGE HIS ADDRESS, AND THEN ORDER ADDRESS WILL CHANGE TOO.
	// COULD ALSO CREATE ORDER DETAILS CLASS WITH ONE-TO-ONE RELATION TO ORDER
	
	@NotEmpty
	private String country;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String code;
	
	@NotEmpty
	private String street;
	
	@NotNull
	private int number;
    
}