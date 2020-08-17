package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.Receptionist;
import pl.maciejdluzen.hotelreservation.domain.repositories.ReceptionistRepository;
import pl.maciejdluzen.hotelreservation.dtos.GetReceptionistsDto;
import pl.maciejdluzen.hotelreservation.services.ReceptionistService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultReceptionistService implements ReceptionistService {

    private final ReceptionistRepository receptionistRepository;
    private final ModelMapper mapper;

    public DefaultReceptionistService(ReceptionistRepository receptionistRepository, ModelMapper mapper) {
        this.receptionistRepository = receptionistRepository;
        this.mapper = mapper;
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






}
