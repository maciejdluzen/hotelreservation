package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;
import pl.maciejdluzen.hotelreservation.domain.entities.VerificationToken;
import pl.maciejdluzen.hotelreservation.domain.repositories.GuestRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.VerificationTokenRepository;
import pl.maciejdluzen.hotelreservation.dtos.GetGuestsDto;
import pl.maciejdluzen.hotelreservation.services.GuestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultGuestService implements GuestService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private final GuestRepository guestRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final ModelMapper mapper;

    public DefaultGuestService(GuestRepository guestRepository, VerificationTokenRepository verificationTokenRepository, ModelMapper mapper) {
        this.guestRepository = guestRepository;
        this.verificationTokenRepository = verificationTokenRepository;
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
        Guest guest = guestRepository.getOne(id);
        VerificationToken token = verificationTokenRepository.findByGuest(guest);
        try {
            if(token != null) {
                verificationTokenRepository.delete(token);
            }
            guestRepository.deleteById(id);
        } catch (Exception exc) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deactivateGuestAccount(Long id) {
        Guest guest = guestRepository.getOne(id);

        if(guest.getActive()) {
            guest.setActive(false);
        } else {
            guest.setActive(true);
        }
        try {
            guestRepository.save(guest);
            return true;
        } catch (Exception exc) {
            return false;
        }
    }
}
