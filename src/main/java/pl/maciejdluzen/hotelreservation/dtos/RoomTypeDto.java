package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomTypeDto {

    private String name;

    private Integer noPersons;

    private Double rateNet;

    private Double tax;

    private Double rateGross;

    private String description;

    private String feature1;

    private String feature2;

    private String feature3;

    private String feature4;

    private byte[] image;

}
