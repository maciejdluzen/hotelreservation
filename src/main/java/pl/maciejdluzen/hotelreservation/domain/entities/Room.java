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
@ToString(exclude = "reservations")

@Entity
@Table(name = "ROOMS")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;
    @Column(name = "floor_number", nullable = false)
    private Integer floorNumber;

    @OneToMany(mappedBy = "room")
    private Set<Reservation> reservations = new HashSet<>(); // Bi-directional relationship

    @OneToOne
    private RoomType roomType; // Uni-directional relationship

}
