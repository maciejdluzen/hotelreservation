package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetHotelDtoJson {

    private Long id;

    private String name;

    private String street;

    private String number;

    private String postCode;

    private String city;

    private String emailAddress;

    private String phoneNumber;






}
