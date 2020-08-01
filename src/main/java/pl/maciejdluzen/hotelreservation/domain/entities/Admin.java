package pl.maciejdluzen.hotelreservation.domain.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode

@Entity
@DiscriminatorValue("A")
@Table(name = "admins")
public class Admin extends User {


}
