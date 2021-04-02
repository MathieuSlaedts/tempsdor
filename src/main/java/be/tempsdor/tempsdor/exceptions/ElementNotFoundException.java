package be.tempsdor.tempsdor.exceptions;

public class ElementNotFoundException extends Exception {
    public ElementNotFoundException() {
        super("L'élément n'as pas été trouvé.");
    }
}
