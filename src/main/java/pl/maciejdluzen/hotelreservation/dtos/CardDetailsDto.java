package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardDetailsDto {

    private Long id;
    @NotBlank(message = "Pole jest wymagane")
    private String nameOnTheCard;
    @Length(min = 16, max = 19, message = "Niepoprawna dane")
    @NotBlank(message = "Pole jest wymagane")
    private String cardNumber;
    @Length(min = 5, max = 5, message = "Niepoprawna dane")
    @NotBlank(message = "Pole jest wymagane")
    private String expiryDate;
    //@Length(min = 3, max = 3, message = "Niepoprawne dane")
    @Digits(integer = 3, fraction = 0)
    @NotNull(message = "Pole jest wymagane")
    private Integer securityCode;

}
