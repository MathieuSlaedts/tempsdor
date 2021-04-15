package be.tempsdor.tempsdor.exceptions;

public class RoomUnavailableException extends Exception{
    public RoomUnavailableException() {
        super("La chambre n'est pas disponible à ces dates.");
    }
}
