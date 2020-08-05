package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maciejdluzen.hotelreservation.domain.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailAddress(String emailAddress);

    boolean existsByUsername(String username);

    boolean existsByEmailAddress(String emailAddress);

}
