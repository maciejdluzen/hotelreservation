package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.maciejdluzen.hotelreservation.dtos.GetRoomDto;
import pl.maciejdluzen.hotelreservation.dtos.NewHotelDto;
import pl.maciejdluzen.hotelreservation.services.HotelService;
import pl.maciejdluzen.hotelreservation.services.RoomService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/auth/admin")
public class AdminController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;
    private final RoomService roomService;

    public AdminController(HotelService hotelService, RoomService roomService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }


    @GetMapping("/hotels")
    public String getHotelsDashboard(@ModelAttribute("hotel") NewHotelDto hotelDto,
                                     Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "admin/dashboard";
    }

    @PostMapping("/hotels")
    public String createHotel(@ModelAttribute("hotel") @Valid NewHotelDto hotelDto,
                              BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        if(result.hasErrors()) {
            LOG.info("Binding error: {}", result.toString());
            return "admin/dashboard";
        }
        redirectAttributes.addAttribute("msg", "addedHotel");
        hotelService.createHotel(hotelDto);
        return "redirect:/auth/admin/hotels";
    }

    @GetMapping("/hotels/{id}/rooms")
    public String getRoomsByHotelId(@PathVariable("id") Long id, Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        List<GetRoomDto> roomsDto = roomService.getAllRoomsByHotelId(id);
        if(roomsDto.size() != 0) {
            model.addAttribute("rooms", roomsDto);
        } else {
            model.addAttribute("msg", "Brak pokoi przypisanych do hotelu");
        }
        return "admin/dashboard";
    }






}
