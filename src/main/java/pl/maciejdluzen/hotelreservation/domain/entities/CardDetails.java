package pl.maciejdluzen.hotelreservation.domain.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString

@Entity
@Table(name = "card_details")
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "name_on_the_card")
    private String nameOnTheCard;
    @Column(name = "card_number")
    private Integer cardNumber;
    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;
    @Column(name = "security_code")
    private Integer securityCode;

}
