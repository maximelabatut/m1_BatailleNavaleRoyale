package facade.mestests;

import exceptions.identification.DejaConnecteException;
import exceptions.identification.MotDePasseIncorrectException;
import exceptions.identification.PseudoDejaPrisException;
import exceptions.partie.JoueurInexistantException;
import exceptions.partie.PartieIntrouvableException;
import facade.Facade;
import facade.IFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class facadeLoginTest {

    IFacade maFacade;
    IFacade maFacade1Inscrit;

    @Before
    public void setMaFacade() throws PseudoDejaPrisException {
        this.maFacade = new Facade();
        this.maFacade1Inscrit = new Facade();
        maFacade1Inscrit.inscription("Jojo", "Jojo");
    }

    @Test
    public void testInscriptionOK() throws PseudoDejaPrisException {
        Assert.assertEquals("Personne ne doit être présent dans la database des joueurs", true, maFacade.getJoueurs().isEmpty());
        int monId = maFacade.inscription("Florian", "Florian");
        Assert.assertEquals("Il doit maintenant y avoir un joueur dans la base de données", 1, maFacade.getJoueurs().size());
        Assert.assertEquals("L'id récupéré doit être le même que l'id du joueur dans la BDD", true, maFacade.getJoueurs().containsKey(monId));
    }

    @Test(expected = PseudoDejaPrisException.class)
    public void testInscriptionKO() throws PseudoDejaPrisException {
        // On essaye de connecter un joueur déjà connecté
        maFacade1Inscrit.inscription("Jojo", "Jojo");
    }

    @Test
    public void testDesinscriptionOK() throws PseudoDejaPrisException, JoueurInexistantException {
        int monId = maFacade.inscription("Florian", "Florian");
        Assert.assertEquals("La liste des joueurs connectés contient 1 joueur", 1, maFacade.getJoueurs().size());
        maFacade.desinscription(monId);
        Assert.assertEquals("Plus personne ne doit être dans la DB", true, maFacade.getJoueurs().isEmpty());

    }

    @Test(expected = JoueurInexistantException.class)
    public void testDesinscriptionKO() throws JoueurInexistantException {
        maFacade.desinscription(-1);
    }

    @Test
    public void testConnexionOK() throws MotDePasseIncorrectException, DejaConnecteException {
        Assert.assertEquals("La liste doit être vide car personne connecté", true, maFacade1Inscrit.getJoueursEnLigne().isEmpty());
        maFacade1Inscrit.connexion("Jojo", "Jojo");
        Assert.assertEquals("Il doit y avoir maintenant 1 joueur connecté", 1, maFacade1Inscrit.getJoueursEnLigne().size());
    }

    @Test(expected = DejaConnecteException.class)
    public void testConnexionKO1() throws MotDePasseIncorrectException, DejaConnecteException {
        maFacade1Inscrit.connexion("Jojo", "Jojo");
        maFacade1Inscrit.connexion("Jojo", "Jojo");
    }

    @Test(expected = MotDePasseIncorrectException.class)
    public void testConnexionKO2() throws MotDePasseIncorrectException, DejaConnecteException {
        maFacade1Inscrit.connexion("Jojo", "blabla");
    }

    @Test
    public void testDeconnexionOK() throws MotDePasseIncorrectException, DejaConnecteException, PartieIntrouvableException {
        Assert.assertEquals("Personne ne devrait être connecté", 0, maFacade1Inscrit.getJoueursEnLigne().size());
        int id = maFacade1Inscrit.connexion("Jojo", "Jojo");
        Assert.assertEquals("Une personne doit être connectée", 1, maFacade1Inscrit.getJoueursEnLigne().size());
        maFacade1Inscrit.deconnexion(id);
        Assert.assertEquals("Le joueur doit être déconnecté", 0, maFacade1Inscrit.getJoueursEnLigne().size());
    }
}
