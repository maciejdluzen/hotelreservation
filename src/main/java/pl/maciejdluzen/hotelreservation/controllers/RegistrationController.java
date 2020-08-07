package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.maciejdluzen.hotelreservation.dtos.GuestDto;
import pl.maciejdluzen.hotelreservation.exceptions.UserAlreadyExistException;
import pl.maciejdluzen.hotelreservation.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/guest")
    public String showGuestRegistrationForm(Model model) {
        model.addAttribute("guest", new GuestDto());
        LOG.info("RegistrationController.class: showing the registration form");
        return "registration";
    }

    @PostMapping("/guest")
    public String registerNewGuestAccount(@ModelAttribute("guest")
                    @Valid GuestDto guestDto, BindingResult results,
                                          HttpServletRequest request) {
        if(results.hasErrors()) {
            return "registration";
        }
        try {
           userService.registerNewGuestAccount(guestDto);
           LOG.info("RegistrationController.class: Guest from the form submission: {}", guestDto.getEmailAddress());
        } catch (UserAlreadyExistException exc) {
            exc.printStackTrace();
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmGuestAccount(@RequestParam("token") String verificationToken) {

        LOG.info("RegistrationController.class: confirmGuestAccount.method: Verification Token from the " +
                " URL parameter: {}", verificationToken);

        if(verificationToken != null) {
            userService.confirmGuestAccount(verificationToken);
            return "redirect:/login";
        } else {
            return "badUser";
        }
    }
}
