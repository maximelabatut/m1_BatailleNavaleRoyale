package exceptions.partie;

/**
 * The type Deja deconnecte exception.
 */
public class DejaDansUnePartieException extends Exception {

    /**
     * Instantiates a new Deja deconnecte exception.
     */
    public DejaDansUnePartieException() {
       super("La personne est deja dans une partie.");
    }

}
