package pl.maciejdluzen.hotelreservation.services.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.repositories.UserRepository;
import pl.maciejdluzen.hotelreservation.services.ValidationService;

@Service
@Slf4j
@AllArgsConstructor
public class DefaultValidationService implements ValidationService {

    private final UserRepository userRepository;

    @Override
    public boolean isUniqueEmailAddress(String emailAddress) {
        return !userRepository.existsByEmailAddress(emailAddress);
    }


}
