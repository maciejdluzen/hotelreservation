package pl.maciejdluzen.hotelreservation.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    boolean saveImage(MultipartFile file, String name);

    boolean deleteImage(Long id);
}
