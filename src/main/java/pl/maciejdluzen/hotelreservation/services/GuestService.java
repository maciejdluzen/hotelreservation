package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.domain.entities.Guest;
import pl.maciejdluzen.hotelreservation.dtos.GetGuestsDto;

import java.util.List;

public interface GuestService {

    List<GetGuestsDto> getAllGuestsWithoutDetails();

    boolean deleteGuest(Long id);

    boolean deactivateGuestAccount(Long id);
}
