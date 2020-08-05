package pl.maciejdluzen.hotelreservation.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String emailAddress) {
        super("Użytkownik z danym adresem mailowym " + emailAddress + " już istnieje!");
    }
}
