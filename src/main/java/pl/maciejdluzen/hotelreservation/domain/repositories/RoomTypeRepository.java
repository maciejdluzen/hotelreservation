package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
}
