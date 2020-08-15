package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetRoomDto {

    private Long id;
    @NotNull
    @Min(1)
    private Integer roomNumber;
    @NotNull
    @Min(1)
    private Integer floorNumber;
    @NotBlank
    private String roomTypeName;
    @NotNull
    private Long hotelId;

}
