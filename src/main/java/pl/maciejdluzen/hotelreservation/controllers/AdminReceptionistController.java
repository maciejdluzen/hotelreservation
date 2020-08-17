package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejdluzen.hotelreservation.dtos.GetReceptionistsDto;
import pl.maciejdluzen.hotelreservation.dtos.NewReceptionistDto;
import pl.maciejdluzen.hotelreservation.services.ReceptionistService;

import java.util.List;

@Controller
@RequestMapping("/auth/admin/receptionists")
public class AdminReceptionistController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final ReceptionistService receptionistService;

    public AdminReceptionistController(ReceptionistService receptionistService) {
        this.receptionistService = receptionistService;
    }

    @GetMapping
    public String getReceptionistsDashboard(Model model) {
        model.addAttribute("receptionist", new NewReceptionistDto());
        return "admin/managereceptionists";
    }

    @GetMapping("/getall")
    public ResponseEntity<List<GetReceptionistsDto>> getAllReceptionists() {
        List<GetReceptionistsDto> receptionists = receptionistService.getAllReceptionists();
        if(!receptionists.isEmpty()) {
            return new ResponseEntity<>(receptionists, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }










}
