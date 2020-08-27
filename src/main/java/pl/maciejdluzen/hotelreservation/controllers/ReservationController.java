package pl.maciejdluzen.hotelreservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping
public class ReservationController {


    @GetMapping("/reservation/roomselection")
    public String getRoomSelectionPage() {
        return "reservation/roomselection";
    }

    @GetMapping("/auth/guest/reservation/details")
    public String getReservationDetailsPage() {
        return "reservation/details";
    }

    @GetMapping("/auth/guest/reservation/summary")
    public String getReservationSummary() {
        return "reservation/summary";
    }




}
