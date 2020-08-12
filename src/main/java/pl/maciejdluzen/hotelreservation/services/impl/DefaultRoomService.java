package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Room;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoomRepository;
import pl.maciejdluzen.hotelreservation.dtos.GetRoomDto;
import pl.maciejdluzen.hotelreservation.services.RoomService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultRoomService implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper mapper;

    public DefaultRoomService(RoomRepository roomRepository, ModelMapper mapper) {
        this.roomRepository = roomRepository;
        this.mapper = mapper;
    }

    public List<GetRoomDto> getAllRoomsByHotelId(Long id) {
        List<Room> rooms = roomRepository.findAllByHotelId(id);
        List<GetRoomDto> roomsDto = new ArrayList<>();
        for(Room room : rooms) {
            GetRoomDto roomDto = mapper.map(room, GetRoomDto.class);
            roomsDto.add(roomDto);
        };
        return roomsDto;
    }

}
