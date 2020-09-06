package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDetailsDto2 {

    private String reservationNumber;

    private String secondGuestName;

    private String thirdGuestName;

    private String fourthGuestName;

    private Double totalNetCost;

    private Double tax;

    private Double totalGrossCost;

    private String roomTypeName;

    private String message;

    private String guestUsername;

    private String guestStreet;

    private String guestHomeNumber;

    private String guestCity;

    private String guestPostCode;

    private String guestPhoneNumber;

}
