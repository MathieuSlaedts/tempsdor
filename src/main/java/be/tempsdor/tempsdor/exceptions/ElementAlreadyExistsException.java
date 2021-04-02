package be.tempsdor.tempsdor.exceptions;

public class ElementAlreadyExistsException extends Exception {
    public ElementAlreadyExistsException() {
        super("L'élément existe déjà.");
    }
}
