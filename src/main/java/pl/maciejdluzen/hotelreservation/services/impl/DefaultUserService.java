package pl.maciejdluzen.hotelreservation.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;
import pl.maciejdluzen.hotelreservation.domain.entities.Role;
import pl.maciejdluzen.hotelreservation.domain.entities.User;
import pl.maciejdluzen.hotelreservation.domain.repositories.GuestRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoleRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.UserRepository;
import pl.maciejdluzen.hotelreservation.dtos.GuestDto;
import pl.maciejdluzen.hotelreservation.exceptions.UserAlreadyExistException;
import pl.maciejdluzen.hotelreservation.services.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Service
@Slf4j
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUserService(UserRepository userRepository, RoleRepository roleRepository, GuestRepository guestRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public Optional<User> findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }

    @Transactional
    @Override
    public void registerNewGuestAccount(@Valid GuestDto guestDto)
        throws UserAlreadyExistException {

        if(userRepository.existsByEmailAddress(guestDto.getEmailAddress())) {
            throw new UserAlreadyExistException(guestDto.getEmailAddress());
        }

        String encodedPassword = passwordEncoder.encode(guestDto.getPassword());

        Guest guest = new Guest();
        log.info("DefaultUserService.class: Saved guest: {}", guestDto.getEmailAddress());
        guest.setUsername(guestDto.getEmailAddress());
        guest.setEmailAddress(guestDto.getEmailAddress());
        guest.setPassword(encodedPassword);
        guest.setFirstName(guestDto.getFirstName());
        guest.setLastName(guestDto.getLastName());
        guest.setActive(true);
        Role roleGuest = roleRepository.getByName("ROLE_GUEST");
        guest.setRole(roleGuest);

        guest.setStreet(guestDto.getStreet());
        guest.setHomeNumber(guestDto.getHomeNumber());
        guest.setCity(guestDto.getCity());
        guest.setPostCode(guestDto.getPostCode());
        guest.setPhoneNumber(guestDto.getPhoneNumber());

        guestRepository.save(guest);

        /*
        TO-DO
        Complete this method***

         */


    }
}
