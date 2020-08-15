package pl.maciejdluzen.hotelreservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditHotelDto {

    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String street;
    @NotNull
    @NotBlank
    private String number;
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String postCode;
    @NotNull
    @NotBlank
    private String phoneNumber;
    @NotNull
    @NotBlank
    @Email
    private String emailAddress;

}
