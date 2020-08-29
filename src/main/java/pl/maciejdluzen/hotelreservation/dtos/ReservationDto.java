package pl.maciejdluzen.hotelreservation.dtos;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;

    private String guestName;

    private String secondGuestName;

    private String thirdGuestName;

    private String fourthGuestName;

    private String message;

    private String hotelName;

    private String roomTypeName;

}
