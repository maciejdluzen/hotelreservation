package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Receptionist;
import pl.maciejdluzen.hotelreservation.domain.repositories.HotelRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.ReceptionistRepository;
import pl.maciejdluzen.hotelreservation.domain.repositories.RoleRepository;
import pl.maciejdluzen.hotelreservation.dtos.GetReceptionistsDto;
import pl.maciejdluzen.hotelreservation.dtos.NewReceptionistDto;
import pl.maciejdluzen.hotelreservation.services.ReceptionistService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultReceptionistService implements ReceptionistService {

    private final ReceptionistRepository receptionistRepository;
    private final ModelMapper mapper;
    private final HotelRepository hotelRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public DefaultReceptionistService(ReceptionistRepository receptionistRepository, ModelMapper mapper, HotelRepository hotelRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.receptionistRepository = receptionistRepository;
        this.mapper = mapper;
        this.hotelRepository = hotelRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<GetReceptionistsDto> getAllReceptionists() {
        List<Receptionist> receptionists = receptionistRepository.findAllByOrderByLastName();
        List<GetReceptionistsDto> receptionistsDto = new ArrayList<>();
        for(Receptionist receptionist : receptionists) {
            GetReceptionistsDto receptionistDto = mapper.map(receptionist, GetReceptionistsDto.class);
            receptionistsDto.add(receptionistDto);
        }
        return receptionistsDto;
    }

    @Override
    public Receptionist createReceptionistAccount(NewReceptionistDto receptionistDto) {
        Receptionist receptionist = mapper.map(receptionistDto, Receptionist.class);
        String encodedPassword = passwordEncoder.encode(receptionistDto.getPassword());
        receptionist.setHotel(hotelRepository.findHotelByName(receptionistDto.getHotelName()));
        receptionist.setUsername(receptionistDto.getEmailAddress());
        receptionist.setPassword(encodedPassword);
        receptionist.setRole(roleRepository.getByName("ROLE_RECEPTIONIST"));
        return receptionistRepository.save(receptionist);
    }

    @Override
    public boolean deactivateReceptionistAccount(Long id) {
        Receptionist receptionist = receptionistRepository.getOne(id);

        if(receptionist.getActive()) {
            receptionist.setActive(false);
        } else {
            receptionist.setActive(true);
        }
        try {
            receptionistRepository.save(receptionist);
            return true;
        } catch (Exception exc) {
            return false;
        }
    }
}
