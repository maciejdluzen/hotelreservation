package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;
import pl.maciejdluzen.hotelreservation.dtos.RoomTypeDto;
import pl.maciejdluzen.hotelreservation.services.ImageService;
import pl.maciejdluzen.hotelreservation.services.RoomTypeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/auth/admin/roomtypes")
public class AdminRoomTypesController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final RoomTypeService roomTypeService;
    private final ImageService imageService;

    public AdminRoomTypesController(RoomTypeService roomTypeService, ImageService imageService) {
        this.roomTypeService = roomTypeService;
        this.imageService = imageService;
    }

    @GetMapping
    public String getRoomTypesDashboard(Model model) {
        model.addAttribute("roomType", new RoomTypeDto());
        model.addAttribute("images", imageService.findAllImages());
        return "admin/manageroomtypes";
    }

    @GetMapping("/getall")
    public ResponseEntity<List<RoomTypeDto>> getAllRoomTypesWithoutImage() {
        List<RoomTypeDto> roomsType = roomTypeService.getAllRoomTypesWithoutImage();
        if(!roomsType.isEmpty()) {
            return new ResponseEntity<>(roomsType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }




    @PostMapping
    public String createRoomType(@ModelAttribute("roomType") @Valid RoomTypeDto roomTypeDto,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        LOG.info("RoomTypeDto: {}", roomTypeDto);
        List<RoomType> roomTypes = roomTypeService.findAllRoomTypes();
        LOG.info("list size: {}", roomTypes.size());

        if(result.hasErrors()) {
            model.addAttribute("images", imageService.findAllImages());
            model.addAttribute("message", "Popraw błędy w formularzu");
            model.addAttribute("alertClass", "alert-danger");
            LOG.info("Errors");
            return "admin/manageroomtypes";
        }
        if(roomTypes.size() >= 3) {
            LOG.info("List size too big");
            model.addAttribute("message", "Dozwolona ilość typów pokoi wynosi 3!");
            model.addAttribute("alertClass", "alert-warning");
            return "admin/manageroomtypes";
        } else {
            redirectAttributes.addFlashAttribute("message", "Rodzaj pokoju został pomyślnie zapisany");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            roomTypeService.createRoomType(roomTypeDto);
            return "redirect:/auth/admin/roomtypes";
        }
    }
}
