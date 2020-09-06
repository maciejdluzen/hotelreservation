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
import pl.maciejdluzen.hotelreservation.dtos.GetReservationsDto2;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDetailsDto;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDetailsDto2;
import pl.maciejdluzen.hotelreservation.services.ReceptionistService;
import pl.maciejdluzen.hotelreservation.services.ReservationService;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/reservations-future")
    public ResponseEntity<List<GetReservationsDto2>> getAllFutureReservationsByHotel(Principal principal) {
        List<GetReservationsDto2> reservations = reservationService.getAllFutureReservationsByHotel(principal.getName());
        LOG.info("Reservations: {}", reservations.toString());
        if(!reservations.isEmpty()) {
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<String> getReservationDetails(@PathVariable("id") Long id) throws JsonProcessingException{
        ReservationDetailsDto2 reservationDetails = reservationService.getReservationDetailsForReceptionist(id);
        LOG.info("ReservationDetails: {}", reservationDetails.toString());
        if(reservationDetails != null) {
            LOG.info("ReservationDetails - HttpStatus.OK");
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
