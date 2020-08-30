package pl.maciejdluzen.hotelreservation.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.maciejdluzen.hotelreservation.domain.entities.CardDetails;
import pl.maciejdluzen.hotelreservation.domain.repositories.CardDetailsRepository;
import pl.maciejdluzen.hotelreservation.dtos.CardDetailsDto;
import pl.maciejdluzen.hotelreservation.services.CardDetailsService;

@Service
public class DefaultCardDetailsService implements CardDetailsService {

    private final CardDetailsRepository cardDetailsRepository;
    private final ModelMapper mapper;

    public DefaultCardDetailsService(CardDetailsRepository cardDetailsRepository, ModelMapper mapper) {
        this.cardDetailsRepository = cardDetailsRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveCardDetails(CardDetailsDto cardDetailsDto) {
        CardDetails cardDetails = mapper.map(cardDetailsDto, CardDetails.class);
        cardDetailsRepository.save(cardDetails);
    }
}
