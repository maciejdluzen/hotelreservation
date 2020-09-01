package pl.maciejdluzen.hotelreservation.controllers;

import com.sun.mail.iap.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.maciejdluzen.hotelreservation.domain.entities.Reservation;
import pl.maciejdluzen.hotelreservation.dtos.CardDetailsDto;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDto;
import pl.maciejdluzen.hotelreservation.services.CardDetailsService;
import pl.maciejdluzen.hotelreservation.services.GuestService;
import pl.maciejdluzen.hotelreservation.services.HotelService;
import pl.maciejdluzen.hotelreservation.services.ReservationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
@SessionAttributes("reservationDto")
public class ReservationController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;
    private final GuestService guestService;
    private final ReservationService reservationService;
    private final CardDetailsService cardDetailsService;

    public ReservationController(HotelService hotelService, GuestService guestService, ReservationService reservationService, CardDetailsService cardDetailsService) {
        this.hotelService = hotelService;
        this.guestService = guestService;
        this.reservationService = reservationService;
        this.cardDetailsService = cardDetailsService;
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
    public String getReservationDetailsPage(//@ModelAttribute("reservationDto") ReservationDto reservationDto,
                                            @SessionAttribute("reservationDto") ReservationDto reservation,
                                            HttpSession session,
                                            HttpServletRequest request,
                                            Principal principal,
                                            Model model) {
        String param = request.getParameter("roomType");
        //ReservationDto reservation = (ReservationDto) session.getAttribute("reservationDto");
        reservation.setRoomTypeName(param);
        reservation.setUsername(principal.getName());
        LOG.info("Username: {}", reservation.getUsername());
        reservation.setGuestName(guestService.findGuestNameByUsername(reservation.getUsername()));
        model.addAttribute("reservationDto", reservation);
        model.addAttribute("cardDetails", new CardDetailsDto());

        LOG.info("Hotel Name form the session: {}, roomType {} and session id {} and creation time: {}, checkin {} and username {}", reservation.getHotelName(), reservation.getRoomTypeName(), session.getId(), session.getCreationTime(), reservation.getCheckInDate(), principal.getName());

        return "reservation/details";
    }

    @PostMapping("auth/guest/reservation/details")
    public String createReservation(@SessionAttribute("reservationDto") ReservationDto reservation,
                                    //BindingResult result,
                                    @ModelAttribute("cardDetails") @Valid CardDetailsDto cardDetails,
                                    BindingResult result2,
                                    Model model) {

        if(result2.hasErrors()) {
            return "reservation/details";
        }

        LOG.info("Reservation: {}", reservation.getGuestName());
        LOG.info("CardDetails: {}", cardDetails.getCardNumber());

        cardDetailsService.saveCardDetails(cardDetails);
        Reservation reservationSummary = reservationService.createReservation(reservation, cardDetails);
        if(reservationSummary != null) {
            model.addAttribute("reservationSummary", reservationSummary);
            return "reservation/summary";
        }

        return "redirect:/auth/guest/reservation/summary";
    }

    @GetMapping("reservationsummary")
    public ResponseEntity<Reservation> getReservationSummary(Reservation reservationSummary) {

        if(reservationSummary != null) {
            return new ResponseEntity<>(reservationSummary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



    @GetMapping("auth/guest/reservation/summary")
    public String getReservationSummaryPage() {
        return "reservation/summary";
    }

}
