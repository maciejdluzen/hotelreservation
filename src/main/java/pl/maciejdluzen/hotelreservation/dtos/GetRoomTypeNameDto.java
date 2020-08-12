package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetRoomTypeNameDto {

    private Long id;

    private String name;

}
