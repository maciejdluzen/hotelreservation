package pl.maciejdluzen.hotelreservation.services.impl;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.maciejdluzen.hotelreservation.domain.entities.Image;
import pl.maciejdluzen.hotelreservation.domain.repositories.ImageRepository;
import pl.maciejdluzen.hotelreservation.services.ImageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        } catch (Exception exc) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteImage(Long id) {
        try {
            imageRepository.deleteById(id);
        } catch (Exception exc) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> findAllImagesDescription() {
        List<Image> images = imageRepository.findAll();

        return images.stream()
                    .map(Image::getDescription)
                    .collect(Collectors.toList());
    }

    @Override
    public List<Image> findAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public List<byte[]> findAllImagesUsedForRoomTypes() {
        List<Image> images = imageRepository.findAllImagesByRoomTypes();
        LOG.info("Images: {}", images);
        List<byte[]> imagesByte = new ArrayList<>();
        for(Image image : images) {
            LOG.info("Inside the loop");
            imagesByte.add(image.getImage());
        }
        return imagesByte;
    }

    @Override
    public Image findImageById(Long id) {
        return imageRepository.getOne(id);
    }

}
