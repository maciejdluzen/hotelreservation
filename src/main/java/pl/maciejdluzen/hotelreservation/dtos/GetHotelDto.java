package pl.maciejdluzen.hotelreservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetHotelDto {

    private Long id;

    private String name;

    private String address;

    private String emailAddress;

    private String phoneNumber;

}
