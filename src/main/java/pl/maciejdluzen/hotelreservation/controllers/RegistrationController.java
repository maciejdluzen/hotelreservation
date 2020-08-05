package pl.maciejdluzen.hotelreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejdluzen.hotelreservation.dtos.GuestDto;

@Controller
@RequestMapping("/register")
public class RegistrationController {


    @GetMapping("/guest")
    public String showGuestRegistrationForm(Model model) {
        model.addAttribute("guest", new GuestDto());
        return "registration";
    }







}
