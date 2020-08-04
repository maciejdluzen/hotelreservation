package pl.maciejdluzen.hotelreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.User;
import pl.maciejdluzen.hotelreservation.domain.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<User> findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }


}
