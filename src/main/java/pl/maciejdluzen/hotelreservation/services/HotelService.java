package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;
import pl.maciejdluzen.hotelreservation.dtos.GetHotelDto;
import pl.maciejdluzen.hotelreservation.dtos.NewHotelDto;

import java.util.List;

public interface HotelService {

    Hotel createHotel(NewHotelDto hotelDto);

    List<GetHotelDto> getAllHotels();

}
