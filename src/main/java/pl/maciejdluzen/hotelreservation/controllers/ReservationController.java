package pl.maciejdluzen.hotelreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {


    @GetMapping("/roomselection")
    public String getRoomSelectionPage() {
        return "reservation/roomselection";
    }

}
