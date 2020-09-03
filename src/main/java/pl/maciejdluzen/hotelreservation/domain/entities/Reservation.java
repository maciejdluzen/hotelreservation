package pl.maciejdluzen.hotelreservation.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reservation_number", nullable = false, unique = true)
    private String reservationNumber;
    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;
    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;
    @Column(nullable = false)
    private Boolean status = Boolean.FALSE;
    @Column(name = "second_guest_name")
    private String secondGuestName;
    @Column(name = "third_guest_name")
    private String thirdGuestName;
    @Column(name = "fourth_guest_name")
    private String fourthGuestName;
    @Column(name = "total_net_cost")
    private Double totalNetCost;
    @Column
    private Double tax;
    @Column(name = "total_gross_cost")
    private Double totalGrossCost;
    @Column(name = "room_type_name")
    private String roomTypeName;
    @Column
    private String message;

    @ManyToOne
    private Guest guest; // Bi-directional relationship

    @OneToOne
    private CardDetails cardDetails; // Uni-directional relationship

    @ManyToOne
    private Hotel hotel; // Bi-directional relationship

    @ManyToOne
    private Room room; // Bi-directional relationship

}
