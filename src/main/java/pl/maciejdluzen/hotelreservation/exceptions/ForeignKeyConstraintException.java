package pl.maciejdluzen.hotelreservation.exceptions;

public class ForeignKeyConstraintException extends RuntimeException {

    public ForeignKeyConstraintException(String message) {
        super(message);
    }
}
