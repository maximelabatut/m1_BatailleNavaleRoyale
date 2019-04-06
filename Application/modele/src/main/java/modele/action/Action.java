package modele.action;

public abstract class Action implements IAction {

    /**
     * On va utiliser l'attribut priorite pour gerer l'ordre des actions
     * 1 : deplacer, pivoter
     * 2 : reparer
     * 3 : tir
     * 4 : passer
     */

    private int priorite;


}
