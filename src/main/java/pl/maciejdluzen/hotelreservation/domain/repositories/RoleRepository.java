package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maciejdluzen.hotelreservation.domain.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByName(String name);

}
