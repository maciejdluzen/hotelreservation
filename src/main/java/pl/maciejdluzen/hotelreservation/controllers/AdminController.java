package pl.maciejdluzen.hotelreservation.controllers;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.loader.plan.build.internal.LoadGraphLoadPlanBuildingStrategy;
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


@Controller
@RequestMapping("/auth/admin")
public class AdminController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @GetMapping("/hotels")
    public String getHotelsDashboard(@ModelAttribute("hotel") NewHotelDto hotelDto) {
        return "admin/dashboard";
    }

    @PostMapping("/hotels")
    public String createHotel(@ModelAttribute("hotel") NewHotelDto hotelDto,
                              BindingResult result, Model model) {
        if(result.hasErrors()) {
            LOG.info("Binding error: {}", result.toString());
        }




        return "redirect:/hotels";
    }






}
