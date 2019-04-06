package modele.ia;

import modele.Joueur;

public class Noob extends Joueur implements IStrategieJeu {

    /**
     * Constructeur de la classe
     *
     */
    public Noob() {
        super("Bot_n00b", "");
    }

    /**
     * IA de niveau le plus faible
     * Comportement :
     * 1- Selectionner une position au hasard puis tire a cette position
     * 2- Passe son tour
     *
     * @return la liste des actions que va réaliser notre IA
     */


    @Override
    public void jouer() {
        //TODO : a faire
    }

}
