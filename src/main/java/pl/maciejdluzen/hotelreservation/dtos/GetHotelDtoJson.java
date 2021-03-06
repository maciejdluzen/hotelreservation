package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetHotelDtoJson {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String street;
    @NotBlank
    private String number;
    @NotBlank
    private String postCode;
    @NotBlank
    private String city;
    @NotBlank
    @Email
    private String emailAddress;
    @NotBlank
    private String phoneNumber;






}
