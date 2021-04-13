package be.tempsdor.tempsdor.exceptions;

public class ElementAlreadyExistsException extends Exception {
    public ElementAlreadyExistsException(String fieldname, String fieldvalue) {
        super(fieldname + " : la valeur [" + fieldvalue + "] existe déjà");
    }
}
