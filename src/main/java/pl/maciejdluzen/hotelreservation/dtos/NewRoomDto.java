package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewRoomDto {

    private Integer roomNumber;

    private Integer floorNumber;

    private String roomTypeName;

    private Long hotelId;

}
