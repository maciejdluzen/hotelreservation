package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetHotelDto {

    private Long id;

    private String name;

    private String address;

    private String emailAddress;

    private String phoneNumber;

}
