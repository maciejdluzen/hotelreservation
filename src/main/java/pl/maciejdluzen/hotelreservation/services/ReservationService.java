package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.dtos.CardDetailsDto;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDto;

import java.util.List;

public interface ReservationService {

    void createReservation(ReservationDto reservationDto, CardDetailsDto cardDetailsDto);

    String reservationNumberBuilder(ReservationDto reservationDto);

    List<Double> calculateTotalCosts(ReservationDto reservationDto);

}
