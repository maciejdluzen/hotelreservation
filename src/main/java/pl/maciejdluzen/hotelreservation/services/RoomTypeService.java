package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;
import pl.maciejdluzen.hotelreservation.dtos.GetRoomTypeNameDto;
import pl.maciejdluzen.hotelreservation.dtos.RoomTypeDto;

import java.util.List;

public interface RoomTypeService {

    List<String> findAllRoomTypeNames();

    RoomType createRoomType(RoomTypeDto roomTypeDto);

    List<RoomType> findAllRoomTypes();

    List<RoomTypeDto> getAllRoomTypesWithoutImage();
}
