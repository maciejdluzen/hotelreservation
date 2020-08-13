package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Room;
import pl.maciejdluzen.hotelreservation.domain.repositories.HotelRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoomRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoomTypeRepository;
import pl.maciejdluzen.hotelreservation.dtos.GetRoomDto;
import pl.maciejdluzen.hotelreservation.dtos.NewRoomDto;
import pl.maciejdluzen.hotelreservation.services.RoomService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultRoomService implements RoomService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final RoomRepository roomRepository;
    private final ModelMapper mapper;
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;

    public DefaultRoomService(RoomRepository roomRepository, ModelMapper mapper, HotelRepository hotelRepository, RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.mapper = mapper;
        this.hotelRepository = hotelRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public List<GetRoomDto> getAllRoomsByHotelId(Long id) {
        List<Room> rooms = roomRepository.findAllByHotelId(id);
        LOG.info("DefaultRoomService.class: Hotel rooms: {}", rooms);
        List<GetRoomDto> roomsDto = new ArrayList<>();
        for(Room room : rooms) {
            GetRoomDto roomDto = mapper.map(room, GetRoomDto.class);
            LOG.info("DefaultRoomService.class: Hotel room DTO: {}", roomDto);
            roomsDto.add(roomDto);
        };
        return roomsDto;
    }

    @Override
    public Room createNewRoom(NewRoomDto roomDto) {
        Room room = mapper.map(roomDto, Room.class);
        room.setRoomType(roomTypeRepository.findRoomTypeByName(roomDto.getRoomTypeName()));
        return roomRepository.save(room);
    }


}
