package pl.maciejdluzen.hotelreservation.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.maciejdluzen.hotelreservation.domain.entities.Image;
import pl.maciejdluzen.hotelreservation.domain.repositories.ImageRepository;
import pl.maciejdluzen.hotelreservation.services.ImageService;

import java.io.IOException;
import java.util.List;

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

//    @GetMapping("/getrooms")
//    public ResponseEntity<List<byte[]>> getAllImagesWithRoomsAssigned() {
//        List<byte[]> images = imageService.findAllImagesUsedForRoomTypes();
//        LOG.info("List byte {}", images);
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<>(images, headers, HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getImageById(@PathVariable("id") Long id) {
        Image image = imageRepository.getOne(id);
        return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(image.getContentType()))
                    .header("Content-Disposition", String.format("filename=%s", image.getName()))
                    .body(new ByteArrayResource(image.getImage()));
    }

    @PostMapping
    public String uploadImage(@RequestParam("name") String name,
                              @RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes) {

        if(imageService.saveImage(file, name)) {
            redirectAttributes.addFlashAttribute("message", "Plik został pomyślnie przesłany");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } else {
            redirectAttributes.addFlashAttribute("message", "Błąd przetwarzania. Sprawdź rozmiar pliku (max. 10MB)");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/auth/admin/images";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id) {
     if(imageService.deleteImage(id)) {
         return ResponseEntity.noContent().build();
     } else {
         return ResponseEntity.notFound().build();
     }
    }
}
