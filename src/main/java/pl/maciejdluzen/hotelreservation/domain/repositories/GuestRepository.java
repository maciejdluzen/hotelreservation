package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;
import pl.maciejdluzen.hotelreservation.domain.projections.FullName;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findByEmailAddressIgnoreCase(String emailAddress);

    @Query("SELECT g.id, g.username, g.emailAddress, g.firstName, g.lastName, g.active FROM Guest g ORDER BY g.lastName ASC")
    List<Guest> getAllHotelsWithoutDetails();

    List<Guest> findAllByOrderByLastName();

    //@Query("SELECT g.firstName, g.lastName FROM Guest g WHERE g.username = :username")
    @Query(value = "SELECT first_name, last_name FROM guests WHERE username = ?1", nativeQuery = true)
    FullName findByUsernameIgnoreCase(@Param("username") String username); // Why this one is not working??

    Guest findByUsername(String username);
}
