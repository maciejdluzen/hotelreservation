package pl.maciejdluzen.hotelreservation.services;

import pl.maciejdluzen.hotelreservation.domain.entities.Reservation;
import pl.maciejdluzen.hotelreservation.dtos.*;

import java.util.List;

public interface ReservationService {

    Reservation createReservation(ReservationDto reservationDto, CardDetailsDto cardDetailsDto);

    String reservationNumberBuilder(ReservationDto reservationDto);

    List<Double> calculateTotalCosts(ReservationDto reservationDto);

    List<GetReservationsDto> getAllReservationsByUsername(String username);

    ReservationDetailsDto getReservationDetails(Long id);

    List<GetReservationsDto2> getAllFutureReservationsByHotel(String receptionistName);

    ReservationDetailsDto2 getReservationDetailsForReceptionist(Long id);

    Boolean confirmReservation(Long id);

    List<GetReservationsDto2> getAllCurrentReservationsByHotel(String username);

    List<GetReservationsDto2> getAllPastReservationsByHotel(String username);
}
