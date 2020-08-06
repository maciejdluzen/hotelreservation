package pl.maciejdluzen.hotelreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejdluzen.hotelreservation.dtos.GuestDto;
import pl.maciejdluzen.hotelreservation.exceptions.UserAlreadyExistException;
import pl.maciejdluzen.hotelreservation.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/guest")
    public String showGuestRegistrationForm(Model model) {
        model.addAttribute("guest", new GuestDto());
        return "registration";
    }

    @PostMapping("/guest")
    public String registerNewGuestAccount(@ModelAttribute("guest")
                    @Valid GuestDto guestDto, BindingResult results) {
        if(results.hasErrors()) {
            return "registration";
        }
        try {
            userService.registerNewGuestAccount(guestDto);
        } catch (UserAlreadyExistException exc) {
            exc.printStackTrace();
        }
        return "redirect:/login";
    }

}
