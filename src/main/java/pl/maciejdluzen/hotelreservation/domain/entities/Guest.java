package pl.maciejdluzen.hotelreservation.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "reservations")

@Entity
@DiscriminatorValue("G")
@Table(name = "GUESTS")
public class Guest extends User {

    @Column(nullable = false)
    private String street;
    @Column(name = "home_number", nullable = false)
    private String homeNumber;
    @Column(nullable = false)
    private String city;
    @Column(name = "post_code", nullable = false)
    private String postCode;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.REMOVE)
    private Set<Reservation> reservations = new HashSet<>(); // Bi-directional relationship

}
