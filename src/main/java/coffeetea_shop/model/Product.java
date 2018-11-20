package coffeetea_shop.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private Long id;
	@NotEmpty(message = "{coffeetea_shop.model.Product.category.NotEmpty}")
	private String category;
	@NotEmpty(message = "{coffeetea_shop.model.Product.name.NotEmpty}")
	private String name;
	@NotNull(message = "{coffeetea_shop.model.Product.weight.NotNull}")
	private int weight;
	@NotNull(message = "{coffeetea_shop.model.Product.count.NotNull}")
	private int count;
	@NotNull(message = "{coffeetea_shop.model.Product.price.NotNull}")
	private int price;
	@NotEmpty
	@Size(min = 0, max = 1000)
	private String description;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + weight;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	
	
}


