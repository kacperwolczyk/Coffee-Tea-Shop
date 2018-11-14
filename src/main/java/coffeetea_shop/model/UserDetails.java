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
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
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
    @OneToOne(mappedBy = "details")
    private User user;
}
