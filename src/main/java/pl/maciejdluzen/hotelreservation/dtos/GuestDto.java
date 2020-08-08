package pl.maciejdluzen.hotelreservation.dtos;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.maciejdluzen.hotelreservation.validation.constraints.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class GuestDto {


    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @Email
    @UniqueEmail
    private String emailAddress;

    @NotNull
    @NotEmpty
    private String street;

    @NotNull
    @NotEmpty
    private String homeNumber;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String postCode;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @NotNull
    @NotEmpty
    private String password;

}
