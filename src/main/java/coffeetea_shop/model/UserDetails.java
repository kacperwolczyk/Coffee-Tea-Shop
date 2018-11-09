package coffeetea_shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.*;


@Entity
@Getter
@Setter
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="details_id")
	private Long id;

	private String firstName;

	private String lastName;

	private String country;

	private String city;

	private String code;
	
	private String street;
	
	private int number;
    @OneToOne(mappedBy = "details")
    private User user;
}
