package coffeetea_shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;


@Entity
@Getter
@Setter
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="details_id")
	private Long id;
	
	@NotEmpty(message = "{coffeetea_shop.model.UserDetails.firstName.NotEmpty}")
	private String firstName;
	
	@NotEmpty(message = "{coffeetea_shop.model.UserDetails.lastName.NotEmpty}")
	private String lastName;
	
	@NotEmpty(message = "{coffeetea_shop.model.UserDetails.country.NotEmpty}")
	private String country;
	
	@NotEmpty(message = "{coffeetea_shop.model.UserDetails.city.NotEmpty}")
	private String city;
	
	@NotEmpty(message = "{coffeetea_shop.model.UserDetails.code.NotEmpty}")
	private String code;
	
	@NotEmpty(message = "{coffeetea_shop.model.UserDetails.street.NotEmpty}")
	private String street;
	
	@NotNull(message = "{coffeetea_shop.model.UserDetails.number.NotNull}")
	private int number;
	
    @OneToOne(mappedBy = "details")
    private User user;
}
