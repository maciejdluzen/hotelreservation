package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDto;
import pl.maciejdluzen.hotelreservation.services.HotelService;

@Controller
//@RequestMapping("/")
@SessionAttributes("reservationDto")
public class HomeController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;

    public HomeController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

//    @GetMapping
//    public String home(Model model) {
//        model.addAttribute("hotelsNames", hotelService.findAllHotelsNames());
//        model.addAttribute("reservationDto", new ReservationDto());
//        return "index";
//    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }






}
