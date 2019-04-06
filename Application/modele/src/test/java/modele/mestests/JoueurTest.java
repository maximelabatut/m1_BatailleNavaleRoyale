package modele.mestests;

import modele.Joueur;
import org.junit.Assert;
import org.junit.Test;

public class JoueurTest {

    Joueur monJoueur;
    String florian = "florian";

    @Test
    public void testCreationJoueur(){
        monJoueur = new Joueur(florian, florian);
        Assert.assertEquals("Le pseudo doit être \"florian\"", florian, monJoueur.getPseudo());
        Assert.assertEquals("Le mdp doit être \"florian\"", florian, monJoueur.getMdp());
        Assert.assertEquals("Le joueur doit posséder 5 bateaux", 5, monJoueur.getListeBateau().size());
        Assert.assertEquals("Le joueur doit avoir 5 points d'actions", 5, monJoueur.getPointactionrestant());
    }
}
