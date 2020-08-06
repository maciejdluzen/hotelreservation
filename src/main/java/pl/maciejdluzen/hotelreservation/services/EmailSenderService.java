package pl.maciejdluzen.hotelreservation.services;


import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {

    void sendEmail(SimpleMailMessage email);

}
