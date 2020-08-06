package pl.maciejdluzen.hotelreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;
import pl.maciejdluzen.hotelreservation.domain.entities.User;
import pl.maciejdluzen.hotelreservation.domain.repositories.UserRepository;
import pl.maciejdluzen.hotelreservation.dtos.GuestDto;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;


public interface UserService {

    Optional<User> findUserByEmailAddress(String emailAddress);

    void registerNewGuestAccount(@Valid GuestDto guestDto);

    void confirmGuestAccount(String verificationToken);
}
