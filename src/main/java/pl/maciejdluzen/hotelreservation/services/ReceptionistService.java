package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.domain.entities.Receptionist;
import pl.maciejdluzen.hotelreservation.dtos.GetReceptionistsDto;
import pl.maciejdluzen.hotelreservation.dtos.NewReceptionistDto;

import java.util.List;

public interface ReceptionistService {

    List<GetReceptionistsDto> getAllReceptionists();

    Receptionist createReceptionistAccount(NewReceptionistDto receptionistDto);
}
