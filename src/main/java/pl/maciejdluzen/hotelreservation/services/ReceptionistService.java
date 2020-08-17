package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.dtos.GetReceptionistsDto;

import java.util.List;

public interface ReceptionistService {

    List<GetReceptionistsDto> getAllReceptionists();
}
