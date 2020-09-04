package pl.maciejdluzen.hotelreservation.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.maciejdluzen.hotelreservation.domain.entities.Guest;
import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;
import pl.maciejdluzen.hotelreservation.domain.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservations WHERE id = (SELECT max(id) FROM reservations) ", nativeQuery = true)
    Reservation findReservationWithGreatestId();

    List<Reservation> findAllReservationsByGuest(Guest guest);

//    @Query(value = "SELECT * FROM reservations WHERE hotel = :hotel AND check_in_date < :checkInDate")
//    List<Reservation> findAllFutureReservationsByHotel(@Param("hotel") Hotel hotel, @Param("todayDate") LocalDate todayDate);

    List<Reservation> findAllByHotelAndCheckInDateAfter(Hotel hotel, LocalDate localDate);


}
