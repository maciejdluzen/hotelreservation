package pl.maciejdluzen.hotelreservation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.services.EmailSenderService;

@Service
public class DefaultEmailSenderService implements EmailSenderService {

    private JavaMailSender javaMailSender;

    @Autowired
    public DefaultEmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }
}
