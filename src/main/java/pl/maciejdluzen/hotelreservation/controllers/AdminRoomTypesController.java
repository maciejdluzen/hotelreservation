package pl.maciejdluzen.hotelreservation.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;
import pl.maciejdluzen.hotelreservation.dtos.RoomTypeDto;
import pl.maciejdluzen.hotelreservation.exceptions.ForeignKeyConstraintException;
import pl.maciejdluzen.hotelreservation.services.ImageService;
import pl.maciejdluzen.hotelreservation.services.RoomTypeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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

    @GetMapping("/{id}")
    public ResponseEntity<String> getRoomType(@PathVariable("id") Long id) throws JsonProcessingException {
            RoomTypeDto roomTypeDto = roomTypeService.findRoomTypeById(id);
            if(roomTypeDto != null) {
                return ResponseEntity.status(HttpStatus.OK).body(toJson(roomTypeDto));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoomType(@PathVariable("id") Long id) {
        if(roomTypeService.deleteRoomTypeById(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            //throw new ForeignKeyConstraintException("Nie można usunąć elementu ze względu na użycie");
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoomType(@PathVariable("id") Long id,
                                            @RequestBody @Valid RoomTypeDto roomTypeDto
                                            /* BindingResult result */) {
        /*
            I have used different kind of validation here - check exceptions package
            and Custom Exception Handler for details

         */

        /*
        if(result.hasErrors()) {
            String error = Objects.requireNonNull(result.getFieldError("name")).getDefaultMessage();
            LOG.info("Err: {}", error);

            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

        }
        */

        if(roomTypeService.updateRoomType(id, roomTypeDto)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private String toJson(Object object) throws JsonProcessingException
    {
        return new ObjectMapper().writeValueAsString(object);
    }
}
