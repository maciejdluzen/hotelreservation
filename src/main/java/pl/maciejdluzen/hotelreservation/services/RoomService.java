package pl.maciejdluzen.hotelreservation.services;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejdluzen.hotelreservation.domain.entities.Room;
import pl.maciejdluzen.hotelreservation.dtos.GetRoomDto;
import pl.maciejdluzen.hotelreservation.dtos.NewRoomDto;

import java.util.List;

public interface RoomService {

    List<GetRoomDto> getAllRoomsByHotelId(Long id);

    Room createNewRoom(NewRoomDto roomDto);

    Boolean deleteRoom(Long id);

}
