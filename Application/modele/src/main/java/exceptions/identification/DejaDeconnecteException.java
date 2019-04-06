package exceptions.identification;

/**
 * The type Deja deconnecte exception.
 */
public class DejaDeconnecteException extends Exception {

    /**
     * Instantiates a new Deja deconnecte exception.
     */
    public DejaDeconnecteException() {
       super("La personne est deja deconnectee.");
    }

}
