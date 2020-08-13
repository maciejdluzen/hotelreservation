package pl.maciejdluzen.hotelreservation.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoomTypeRepository;
import pl.maciejdluzen.hotelreservation.dtos.GetRoomTypeNameDto;
import pl.maciejdluzen.hotelreservation.services.RoomTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultRoomTypeService implements RoomTypeService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final RoomTypeRepository roomTypeRepository;

    public DefaultRoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public List<String> findAllRoomTypeNames() {
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        //List<String> roomTypeNames = new ArrayList<>();

        return roomTypes.stream()
                        .map(RoomType::getName)
                        .collect(Collectors.toList());

        // Create a List<String> containing all room names!
//        for(RoomType rm : roomTypes) {
//            roomTypeNames.add(rm.getName());
//        }
//        LOG.info("DefaultRoomTypeService: Found room types: {}", roomTypeNames);
//        return roomTypeNames;
    }
}
