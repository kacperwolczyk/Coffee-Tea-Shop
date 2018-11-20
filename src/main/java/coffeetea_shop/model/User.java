package coffeetea_shop.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

import coffeetea_shop.model.UserRole;
import lombok.*;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	
	@NotEmpty(message = "{coffeetea_shop.model.User.username.NotEmpty}")
	@Column(unique = true)
	private String username;
	
	@NotEmpty(message = "{coffeetea_shop.model.User.password.NotEmpty}")
	private String password;
	
	@NotEmpty(message = "{coffeetea_shop.model.User.email.NotEmpty}")
	@Email(message = "{coffeetea_shop.model.User.email.Email}")
	@Column(unique = true)
	private String email;
	
	@NotEmpty(message = "{coffeetea_shop.model.User.phone.NotEmpty}")
	private String phone;
	
	private String status;
	
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_details")
    private UserDetails details;
    
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	Set<UserRole> roles = new HashSet<>();
}