package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;
import pl.maciejdluzen.hotelreservation.domain.entities.Image;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomTypeDto {

    @NotBlank
    private String name;
    @Positive @Max(5)
    @NotNull
    private Integer noPersons;
    @PositiveOrZero
    @NotNull
    private Double rateNet;
    @PositiveOrZero @Max(1)
    @NotNull
    private Double tax;
    @NotBlank
    private String description;

    private String feature1;

    private String feature2;

    private String feature3;

    private String feature4;

    private Long imageId;

}
