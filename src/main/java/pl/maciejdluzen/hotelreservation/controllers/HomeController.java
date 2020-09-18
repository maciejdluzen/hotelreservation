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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
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
    public String login(Model model,
                        HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        List<String> sessionNames = Collections.list(session.getAttributeNames());
        LOG.info("Session value: {}, creation time {}, attribute size {}, attributes {}", session.getId(), session.getCreationTime(), sessionNames.size(), sessionNames);

        if(!(sessionNames.contains("SPRING_SECURITY_LAST_EXCEPTION"))) {
            ReservationDto reservation = (ReservationDto) session.getAttribute("reservationDto");
            LOG.info("Session parameters: {}, {}", reservation.getHotelName(), reservation.getCheckInDate());
            if(reservation.getCheckInDate() == null &&
                    reservation.getCheckOutDate() == null &&
                    reservation.getHotelName() == null ) {
                session.invalidate();
                LOG.info("Session invalidated");
            } else {
                model.addAttribute("reservationDto", reservation);
                LOG.info("Session still valid");
            }
        }
        return "login";
    }
}
