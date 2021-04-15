package be.tempsdor.tempsdor.exceptions;

public class NullPropertyException extends Exception{
    public NullPropertyException(String property) {
        super("La propriété [" + property + "] ne peut pas être null.");
    }
}
