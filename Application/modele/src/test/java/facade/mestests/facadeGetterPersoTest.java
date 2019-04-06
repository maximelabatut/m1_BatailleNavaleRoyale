package facade.mestests;

import exceptions.identification.PseudoDejaPrisException;
import exceptions.partie.JoueurInexistantException;
import facade.Facade;
import facade.IFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class facadeGetterPersoTest {

    IFacade maFacade;
    int jojo;
    int floflo;

    @Before
    public void setMaFacade() throws PseudoDejaPrisException {
        maFacade = new Facade();
        jojo = maFacade.inscription("Jojo", "Jojo");
        floflo = maFacade.inscription("FloFlo", "FloFlo");
    }

    @Test
    public void testGetJoueurByIdOK() throws JoueurInexistantException {
        Assert.assertEquals("Le pseudo du joueur doit être Jojo", "Jojo", maFacade.getJoueurById(jojo).getPseudo());
        Assert.assertEquals("Le pseudi du joueur doit être FloFlo", "FloFlo", maFacade.getJoueurById(floflo).getPseudo());
    }

    @Test(expected = JoueurInexistantException.class)
    public void testGetJoueurByIdKO() throws JoueurInexistantException {
        maFacade.getJoueurById(-1); // On test avec un id impossible pour tester l'exception
    }
}
