package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDto;
import pl.maciejdluzen.hotelreservation.services.GuestService;
import pl.maciejdluzen.hotelreservation.services.HotelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/")
@SessionAttributes("reservationDto")
public class ReservationController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;
    private final GuestService guestService;

    public ReservationController(HotelService hotelService, GuestService guestService) {
        this.hotelService = hotelService;
        this.guestService = guestService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("hotelsNames", hotelService.findAllHotelsNames());
        model.addAttribute("reservationDto", new ReservationDto());
        return "index";
    }

    @PostMapping("reservation/roomselection")
    public String getRoomSelectionPage(@ModelAttribute("reservationDto") ReservationDto reservationDto,
                                       HttpSession session,
                                       Model model) {
        ReservationDto reservation = (ReservationDto) session.getAttribute("reservationDto");
        LOG.info("Hotel Name form the session: {} and session id {} and creation time: {}, checkin {} ", reservation.getHotelName(), session.getId(), session.getCreationTime(), reservation.getCheckInDate());
        model.addAttribute("reservationDto", reservation);
        return "reservation/roomselection";
    }

    @GetMapping("auth/guest/reservation/details")
    public String getReservationDetailsPage(@ModelAttribute("reservationDto") ReservationDto reservationDto,
                                            HttpSession session,
                                            HttpServletRequest request,
                                            Principal principal,
                                            Model model) {
        String param = request.getParameter("roomType");
        ReservationDto reservation = (ReservationDto) session.getAttribute("reservationDto");
        reservation.setRoomTypeName(param);
        reservation.setUsername(principal.getName());
        LOG.info("Username: {}", reservation.getUsername());
        reservation.setGuestName(guestService.findGuestNameByUsername(reservation.getUsername()));
        model.addAttribute("reservationDto", reservation);
        LOG.info("Hotel Name form the session: {}, roomType {} and session id {} and creation time: {}, checkin {} and username {}", reservation.getHotelName(), reservation.getRoomTypeName(), session.getId(), session.getCreationTime(), reservation.getCheckInDate(), principal.getName());
        return "reservation/details";
    }

    @GetMapping("/auth/guest/reservation/summary")
    public String getReservationSummary() {
        return "reservation/summary";
    }

}
