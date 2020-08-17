package pl.maciejdluzen.hotelreservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.maciejdluzen.hotelreservation.validation.constraints.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewReceptionistDto {

    @NotEmpty
    private String lastName;
    @NotEmpty
    private String firstName;
    @NotEmpty
    @Email
    @UniqueEmail
    private String emailAddress;
    @NotEmpty
    private String hotelName;

    private Boolean status;
    @NotEmpty
    private String password;
}
