package pl.maciejdluzen.hotelreservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.maciejdluzen.hotelreservation.validation.constraints.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewReceptionistDto {

    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    @NotBlank
    @Email
    @UniqueEmail
    private String emailAddress;
    @NotBlank
    private String hotelName;

    @NotBlank
    private String password;
}
