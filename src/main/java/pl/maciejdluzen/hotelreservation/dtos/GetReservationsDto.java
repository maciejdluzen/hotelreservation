package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetReservationsDto {

    public Long id;

    public String reservationNumber;

    public String hotelName;

    public LocalDate checkInDate;

    public LocalDate checkOutDate;

    private Boolean status;

}
