package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.maciejdluzen.hotelreservation.domain.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT i.id, i.name, i.contentType, i.image, i.description FROM Image i WHERE i.description = :description")
    Image findImageByDescription(@Param("description") String description);
}
