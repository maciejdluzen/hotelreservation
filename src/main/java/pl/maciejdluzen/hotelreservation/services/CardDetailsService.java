package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.domain.entities.CardDetails;
import pl.maciejdluzen.hotelreservation.dtos.CardDetailsDto;

public interface CardDetailsService {

    void saveCardDetails(CardDetailsDto cardDetailsDto);



}
