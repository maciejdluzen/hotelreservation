package pl.maciejdluzen.hotelreservation.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoomTypeRepository;
import pl.maciejdluzen.hotelreservation.dtos.GetRoomTypeNameDto;
import pl.maciejdluzen.hotelreservation.services.RoomTypeService;

import java.util.List;

@Service
public class DefaultRoomTypeService implements RoomTypeService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final RoomTypeRepository roomTypeRepository;

    public DefaultRoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public List<RoomType> findAllRoomTypeNames() {
        List<RoomType> roomTypeNames = roomTypeRepository.findAll();
        LOG.info("DefaultRoomTypeService: Found room types: {}", roomTypeNames);
        return roomTypeNames;
    }
}
