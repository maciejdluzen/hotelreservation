package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Image;
import pl.maciejdluzen.hotelreservation.domain.entities.RoomType;
import pl.maciejdluzen.hotelreservation.domain.repositories.ImageRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoomTypeRepository;
import pl.maciejdluzen.hotelreservation.dtos.RoomTypeDto;
import pl.maciejdluzen.hotelreservation.services.RoomTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultRoomTypeService implements RoomTypeService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final RoomTypeRepository roomTypeRepository;
    private final ImageRepository imageRepository;
    private final ModelMapper mapper;

    public DefaultRoomTypeService(RoomTypeRepository roomTypeRepository, ImageRepository imageRepository, ModelMapper mapper) {
        this.roomTypeRepository = roomTypeRepository;
        this.imageRepository = imageRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RoomType> findAllRoomTypes() {
        return roomTypeRepository.findAll();
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

    @Override
    public RoomType createRoomType(RoomTypeDto roomTypeDto) {
        RoomType roomType = new RoomType();
        roomType.setName(roomTypeDto.getName());
        roomType.setNoPersons(roomTypeDto.getNoPersons());
        roomType.setRateNet(roomTypeDto.getRateNet());
        roomType.setTax(roomTypeDto.getTax());
        roomType.setRateGross(roomTypeDto.getRateNet() * (1 + roomTypeDto.getTax()));
        roomType.setDescription(roomTypeDto.getDescription());
        roomType.setFeature1(roomTypeDto.getFeature1());
        roomType.setFeature2(roomTypeDto.getFeature2());
        roomType.setFeature3(roomTypeDto.getFeature3());
        roomType.setFeature4(roomTypeDto.getFeature4());
        Image image = imageRepository.getOne(roomTypeDto.getImageId());
        roomType.setImage(image);
        return roomTypeRepository.save(roomType);
    }

    @Override
    public List<RoomTypeDto> getAllRoomTypesWithoutImage() {
        List<RoomType> roomTypes = roomTypeRepository.findAll();
        List<RoomTypeDto> roomsTypeDto = new ArrayList<>();
        for(RoomType roomType : roomTypes) {
            RoomTypeDto roomTypeDto = mapper.map(roomType, RoomTypeDto.class);
            roomsTypeDto.add(roomTypeDto);
        }
        return roomsTypeDto;
    }
}
