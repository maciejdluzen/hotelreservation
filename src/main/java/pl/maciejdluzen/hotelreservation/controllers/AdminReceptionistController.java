package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejdluzen.hotelreservation.dtos.GetReceptionistsDto;
import pl.maciejdluzen.hotelreservation.dtos.NewReceptionistDto;
import pl.maciejdluzen.hotelreservation.services.HotelService;
import pl.maciejdluzen.hotelreservation.services.ReceptionistService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/auth/admin/receptionists")
public class AdminReceptionistController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final ReceptionistService receptionistService;
    private final HotelService hotelService;

    public AdminReceptionistController(ReceptionistService receptionistService, HotelService hotelService) {
        this.receptionistService = receptionistService;
        this.hotelService = hotelService;
    }

    @GetMapping
    public String getReceptionistsDashboard(Model model) {
        model.addAttribute("receptionist", new NewReceptionistDto());
        model.addAttribute("hotelsNames", hotelService.findAllHotelsNames());
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

    @PostMapping
    public String createReceptionistAccount(@ModelAttribute("receptionist") @Valid NewReceptionistDto receptionistDto,
                                            BindingResult result,
                                            Model model) {
        if(result.hasErrors()) {
            //model.addAttribute("receptionist", new NewReceptionistDto());
            model.addAttribute("hotelsNames", hotelService.findAllHotelsNames());
            return "admin/managereceptionists";
        }
        receptionistService.createReceptionistAccount(receptionistDto);
        return "redirect:/auth/admin/receptionists";
    }

}
