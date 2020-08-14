package pl.maciejdluzen.hotelreservation.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;
import pl.maciejdluzen.hotelreservation.dtos.GetRoomDto;
import pl.maciejdluzen.hotelreservation.dtos.NewHotelDto;
import pl.maciejdluzen.hotelreservation.dtos.NewRoomDto;
import pl.maciejdluzen.hotelreservation.services.HotelService;
import pl.maciejdluzen.hotelreservation.services.RoomService;
import pl.maciejdluzen.hotelreservation.services.RoomTypeService;

import javax.servlet.http.HttpServletResponse;
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
                              Model model) {

        model.addAttribute("hotels", hotelService.getAllHotels());
        if(result.hasErrors()) {
            model.addAttribute("room", new NewRoomDto());
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
    public String createRoomForHotelById(@ModelAttribute("room") @Valid NewRoomDto roomDto,
                                         BindingResult result,
                                         @PathVariable("hotelId") Long hotelId,
                                         Model model) {
        if(result.hasErrors()) {

            model.addAttribute("hotels", hotelService.getAllHotels());
            List<GetRoomDto> roomsDto = roomService.getAllRoomsByHotelId(hotelId);
            model.addAttribute("rooms", roomsDto);
            model.addAttribute("roomTypes", roomTypeService.findAllRoomTypeNames());


            model.addAttribute("hotel", new NewHotelDto());
            LOG.info("Binding error: {}", result.toString());
            return "admin/dashboard";
        }


        model.addAttribute("hotels", hotelService.getAllHotels());
        List<GetRoomDto> roomsDto = roomService.getAllRoomsByHotelId(hotelId);
        model.addAttribute("rooms", roomsDto);
        model.addAttribute("roomTypes", roomTypeService.findAllRoomTypeNames());


        LOG.info("AdminController: CreateRoomForHotelById: Created room: {}", roomDto.toString());

        roomDto.setHotelId(hotelId);
        roomService.createNewRoom(roomDto);

        return "redirect:/auth/admin/hotels/{hotelId}/rooms";
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<String> getHotel(@PathVariable("id") Long id) throws JsonProcessingException {

        Hotel hotel = hotelService.findHotelById(id);
        if(hotel != null) {
            return ResponseEntity.status(HttpStatus.OK).body(toJson(hotel));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") Long id) {
        LOG.info("Deleting hotel");

        if(hotelService.deleteHotel(id)) {
            // .header(HttpHeaders.LOCATION, "/auth/admin/hotels") // might need it if redirection in ajax doesn't work!
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/hotels/rooms/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable("roomId") Long roomId) {
        if(roomService.deleteRoom(roomId)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private String toJson(Object object) throws JsonProcessingException
    {
        return new ObjectMapper().writeValueAsString(object);
    }
}
