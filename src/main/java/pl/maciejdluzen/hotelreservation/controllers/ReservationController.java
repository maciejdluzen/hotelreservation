package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDto;
import pl.maciejdluzen.hotelreservation.services.HotelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
@SessionAttributes("reservationDto")
public class ReservationController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;

    public ReservationController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(LocalDate.class, new CustomDateEditor(new SimpleDateFormat("dd.MM.yyyy"), true));
//    }


    @GetMapping
    public String home(Model model) {
        model.addAttribute("hotelsNames", hotelService.findAllHotelsNames());
        model.addAttribute("reservationDto", new ReservationDto());
        return "index";
    }

    //@GetMapping("reservation/roomselection")
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
                                            Model model) {
        String param = request.getParameter("roomType");
        ReservationDto reservation = (ReservationDto) session.getAttribute("reservationDto");
        reservation.setRoomTypeName(param);
        model.addAttribute("reservationDto", reservation);
        LOG.info("Hotel Name form the session: {}, roomType {} and session id {} and creation time: {}, checkin {} ", reservation.getHotelName(), reservation.getRoomTypeName(), session.getId(), session.getCreationTime(), reservation.getCheckInDate());
        return "reservation/details";
    }

    @GetMapping("/auth/guest/reservation/summary")
    public String getReservationSummary() {
        return "reservation/summary";
    }




}
