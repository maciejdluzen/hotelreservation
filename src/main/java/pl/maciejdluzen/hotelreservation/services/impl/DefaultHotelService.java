package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;
import pl.maciejdluzen.hotelreservation.domain.repositories.HotelRepository;
import pl.maciejdluzen.hotelreservation.dtos.NewHotelDto;
import pl.maciejdluzen.hotelreservation.services.HotelService;

@Service
public class DefaultHotelService implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper mapper;

    public DefaultHotelService(HotelRepository hotelRepository, ModelMapper mapper) {
        this.hotelRepository = hotelRepository;
        this.mapper = mapper;
    }



    /*
    ******* HOTELS *******
     */

    @Override
    public Hotel createHotel(NewHotelDto hotelDto) {
        Hotel hotel = mapper.map(hotelDto, Hotel.class);
        return hotelRepository.save(hotel);
    }








}
