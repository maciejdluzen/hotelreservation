package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;
import pl.maciejdluzen.hotelreservation.domain.repositories.HotelRepository;
import pl.maciejdluzen.hotelreservation.dtos.GetHotelDto;
import pl.maciejdluzen.hotelreservation.dtos.GetHotelDtoJson;
import pl.maciejdluzen.hotelreservation.dtos.NewHotelDto;
import pl.maciejdluzen.hotelreservation.services.HotelService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultHotelService implements HotelService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

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

    @Override
    public List<GetHotelDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<GetHotelDto> hotelsDto = new ArrayList<>();
        for(Hotel hotel : hotels) {
            GetHotelDto hotelDto = mapper.map(hotel, GetHotelDto.class);
            hotelDto.setAddress(hotel.getStreet() + " " + hotel.getNumber() + ", " + hotel.getPostCode() + ", " + hotel.getCity());
            hotelsDto.add(hotelDto);
        }
        return hotelsDto;
    }

    @Override
    public GetHotelDtoJson findHotelById(Long id) {
        Hotel hotel = hotelRepository.getOne(id);
        GetHotelDtoJson hotelDto = mapper.map(hotel, GetHotelDtoJson.class);
        return hotelDto;
    }

    @Override
    public Boolean deleteHotel(Long id) {
        try {
            LOG.info("Deleting hotel");
            hotelRepository.deleteById(id);
            return true;
        } catch (Exception exc) {
            return false;
        }
    }


}
