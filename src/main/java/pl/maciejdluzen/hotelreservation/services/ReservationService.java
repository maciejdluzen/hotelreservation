package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.domain.entities.Reservation;
import pl.maciejdluzen.hotelreservation.dtos.CardDetailsDto;
import pl.maciejdluzen.hotelreservation.dtos.GetReservationsDto;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDetailsDto;
import pl.maciejdluzen.hotelreservation.dtos.ReservationDto;

import java.util.List;

public interface ReservationService {

    Reservation createReservation(ReservationDto reservationDto, CardDetailsDto cardDetailsDto);

    String reservationNumberBuilder(ReservationDto reservationDto);

    List<Double> calculateTotalCosts(ReservationDto reservationDto);

    List<GetReservationsDto> getAllReservationsByUsername(String username);

    ReservationDetailsDto getReservationDetails(Long id);

}
