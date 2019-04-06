package modele.mestests;

import exceptions.partie.TaillePlateauIncorrecteException;
import modele.Case;
import modele.Plateau;
import org.junit.Assert;
import org.junit.Test;

public class PlateauTest {

    Plateau plateau;

    @Test
    public void creerPlateauOK() throws TaillePlateauIncorrecteException {
        plateau = new Plateau(100,100);
        Case[][] monPlateau = plateau.getLePlateau();
        for(int i=0; i<monPlateau.length; i++){
            for(int j=0; j<monPlateau[i].length; j++){
                Assert.assertEquals("Le plateau devrai être de l'eau en [" + i + "] [" + j + "]",true, monPlateau[i][j].isEau());
            }
        }
    }

    @Test(expected = TaillePlateauIncorrecteException.class)
    public void creerPlateauKO() throws TaillePlateauIncorrecteException {
        plateau = new Plateau(90, 90);
    }

    @Test
    public void testGetTaillePlateau() throws TaillePlateauIncorrecteException {
        plateau = new Plateau(150,150);
        Assert.assertEquals("La taille doit être égale à 150", 150, plateau.getTaillePlateau());
    }

    @Test
    public void testCopy() throws TaillePlateauIncorrecteException {
        plateau = new Plateau(100, 100);
        Case[][] monPlateauInitial = plateau.getLePlateau();
        Case[][] laCopy = plateau.copy();
        Assert.assertEquals("La taille des deux tableaux doit être identique", monPlateauInitial.length, laCopy.length);

        for(int i = 0; i < plateau.getTaillePlateau(); i++){
            for(int j = 0; j < plateau.getTaillePlateau(); j++){
                Case caseInitiale = monPlateauInitial[i][j];
                Case caseCopy = laCopy[i][j];
                Assert.assertNotSame("Les cases doivent pas avoir la même adresse car c'est une copie", caseInitiale, caseCopy);
                Assert.assertEquals("La coordonnée x doit être identique", caseInitiale.getX(), caseCopy.getX());
                Assert.assertEquals("La coordonnée y doit être identique", caseInitiale.getY(), caseCopy.getY());
                Assert.assertEquals("La situation de l'eau doit être identique", caseInitiale.isEau(), caseCopy.isEau());
                Assert.assertEquals("Le path doit être identique", caseInitiale.getImgPath(), caseCopy.getImgPath());
            }
        }
    }
}
