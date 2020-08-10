package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejdluzen.hotelreservation.dtos.NewHotelDto;
import pl.maciejdluzen.hotelreservation.services.HotelService;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth/admin")
public class AdminController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;

    public AdminController(HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @GetMapping("/hotels")
    public String getHotelsDashboard(@ModelAttribute("hotel") NewHotelDto hotelDto) {
        return "admin/dashboard";
    }

    @PostMapping("/hotels")
    public String createHotel(@ModelAttribute("hotel") @Valid NewHotelDto hotelDto,
                              BindingResult result, Model model) {
        if(result.hasErrors()) {
            LOG.info("Binding error: {}", result.toString());
            return "admin/dashboard";
        }
        hotelService.createHotel(hotelDto);
        return "redirect:/auth/admin/hotels";
    }






}
