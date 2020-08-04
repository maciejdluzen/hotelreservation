package pl.maciejdluzen.hotelreservation.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@ToString

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(name = "email_address", unique = true)
    private String emailAddress;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private Boolean active;

    @ManyToOne
    private Role role; // Uni-directional relationship

//    public void setEmailAddress() {
//        this.emailAddress = getUsername();
//    }
}
