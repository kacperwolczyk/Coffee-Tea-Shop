package coffeetea_shop.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Product product;
	
	private int productCount;
	
	private int totalprice;
	
	@ManyToOne
	@JoinColumn(name="id_order")
	private Order order;
	
	// IT NECESSERY TO SAVE INFORMATION ABOUT ACTUALL PRICE, BECAUSE
	// PRODUCT PRICE CAN CHANGE`-> THEN WILL CHANGE ALSO IN OUR ORDER
	
	private int buyPrice;

}
