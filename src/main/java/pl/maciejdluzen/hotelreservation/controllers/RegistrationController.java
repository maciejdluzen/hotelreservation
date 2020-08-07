package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

           /*
            T0-DO:
            Add a page informing user that the verification email has been sent.
            */

        } catch (UserAlreadyExistException exc) {
            exc.printStackTrace();
        }
        return "redirect:/register/guest?active=false";
    }

    @RequestMapping(value = "/confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmGuestAccount(@RequestParam("token") String verificationToken) {

        LOG.info("RegistrationController.class: confirmGuestAccount.method: Verification Token from the " +
                " URL parameter: {}", verificationToken);

        if(verificationToken != null) {
            userService.confirmGuestAccount(verificationToken);

            /*
            TO-DO:
            Add a page informing about successful verification and possibility to login to the account.
             */


            return "redirect:/login?active=true";
        } else {
            return "badUser";
        }
    }
}
