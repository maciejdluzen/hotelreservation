package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Reservation;
import pl.maciejdluzen.hotelreservation.domain.entities.Room;
import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;
import pl.maciejdluzen.hotelreservation.domain.repositories.HotelRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.ReservationRepository;
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
    private final ReservationRepository reservationRepository;

    public DefaultRoomService(RoomRepository roomRepository, ModelMapper mapper, HotelRepository hotelRepository, RoomTypeRepository roomTypeRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.mapper = mapper;
        this.hotelRepository = hotelRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.reservationRepository = reservationRepository;
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
        //Room room = mapper.map(roomDto, Room.class);
        Room room = new Room();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setFloorNumber(roomDto.getFloorNumber());
        room.setHotel(hotelRepository.getOne(roomDto.getHotelId()));
        room.setRoomType(roomTypeRepository.findRoomTypeByName(roomDto.getRoomTypeName()));
        return roomRepository.save(room);
    }

    @Override
    public GetRoomDto findRoomById(Long id) {
        Room room = roomRepository.getOne(id);
        GetRoomDto roomDto = mapper.map(room, GetRoomDto.class);
        return roomDto;
    }

    @Override
    public Boolean deleteRoom(Long id) {
        try {
            List<Reservation> reservations = reservationRepository.findAllByRoom(roomRepository.getOne(id));
            for(Reservation reservation : reservations) {
                reservation.setRoom(null);
            }
            roomRepository.deleteById(id);
            return true;
        } catch (Exception exc) {
            return false;
        }
    }

    @Override
    public Boolean updateRoom(GetRoomDto roomDto) {
        Room room = mapper.map(roomDto, Room.class);
        RoomType roomType = roomTypeRepository.findRoomTypeByName(roomDto.getRoomTypeName());
        room.setRoomType(roomType);
        room.setHotel(hotelRepository.getOne(roomDto.getHotelId()));
        LOG.info("DefaultRoomService: updateRoom: room: {}", room.toString());
        try {
            LOG.info("DefaultRoomService: updateRoom: SAVING ROOM!");
            roomRepository.save(room);
        } catch (Exception exc) {
            LOG.info(exc.toString());
            return false;
        }
        return true;
    }


}
