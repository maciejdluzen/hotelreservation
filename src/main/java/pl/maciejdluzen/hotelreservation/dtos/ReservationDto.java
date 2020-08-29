package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,
 proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ReservationDto {

    private String username;

    private String reservationNumber;
    //@Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
    //@Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;
    //@NotBlank(message = "Pole nie może być puste")
    private String guestName;

    private String secondGuestName;

    private String thirdGuestName;

    private String fourthGuestName;

    private String message;
    //@NotBlank(message = "Pole nie może być puste")
    private String hotelName;
    //@NotBlank(message = "Pole nie może być puste")
    private String roomTypeName;

}
