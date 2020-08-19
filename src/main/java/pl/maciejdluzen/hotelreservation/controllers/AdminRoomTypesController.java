package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejdluzen.hotelreservation.services.RoomTypeService;

@Controller
@RequestMapping("/auth/admin/roomtypes")
public class AdminRoomTypesController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final RoomTypeService roomTypeService;

    public AdminRoomTypesController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping
    public String getRoomTypesDashboard(Model model) {
        return "admin/manageroomtypes";
    }
}
