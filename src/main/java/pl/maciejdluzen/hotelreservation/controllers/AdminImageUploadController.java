package pl.maciejdluzen.hotelreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.maciejdluzen.hotelreservation.domain.repositories.ImageRepository;
import pl.maciejdluzen.hotelreservation.services.ImageService;

@Controller
@RequestMapping("/auth/admin/images")
public class AdminImageUploadController {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final ImageService imageService;
    private final ImageRepository imageRepository;

    public AdminImageUploadController(ImageService imageService, ImageRepository imageRepository) {
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    @GetMapping
    public String getImageDashboard(Model model){
        model.addAttribute("images", imageRepository.findAll());
        return "admin/manageimages";
    }

    @PostMapping
    public String uploadImage(@RequestParam("name") String name,
                              @RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes) {

        if(imageService.saveImage(file, name)) {
            redirectAttributes.addFlashAttribute("message", "File successfully uploaded!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error occured!");
        }
        return "redirect:/auth/admin/images";
    }




}
