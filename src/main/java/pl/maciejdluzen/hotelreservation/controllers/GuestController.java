package pl.maciejdluzen.hotelreservation.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.maciejdluzen.hotelreservation.domain.entities.Reservation;
import pl.maciejdluzen.hotelreservation.dtos.GetReservationsDto;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDetailsDto;
import pl.maciejdluzen.hotelreservation.services.GuestService;
import pl.maciejdluzen.hotelreservation.services.ReservationService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/auth/guest")
public class GuestController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final GuestService guestService;
    private final ReservationService reservationService;

    public GuestController(GuestService guestService, ReservationService reservationService) {
        this.guestService = guestService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getGuestDashboard(Model model, Principal principal) {
        LOG.info("Logged user: {} ", principal.getName());
        model.addAttribute("guestFullName", guestService.findGuestNameByUsername(principal.getName()));
        return "guest/dashboard";
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<GetReservationsDto>> getGuestReservations(Principal principal) {
        List<GetReservationsDto> reservations = reservationService.getAllReservationsByUsername(principal.getName());
        if (!reservations.isEmpty()) {
            LOG.info("GuestController - getGuestReservations: {}", reservations.toString());
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<String> getReservationDetails(@PathVariable("id") Long id) throws JsonProcessingException{
        ReservationDetailsDto reservationDetails = reservationService.getReservationDetails(id);
        LOG.info("ReservationDetails: {}", reservationDetails.toString());
        if(reservationDetails != null) {
            LOG.info("ReservationDetails - HttpStatus.OK");
            //return new ResponseEntity<>(reservationDetails, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(toJson(reservationDetails));
        } else {
            LOG.info("ReservationDetails - HttpStatus.NOTFOUND");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private String toJson(Object object) throws JsonProcessingException
    {
        return new ObjectMapper().writeValueAsString(object);
    }
}
