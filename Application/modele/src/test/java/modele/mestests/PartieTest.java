package modele.mestests;

import exceptions.partie.*;
import modele.Joueur;
import modele.Partie;
import modele.Plateau;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PartieTest {

    Partie maPartie;
    Partie maPartieInitialisee0J;
    Partie maPartieInitialisee4J;
    Joueur hote;
    Joueur joueur1;
    Joueur joueur2;
    Joueur joueur3;

    String florian = "florian";
    String jonathan = "jonathan";
    String messagePartieNonLancee = "La partie ne doit pas être lancée";
    String messageUnSeulHote = "La liste des joueurs doit contenir seulement l'hote";

    @Before
    public void setApplication() throws TaillePlateauIncorrecteException, NbJoueurMaxException {
        hote = new Joueur(florian, florian);
        joueur1 = new Joueur(jonathan, jonathan);
        joueur2 = new Joueur("anthony", "anthony");
        joueur3 = new Joueur("quentin", "quentin");
        maPartieInitialisee0J = new Partie(100, 100, hote, 4);
        maPartieInitialisee4J = new Partie(100, 100, hote, 4);
        maPartieInitialisee4J.ajouterJoueur(joueur1);
        maPartieInitialisee4J.ajouterJoueur(joueur2);
        maPartieInitialisee4J.ajouterJoueur(joueur3);
    }

    @Test
    public void testCreationPartieOK() {
        Plateau monPlateau = maPartieInitialisee0J.getPlateau();
        Assert.assertEquals(messageUnSeulHote, 1, maPartieInitialisee0J.getMesJoueurs().size());
        Assert.assertEquals("La liste des joueurs doit contenir l'hote", florian, maPartieInitialisee0J.getHote().getPseudo());
        Assert.assertEquals("Il doit y avoir 5 points d'action", 5, maPartieInitialisee0J.getPointAction());
        Assert.assertEquals("Il doit y avoir 4 joueurs attendus", 4, maPartieInitialisee0J.getNbJoueurAttendu());
        Assert.assertEquals("La partie ne doit pas être terminée", false, maPartieInitialisee0J.isPartieTerminee());
        Assert.assertEquals(messagePartieNonLancee, false, maPartieInitialisee0J.isLancee());
        Assert.assertEquals("L'hote doit être le joueur ayant créé la partie", hote.getPseudo(), maPartieInitialisee0J.getHote().getPseudo());
        Assert.assertEquals("La largeur du plateau doit être de 100", 100, monPlateau.getLePlateau().length);
        Assert.assertEquals("La hauteur du plateau doit être de 100", 100, monPlateau.getLePlateau()[0].length);
    }

    @Test(expected = TaillePlateauIncorrecteException.class)
    public void testCreationPartieKO() throws TaillePlateauIncorrecteException {
        maPartie = new Partie(90, 90, hote, 4);
    }

    @Test
    public void testAjouterJoueurOK() throws NbJoueurMaxException {
        Assert.assertEquals(messageUnSeulHote, 1, maPartieInitialisee0J.getMesJoueurs().size());
        maPartieInitialisee0J.ajouterJoueur(joueur1);
        Assert.assertEquals("Un joueur devrait être ajouté", 2, maPartieInitialisee0J.getMesJoueurs().size());
        Assert.assertEquals("Le pseudo doit être \"Jonathan\"", jonathan, maPartieInitialisee0J.getMesJoueurs().get(1).getPseudo());
    }

    @Test(expected = NbJoueurMaxException.class)
    public void testAjouterJoueurKO() throws NbJoueurMaxException {
        Assert.assertEquals("La liste doit contenir 4 joueurs", 4, maPartieInitialisee4J.getMesJoueurs().size());
        Joueur monNouveauJoueur = new Joueur("maxime", "maxime");
        maPartieInitialisee4J.ajouterJoueur(monNouveauJoueur);
    }

    @Test
    public void testSupprimerJoueurOK() throws NbJoueursIncorrectException {
        Assert.assertEquals("La liste doit contenir 4 joueurs", 4, maPartieInitialisee4J.getMesJoueurs().size());
        int idJoueur = joueur1.getIdJoueur();
        maPartieInitialisee4J.supprimerJoueur(idJoueur);
        Assert.assertEquals("La liste doit contenir seulement 3 joueurs après suppression", 3, maPartieInitialisee4J.getMesJoueurs().size());
        Assert.assertEquals("Le bon joueur doit être supprimé", false, maPartieInitialisee4J.getMesJoueurs().contains(joueur1));
    }

    @Test(expected = NbJoueursIncorrectException.class)
    public void testSupprimerJoueurKO() throws NbJoueursIncorrectException {
        Assert.assertEquals(messageUnSeulHote, 1, maPartieInitialisee0J.getMesJoueurs().size());
        maPartieInitialisee0J.supprimerJoueur(hote.getIdJoueur());
        maPartieInitialisee0J.supprimerJoueur(0);
    }

    @Test
    public void testLancementPartieOK() throws NbJoueursIncorrectException, NbJoueurMaxException, TaillePlateauIncorrecteException {
        Assert.assertEquals(messageUnSeulHote, 1, maPartieInitialisee0J.getMesJoueurs().size());
        Assert.assertEquals(messagePartieNonLancee, false, maPartieInitialisee0J.isLancee());
        maPartieInitialisee0J.ajouterJoueur(joueur1);
        maPartieInitialisee0J.lancerPartie();
        Assert.assertEquals("La partie devrait être lancée", true, maPartieInitialisee0J.isLancee());
    }

    @Test(expected = NbJoueursIncorrectException.class)
    public void testLancementPartieKO() throws NbJoueursIncorrectException, NbJoueurMaxException, TaillePlateauIncorrecteException {
        Assert.assertEquals(messagePartieNonLancee, false, maPartieInitialisee0J.isLancee());
        maPartieInitialisee0J.lancerPartie();
    }

    @Test
    public void testGetPositionJoueurOK() throws JoueurInexistantException {
        Assert.assertEquals("L'id de l'hote doit être 1", 1, maPartieInitialisee4J.getPositionJoueur(hote.getIdJoueur()));
        Assert.assertEquals("L'id du joueur1 doit être 2", 2, maPartieInitialisee4J.getPositionJoueur(joueur1.getIdJoueur()));
        Assert.assertEquals("L'id du joueur2 doit être 3", 3, maPartieInitialisee4J.getPositionJoueur(joueur2.getIdJoueur()));
        Assert.assertEquals("L'id du joueur3 soit être 4", 4, maPartieInitialisee4J.getPositionJoueur(joueur3.getIdJoueur()));
    }

    @Test(expected = JoueurInexistantException.class)
    public void testGetPositionJoueurKO() throws JoueurInexistantException {
        maPartieInitialisee4J.getPositionJoueur(-1);
    }

    @Test
    public void testIsPair(){
        Assert.assertEquals("Ce nombre doit être pair donc true", true, maPartieInitialisee0J.isPair(4));
        Assert.assertEquals("Ce nombre ne doit pas être pair donc false", false, maPartieInitialisee0J.isPair(3));
    }

}
