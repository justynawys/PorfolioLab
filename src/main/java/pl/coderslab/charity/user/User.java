package pl.coderslab.charity.user;

import lombok.Data;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.user.role.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String lastname;
    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToMany
    private List<Donation> donations;
}
