package pl.maciejdluzen.hotelreservation.services;


import org.springframework.mail.SimpleMailMessage;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;
import pl.maciejdluzen.hotelreservation.domain.entities.VerificationToken;

public interface EmailSenderService {

    void sendEmail(SimpleMailMessage mailMessage);

}
