package pl.maciejdluzen.hotelreservation.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@ToString

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Guest.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Guest guest;

    private Date expiryDate;

    public VerificationToken(Guest guest) {
        this.guest = guest;
        createdDate = new Date();
        token = UUID.randomUUID().toString();
    }
}
