package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetReservationsDto2 {

    public Long id;

    public String reservationNumber;

    public LocalDate checkInDate;

    public LocalDate checkOutDate;

    private Boolean status;

    private String guestFirstName;

    private String guestLastName;

}
