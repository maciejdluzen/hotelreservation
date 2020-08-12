package pl.maciejdluzen.hotelreservation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRoomDto {

    private Long id;

    private Integer roomNumber;

    private Integer floorNumber;

    private String roomTypeName;

}
