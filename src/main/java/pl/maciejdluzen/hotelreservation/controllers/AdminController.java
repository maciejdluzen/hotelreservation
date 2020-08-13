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
    public String getHotelsDashboard(Model model) {

        // Passing form beans to the view - there are two forms on one view, hence need to pass two objects
        model.addAttribute("hotel", new NewHotelDto());
        model.addAttribute("room", new NewRoomDto());

        // Passing the list of hotels to display in the view
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
        hotelService.createHotel(hotelDto);
        return "redirect:/auth/admin/hotels";
    }

    @GetMapping("/hotels/{hotelId}/rooms")
    public String getRoomsByHotelId(@PathVariable("hotelId") Long hotelId,
                                    Model model) {

        // Passing form beans to the view - there are two forms on one view, hence need to pass two objects
        model.addAttribute("hotel", new NewHotelDto());
        model.addAttribute("room", new NewRoomDto());

        model.addAttribute("hotels", hotelService.getAllHotels());
        List<GetRoomDto> roomsDto = roomService.getAllRoomsByHotelId(hotelId);
        LOG.info("AdminController.class: Hotel rooms: {}", roomsDto);
        model.addAttribute("rooms", roomsDto);
        model.addAttribute("roomTypes", roomTypeService.findAllRoomTypeNames());
        return "admin/dashboard";
    }

    @PostMapping("/hotels/{hotelId}/rooms")
    public String createRoomForHotelById(@ModelAttribute("room") NewRoomDto roomDto,
                                         @PathVariable("hotelId") Long hotelId,
                                         Model model) {

        model.addAttribute("hotels", hotelService.getAllHotels());
        List<GetRoomDto> roomsDto = roomService.getAllRoomsByHotelId(hotelId);
        model.addAttribute("rooms", roomsDto);
        model.addAttribute("roomTypes", roomTypeService.findAllRoomTypeNames());
        LOG.info("AdminController: CreateRoomForHotelById: Created room: {}", roomDto.toString());
        roomDto.setHotelId(hotelId);
        roomService.createNewRoom(roomDto);
        return "redirect:/auth/admin/hotels/{hotelId}/rooms";
    }
}
