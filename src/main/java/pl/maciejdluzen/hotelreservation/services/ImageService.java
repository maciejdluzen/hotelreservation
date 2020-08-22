package pl.maciejdluzen.hotelreservation.services;

import org.springframework.web.multipart.MultipartFile;
import pl.maciejdluzen.hotelreservation.domain.entities.Image;

import java.util.List;

public interface ImageService {

    boolean saveImage(MultipartFile file, String name);

    boolean deleteImage(Long id);

    List<String> findAllImagesDescription();

    List<Image> findAllImages();
}
