package modele;

import exceptions.partie.*;
import modele.bateau.Bateau;

public interface IPartie {

    void ajouterJoueur(Joueur j) throws NbJoueurMaxException;

    void supprimerJoueur(int idJoueur) throws NbJoueursIncorrectException;

    void lancerPartie() throws NbJoueursIncorrectException, TaillePlateauIncorrecteException;

    void placerBateau(int idJoueur, int posXMoteur, int posYMoteur, Bateau b, int angleOrientation) throws JoueurInexistantException, PositionBateauHorsPlateauException;

    boolean isPair(int number);

    int getPositionJoueur(int idJoueur) throws JoueurInexistantException;

}
