package pl.maciejdluzen.hotelreservation.domain.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@DiscriminatorValue("A")
@Table(name = "ADMINS")
public class Admin extends User {


}
