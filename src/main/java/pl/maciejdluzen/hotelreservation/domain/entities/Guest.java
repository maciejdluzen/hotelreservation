package pl.maciejdluzen.hotelreservation.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode

@Entity
@DiscriminatorValue("G")
@Table(name = "guests")
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

}
