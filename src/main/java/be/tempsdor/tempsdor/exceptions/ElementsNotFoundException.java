package be.tempsdor.tempsdor.exceptions;

public class ElementsNotFoundException extends Exception {

    public ElementsNotFoundException() {
        super("Les éléments n'ont pas été trouvé.");
    }
}
