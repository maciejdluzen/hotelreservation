package pl.maciejdluzen.hotelreservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetReceptionistsDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String emailAddress;

    private String hotelName;

    private Boolean active;
}
