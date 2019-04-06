package modele.ia;

import modele.action.IAction;

import java.util.List;

public interface IStrategieJeu {

    /**
     * On vient utiliser le pattern strategy pour la gestion des niveaux d'IA
     * L'interface definit le cadre
     * et les classes qui vont impl vont proposer un codage pour jouer()
     */

    //List<IAction> jouer();

    void jouer();
}
