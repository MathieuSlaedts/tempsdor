package be.tempsdor.tempsdor.exceptions;

public class OwnRoomBookingException extends Exception {
    public OwnRoomBookingException() {
        super("Il n'est pas possible de louer ses propres chambres.");
    }
}
