package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;
import pl.maciejdluzen.hotelreservation.dtos.GetRoomTypeNameDto;

import java.util.List;

public interface RoomTypeService {

    List<String> findAllRoomTypeNames();

}
