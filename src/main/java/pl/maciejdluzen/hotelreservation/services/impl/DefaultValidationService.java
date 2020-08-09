package pl.maciejdluzen.hotelreservation.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.repositories.UserRepository;
import pl.maciejdluzen.hotelreservation.services.ValidationService;

@Service
@Slf4j
public class DefaultValidationService implements ValidationService {

    private final UserRepository userRepository;

    public DefaultValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isUniqueEmailAddress(String emailAddress) {
        return !userRepository.existsByEmailAddress(emailAddress);
    }


}
