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

    private Long id;
    @NotBlank(message = "Pole \"Nazwa hotelu nie może być puste\" ")
    private String name;
    @Positive(message = "Wartość pola \"Liczba gości\" musi być dodatnia")
    @Max(value = 5, message = "Wartość pola \"Liczba gości\" musi być mniejsza niż 5")
    @NotNull(message = "Wartość pola \"Liczba gości\" nie może pozostać pusta")
    private Integer noPersons;
    @PositiveOrZero(message = "Wartość pola \"Cena netto\" musi być dodatnia")
    @NotNull(message = "Wartość pola \"Cena netto\" nie może pozostać pusta")
    private Double rateNet;
    @PositiveOrZero(message = "Wartość pola \"VAT\" musi być dodatnia")
    @Max(value = 1, message = "Wartość pola \"VAT\" musi być mniejsza niż 5")
    @NotNull(message = "Wartość pola \"VAT\" nie może pozostać pusta")
    private Double tax;

    private Double rateGross;

    @NotBlank(message = "Pole \"Opis\" nie może być puste")
    private String description;

    private String feature1;

    private String feature2;

    private String feature3;

    private String feature4;

    private Long imageId;

}
