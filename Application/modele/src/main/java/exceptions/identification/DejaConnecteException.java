package exceptions.identification;

/**
 * The type Deja connecte exception.
 */
public class DejaConnecteException extends Exception {

    /**
     * Instantiates a new Deja connecte exception.
     */
    public DejaConnecteException() {
        super("La personne est deja connectee.");
    }

}
