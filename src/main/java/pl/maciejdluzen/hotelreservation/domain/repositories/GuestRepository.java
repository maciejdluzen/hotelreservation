package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findByEmailAddressIgnoreCase(String emailAddress);

}
