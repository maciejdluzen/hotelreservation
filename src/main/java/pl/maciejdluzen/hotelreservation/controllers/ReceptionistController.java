package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejdluzen.hotelreservation.services.ReceptionistService;
import pl.maciejdluzen.hotelreservation.services.ReservationService;

import java.security.Principal;

@Controller
@RequestMapping("/auth/receptionist")
public class ReceptionistController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final ReceptionistService receptionistService;
    private final ReservationService reservationService;

    public ReceptionistController(ReceptionistService receptionistService, ReservationService reservationService) {
        this.receptionistService = receptionistService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReceptionistDashboard(Model model, Principal principal) {

        return "receptionist/dashboard";
    }




}
