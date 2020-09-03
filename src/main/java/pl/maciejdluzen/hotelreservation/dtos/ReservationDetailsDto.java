package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;
import pl.maciejdluzen.hotelreservation.domain.entities.Hotel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDetailsDto {

    private String reservationNumber;

    private String secondGuestName;

    private String thirdGuestName;

    private String fourthGuestName;

    private Double totalNetCost;

    private Double tax;

    private Double totalGrossCost;

    private String roomTypeName;

    private String message;

    private String hotelName;

    private String hotelStreet;

    private String hotelNumber;

    private String hotelCity;

    private String hotelPostCode;

    private String hotelEmailAddress;

    private String hotelPhoneNumber;
}
