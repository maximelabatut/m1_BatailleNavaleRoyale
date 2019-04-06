package exceptions.bateau;

public class PasTonBateauException extends Exception {
    /**
     * Instantiates a new Deja connecte exception.
     */
    public PasTonBateauException() {
        super("Ce n'est pas ton bateau, tu ne peux pas le réparer");
    }
}
