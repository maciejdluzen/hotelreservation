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
import pl.maciejdluzen.hotelreservation.dtos.NewRoomDto;
import pl.maciejdluzen.hotelreservation.services.HotelService;
import pl.maciejdluzen.hotelreservation.services.RoomService;
import pl.maciejdluzen.hotelreservation.services.RoomTypeService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/auth/admin")
public class AdminController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;
    private final RoomService roomService;
    private final RoomTypeService roomTypeService;

    public AdminController(HotelService hotelService, RoomService roomService, RoomTypeService roomTypeService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.roomTypeService = roomTypeService;
    }

    @GetMapping("/hotels")
    public String getHotelsDashboard(@ModelAttribute("hotel") NewHotelDto hotelDto,
                                     Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "admin/dashboard";
    }

    @PostMapping("/hotels")
    public String createHotel(@ModelAttribute("hotel") @Valid NewHotelDto hotelDto,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {

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
    public String getRoomsByHotelId(@ModelAttribute("hotel") NewHotelDto hotelDto,
                                    @ModelAttribute("room") NewRoomDto roomDto,
                                    @PathVariable("id") Long id,
                                    Model model) {

        model.addAttribute("hotels", hotelService.getAllHotels());
        List<GetRoomDto> roomsDto = roomService.getAllRoomsByHotelId(id);
        LOG.info("AdminController.class: Hotel rooms: {}", roomsDto);
        model.addAttribute("rooms", roomsDto);
        model.addAttribute("roomTypes", roomTypeService.findAllRoomTypeNames());
        /*
        if(roomsDto.size() != 0) {

            LOG.info("AdminController.class: Inside if-statement: Hotel rooms: {}", roomsDto);
        } else {
            model.addAttribute("msg", "Brak pokoi przypisanych do hotelu");
            LOG.info("AdminController.class: Inside else part of if-statement");
        }
        */

        return "admin/dashboard";
    }

    @PostMapping("/hotels/{id}/rooms")
    public String createRoomForHotelById(@ModelAttribute("room") @Valid NewRoomDto roomDto,
                                         @PathVariable("id") Long id,
                                         BindingResult result,
                                         Model model) {

        model.addAttribute("hotels", hotelService.getAllHotels());
        List<GetRoomDto> roomsDto = roomService.getAllRoomsByHotelId(id);
        model.addAttribute("rooms", roomsDto);
        model.addAttribute("roomTypes", roomTypeService.findAllRoomTypeNames());
        roomService.createNewRoom(roomDto, id);
        return "redirect:/auth/admin/hotels/{id}/rooms";
    }
}
