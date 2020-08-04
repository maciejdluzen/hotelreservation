package pl.maciejdluzen.hotelreservation.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString

@Entity
@DiscriminatorValue("R")
@Table(name = "receptionists")
public class Receptionist extends User {

    @ManyToOne
    private Hotel hotel; // Bi-directional relationship

}
