package coffeetea_shop.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.*;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	private String username;
	
	private String password;

	private String email;
	
	private String status;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_details")
    private UserDetails details;
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
}