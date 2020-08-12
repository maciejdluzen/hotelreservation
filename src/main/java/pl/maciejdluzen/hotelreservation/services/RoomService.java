package pl.maciejdluzen.hotelreservation.services;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejdluzen.hotelreservation.domain.entities.Room;
import pl.maciejdluzen.hotelreservation.dtos.GetRoomDto;

import java.util.List;

public interface RoomService {

    List<GetRoomDto> getAllRoomsByHotelId(Long id);

}
