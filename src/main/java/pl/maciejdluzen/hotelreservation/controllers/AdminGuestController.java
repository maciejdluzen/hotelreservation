package pl.maciejdluzen.hotelreservation.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.iap.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;
import pl.maciejdluzen.hotelreservation.dtos.GetGuestsDto;
import pl.maciejdluzen.hotelreservation.dtos.GuestDto;
import pl.maciejdluzen.hotelreservation.services.GuestService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/auth/admin/guests")
public class AdminGuestController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final GuestService guestService;

    public AdminGuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public String getGuestsDashboard() {
        return "admin/manageguests";
    }

    @GetMapping("/getall")
    public ResponseEntity<List<GetGuestsDto>> getAllGuests() {
        List<GetGuestsDto> guests = guestService.getAllGuestsWithoutDetails();
        LOG.info("Guest list: {}: ", guests.toString());
        if(!guests.isEmpty()) {
            return new ResponseEntity<>(guests, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGuest(@PathVariable("id") Long id) {
        if(guestService.deleteGuest(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



//    @GetMapping("/getall")
//    public ResponseEntity<String> getAllGuests2() throws JsonProcessingException {
//        List<GetGuestsDto> guests = guestService.getAllGuestsWithoutDetails();
//        LOG.info("Guest list: {}: ", guests.toString());
//        if(!guests.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.OK).body(toJson(guests));
//        } else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//    }

    private String toJson(Object object) throws JsonProcessingException
    {
        return new ObjectMapper().writeValueAsString(object);
    }

}
