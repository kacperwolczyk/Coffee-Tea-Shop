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
	private int buyPrice;
	private int totalprice;
	@ManyToOne
	@JoinColumn(name="id_order")
	private Order order;
}
