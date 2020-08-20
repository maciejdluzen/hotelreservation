package pl.maciejdluzen.hotelreservation.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.maciejdluzen.hotelreservation.domain.entities.Image;
import pl.maciejdluzen.hotelreservation.domain.repositories.ImageRepository;
import pl.maciejdluzen.hotelreservation.services.ImageService;

import java.io.IOException;

@Transactional
@Service
public class DefaultImageService implements ImageService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final ImageRepository imageRepository;

    public DefaultImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public boolean saveImage(MultipartFile file, String name) {
        try {
            Image image = new Image();
            image.setContentType(file.getContentType());
            image.setName(file.getName());
            image.setImage(file.getBytes());
            image.setDescription(name);
            imageRepository.save(image);
        } catch (IOException exc) {
            LOG.info("Exception occured: {}", exc);
            exc.printStackTrace();
            return false;
        }
        return true;
    }
}
