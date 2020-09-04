package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maciejdluzen.hotelreservation.domain.entities.Receptionist;

import java.util.List;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {

    List<Receptionist> findAllByOrderByLastName();

    Receptionist findByUsername(String username);

}
