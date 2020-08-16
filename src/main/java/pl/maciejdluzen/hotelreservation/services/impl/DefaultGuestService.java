package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;
import pl.maciejdluzen.hotelreservation.domain.repositories.GuestRepository;
import pl.maciejdluzen.hotelreservation.dtos.GetGuestsDto;
import pl.maciejdluzen.hotelreservation.services.GuestService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultGuestService implements GuestService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final GuestRepository guestRepository;
    private final ModelMapper mapper;

    public DefaultGuestService(GuestRepository guestRepository, ModelMapper mapper) {
        this.guestRepository = guestRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GetGuestsDto> getAllGuestsWithoutDetails() {
        List<Guest> guests = guestRepository.findAllByOrderByLastName();
        List<GetGuestsDto> guestsDto = new ArrayList<>();
        for(Guest guest : guests) {
            GetGuestsDto guestDto = mapper.map(guest, GetGuestsDto.class);
            guestsDto.add(guestDto);
        }
        return guestsDto;
    }

    @Override
    public boolean deleteGuest(Long id) {
        try {
            guestRepository.deleteById(id);
        } catch (Exception exc) {
            return false;
        }
        return true;
    }


}
