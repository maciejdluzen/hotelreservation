package pl.maciejdluzen.hotelreservation.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"reservations", "rooms", "receptionists"})

@Entity
@Table(name = "HOTELS")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String city;
    @Column(name = "post_code", nullable = false)
    private String postCode;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE)
    private Set<Reservation> reservations = new HashSet<>(); // Bi-directional relationship

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE)
    private Set<Room> rooms = new HashSet<>(); // Uni-directional relationship

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE)
    private Set<Receptionist> receptionists = new HashSet<>(); // Bi-directional relationship

}
