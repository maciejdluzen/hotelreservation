package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findByEmailAddressIgnoreCase(String emailAddress);

    @Query("SELECT g.id, g.username, g.emailAddress, g.firstName, g.lastName, g.active FROM Guest g ORDER BY g.lastName ASC")
    List<Guest> getAllHotelsWithoutDetails();

    List<Guest> findAllByOrderByLastName();

}
