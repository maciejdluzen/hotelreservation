package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;
import pl.maciejdluzen.hotelreservation.dtos.NewHotelDto;

public interface HotelService {

    Hotel createHotel(NewHotelDto hotelDto);

}
