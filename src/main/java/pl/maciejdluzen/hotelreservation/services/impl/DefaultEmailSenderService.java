package pl.maciejdluzen.hotelreservation.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;
import pl.maciejdluzen.hotelreservation.domain.entities.VerificationToken;
import pl.maciejdluzen.hotelreservation.services.EmailSenderService;

@Service
@Slf4j
public class DefaultEmailSenderService implements EmailSenderService {

    private JavaMailSender javaMailSender;

    @Autowired
    public DefaultEmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {

        javaMailSender.send(mailMessage);
    }
}
