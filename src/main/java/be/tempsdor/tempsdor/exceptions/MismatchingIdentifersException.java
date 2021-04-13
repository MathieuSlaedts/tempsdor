package be.tempsdor.tempsdor.exceptions;

public class MismatchingIdentifersException extends Exception {
    public MismatchingIdentifersException() {
        super("Les identifiants en body et en paramètre ne correspondent pas.");
    }
}
