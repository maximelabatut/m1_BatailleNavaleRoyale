package exceptions.bateau;

public class BateauNonReparableException extends Exception {
    /**
     * Instantiates a new Bateau non réparable exception.
     */
    public BateauNonReparableException() {
        super("Cet endroit du bateau n'est pas cassé, il est donc impossible de le réparer");
    }
}
