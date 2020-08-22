package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.maciejdluzen.hotelreservation.domain.entities.Image;
import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    RoomType findRoomTypeByName(String name);



}
