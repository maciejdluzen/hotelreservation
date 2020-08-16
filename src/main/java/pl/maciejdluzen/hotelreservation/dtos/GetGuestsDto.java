package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetGuestsDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String emailAddress;

    private Boolean active;

}
