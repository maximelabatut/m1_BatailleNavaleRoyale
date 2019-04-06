package facade.mestests;

import exceptions.identification.PseudoDejaPrisException;
import exceptions.partie.*;
import facade.Facade;
import facade.IFacade;
import modele.Joueur;
import modele.Partie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class facadePartieTest {

    IFacade maFacade;
    int idJoueur1;
    int idJoueur2;
    Joueur joueur1;
    Joueur joueur2;

    @Before
    public void setMaFacade() throws PseudoDejaPrisException, JoueurInexistantException, TaillePlateauIncorrecteException, NbJoueursIncorrectException {
        this.maFacade = new Facade();
        idJoueur1 = maFacade.inscription("idJoueur1", "idJoueur1");
        idJoueur2 = maFacade.inscription("idJoueur2", "idJoueur2");
        joueur1 = maFacade.getJoueurById(idJoueur1);
        joueur2 = maFacade.getJoueurById(idJoueur2);
    }

    @Test
    public void testCreerPartieOK() throws TaillePlateauIncorrecteException, NbJoueursIncorrectException {
        Assert.assertEquals("La liste des parties doit être vide", 0, maFacade.getListeParties().size());
        maFacade.creerPartie(100,100, joueur1, 4);
        Assert.assertEquals("La facade doit maintenant contenir une partie", 1, maFacade.getListeParties().size());
        Assert.assertEquals("Le joueur hote doit se situer dans la partie", true, maFacade.getJoueursDansParties().containsKey(idJoueur1));
    }

    @Test(expected = TaillePlateauIncorrecteException.class)
    public void testCreerPartieKO1() throws TaillePlateauIncorrecteException, NbJoueursIncorrectException {
        maFacade.creerPartie(90,90, joueur1, 4);
    }

    @Test(expected = NbJoueursIncorrectException.class)
    public void testCreerPartieKO2() throws TaillePlateauIncorrecteException, NbJoueursIncorrectException {
        maFacade.creerPartie(100,100, joueur1, 6);
    }

    @Test
    public void testRejoindrePartieOK() throws TaillePlateauIncorrecteException, NbJoueursIncorrectException, NbJoueurMaxException, PartieIntrouvableException, JoueurInexistantException {
        maFacade.creerPartie(100,100, joueur1, 4);
        int idMaPartie = maFacade.getJoueursDansParties().get(idJoueur1);
        Partie maPartie = maFacade.getListeParties().get(idMaPartie);
        Assert.assertEquals("La partie contient 1 seul joueur", 1, maPartie.getMesJoueurs().size());
        maFacade.rejoindrePartie(idMaPartie, idJoueur2);
        maPartie = maFacade.getListeParties().get(idMaPartie);
        Assert.assertEquals("La partie contient 2 joueurs", 2, maPartie.getMesJoueurs().size());
    }

    @Test(expected = PartieIntrouvableException.class)
    public void testRejoindrePartieKO() throws TaillePlateauIncorrecteException, NbJoueursIncorrectException, NbJoueurMaxException, PartieIntrouvableException, JoueurInexistantException {
        maFacade.creerPartie(150,150, joueur1, 3);
        int idMaPartie = maFacade.getJoueursDansParties().get(idJoueur1);
        Partie maPartie = maFacade.getListeParties().get(idMaPartie);
        Assert.assertEquals("La partie contient 1 seul joueur", 1, maPartie.getMesJoueurs().size());
        maFacade.rejoindrePartie(-1, idJoueur2);
    }

}
