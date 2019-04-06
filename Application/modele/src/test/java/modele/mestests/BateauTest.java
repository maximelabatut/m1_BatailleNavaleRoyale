package modele.mestests;

import exceptions.bateau.BateauNonReparableException;
import modele.Case;
import modele.bateau.Bateau;
import modele.bateau.FabriqueBateau;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BateauTest {

    FabriqueBateau maFabrique;
    Bateau monBateauContreTorpilleur;
    Bateau monBateauCroiseur;
    Bateau monBateauPorteAvion;
    Bateau monBateauSousMarin;
    Bateau monBateauTorpilleur;

    Bateau bateauCasesSens1;
    Bateau bateauCasesSens2;
    Bateau bateauCasesSens3;
    Bateau bateauCasesSens4;

    String largeur1 = "La largeur doit être de 1";
    String moteurNonTouche = "Le moteur n'est pas touché";
    String pasHorizontal = "Le bateau ne doit pas être horizontal";
    String estVertical = "Le bateau doit être vertical";
    String messageOrientationInit = "Le bateau doit avoir la position 1 à l'initialisation";

    @Before
    public void setApplication() {
        maFabrique = new FabriqueBateau();
        monBateauContreTorpilleur = maFabrique.creerBateau(1); // Création d'un ContreTorpilleur
        monBateauTorpilleur = maFabrique.creerBateau(2);       // Création d'un Tropilleur
        monBateauPorteAvion = maFabrique.creerBateau(3);   // Création d'un PorteAvion
        monBateauSousMarin = maFabrique.creerBateau(4);    // Création d'un SousMarin
        monBateauCroiseur = maFabrique.creerBateau(5);     // Création d'un Croiseur

        bateauCasesSens1 = maFabrique.creerBateau(1);
        bateauCasesSens1.setAngleOrientation(1);

        bateauCasesSens2 = maFabrique.creerBateau(1);
        bateauCasesSens2.setAngleOrientation(2);

        bateauCasesSens3 = maFabrique.creerBateau(1);
        bateauCasesSens3.setAngleOrientation(3);

        bateauCasesSens4 = maFabrique.creerBateau(4);
        bateauCasesSens4.setAngleOrientation(4);
    }

    @Test
    public void testBateauContreTorpilleur() {
        Assert.assertEquals("La longueur doit être de 3", 3, monBateauContreTorpilleur.getLongueur());
        Assert.assertEquals(largeur1, 1, monBateauContreTorpilleur.getLargeur());
        Assert.assertEquals("La vitesse doit être de 3", 3, monBateauContreTorpilleur.getVitesse());
        Assert.assertEquals("Le rayon doit être de 7", 7, monBateauContreTorpilleur.getRayon());
        Assert.assertEquals(moteurNonTouche, false, monBateauContreTorpilleur.isMoteurTouche());
        Assert.assertEquals(pasHorizontal, false, monBateauContreTorpilleur.isHorizontal());
        Assert.assertEquals(estVertical, true, monBateauContreTorpilleur.isVertical());
        Assert.assertEquals(messageOrientationInit, 1, monBateauContreTorpilleur.getAngleOrientation());
    }

    @Test
    public void testBateauTorpilleur() {
        Assert.assertEquals("La longueur doit être de 2", 2, monBateauTorpilleur.getLongueur());
        Assert.assertEquals(largeur1, 1, monBateauTorpilleur.getLargeur());
        Assert.assertEquals("La vitesse doit être de 4", 4, monBateauTorpilleur.getVitesse());
        Assert.assertEquals("Le rayon doit être de 5", 5, monBateauTorpilleur.getRayon());
        Assert.assertEquals(moteurNonTouche, false, monBateauTorpilleur.isMoteurTouche());
        Assert.assertEquals(pasHorizontal, false, monBateauTorpilleur.isHorizontal());
        Assert.assertEquals(estVertical, true, monBateauTorpilleur.isVertical());
        Assert.assertEquals(messageOrientationInit, 1, monBateauTorpilleur.getAngleOrientation());
    }

    @Test
    public void testBateauPorteAvion() {
        Assert.assertEquals("La longueur doit être de 5", 5, monBateauPorteAvion.getLongueur());
        Assert.assertEquals("La largeur doit être de 2", 2, monBateauPorteAvion.getLargeur());
        Assert.assertEquals("La vitesse doit être de 1", 1, monBateauPorteAvion.getVitesse());
        Assert.assertEquals("Le rayon doit être de 15", 15, monBateauPorteAvion.getRayon());
        Assert.assertEquals(moteurNonTouche, false, monBateauPorteAvion.isMoteurTouche());
        Assert.assertEquals(pasHorizontal, false, monBateauPorteAvion.isHorizontal());
        Assert.assertEquals(estVertical, true, monBateauPorteAvion.isVertical());
        Assert.assertEquals(messageOrientationInit, 1, monBateauPorteAvion.getAngleOrientation());
    }

    @Test
    public void testBateauSousMarin() {
        Assert.assertEquals("La longueur doit être de 3", 3, monBateauSousMarin.getLongueur());
        Assert.assertEquals(largeur1, 1, monBateauSousMarin.getLargeur());
        Assert.assertEquals("La vitesse doit être de 5", 5, monBateauSousMarin.getVitesse());
        Assert.assertEquals("Le rayon doit être de 3", 3, monBateauSousMarin.getRayon());
        Assert.assertEquals(moteurNonTouche, false, monBateauSousMarin.isMoteurTouche());
        Assert.assertEquals(pasHorizontal, false, monBateauSousMarin.isHorizontal());
        Assert.assertEquals(estVertical, true, monBateauSousMarin.isVertical());
        Assert.assertEquals(messageOrientationInit, 1, monBateauSousMarin.getAngleOrientation());
    }

    @Test
    public void testBateauCroiseur() {
        Assert.assertEquals("La longueur doit être de 4", 4, monBateauCroiseur.getLongueur());
        Assert.assertEquals(largeur1, 1, monBateauCroiseur.getLargeur());
        Assert.assertEquals("La vitesse doit être de 2", 2, monBateauCroiseur.getVitesse());
        Assert.assertEquals("Le rayon doit être de 10", 10, monBateauCroiseur.getRayon());
        Assert.assertEquals(moteurNonTouche, false, monBateauCroiseur.isMoteurTouche());
        Assert.assertEquals(pasHorizontal, false, monBateauCroiseur.isHorizontal());
        Assert.assertEquals(estVertical, true, monBateauCroiseur.isVertical());
        Assert.assertEquals(messageOrientationInit, 1, monBateauCroiseur.getAngleOrientation());
    }

    @Test
    public void testIsHorizontal() {
        Assert.assertEquals("Le bateau ne doit être horizontal après initialisation", false, monBateauContreTorpilleur.isHorizontal());
        monBateauContreTorpilleur.setAngleOrientation(2);
        Assert.assertEquals("Le bateau doit maintenant être horizontal avec 2", true, monBateauContreTorpilleur.isHorizontal());
        monBateauContreTorpilleur.setAngleOrientation(4);
        Assert.assertEquals("Le bateau doit toujours être horizontal avec 4", true, monBateauContreTorpilleur.isHorizontal());
    }

    @Test
    public void testIsVertical() {
        Assert.assertEquals("Le bateau doit être vertical à l'initialisation", true, monBateauContreTorpilleur.isVertical());
        monBateauContreTorpilleur.setAngleOrientation(2);
        Assert.assertEquals("Le bateau ne doit plus être vertical", false, monBateauContreTorpilleur.isVertical());
        monBateauContreTorpilleur.setAngleOrientation(3);
        Assert.assertEquals("Le bateau doit être vertical avec 3", true, monBateauContreTorpilleur.isVertical());
    }

    @Test
    public void testXGoalTete() {
        // Test pour orientation 1 et 3 (ou x pos tete bouge)
        monBateauCroiseur.setPosX(10);
        monBateauCroiseur.setPosY(10);
        int posTeteExpected = 7; // car la longueur du Croiseur est de 4
        Assert.assertEquals("La pos x tête doit être identique", posTeteExpected, monBateauCroiseur.getXGoalTete());
        monBateauCroiseur.setAngleOrientation(3);
        posTeteExpected = 13;
        Assert.assertEquals("La pos x tête doit être identique", posTeteExpected, monBateauCroiseur.getXGoalTete());

        // Test pour orientation 2 et 4 (ou x pos tete ne bouge pas)
        posTeteExpected = 10;
        monBateauCroiseur.setAngleOrientation(2);
        Assert.assertEquals("La pos x ne doit pas bouger", posTeteExpected, monBateauCroiseur.getXGoalTete());
        monBateauCroiseur.setAngleOrientation(4);
        Assert.assertEquals("La pos x ne doit toujours pas bouger", posTeteExpected, monBateauCroiseur.getXGoalTete());
    }

    @Test
    public void testYGoalTete() {
        // Test pour orientation 1 et 3 (ou y pos tête ne bouge pas)
        monBateauCroiseur.setPosX(10);
        monBateauCroiseur.setPosY(10);
        int posTeteExpected = 10;
        Assert.assertEquals("La pos y tête doit pas bouger", posTeteExpected, monBateauCroiseur.getYGoalTete());
        monBateauCroiseur.setAngleOrientation(3);
        Assert.assertEquals("La pos y tête doit toujours pas bouger", posTeteExpected, monBateauCroiseur.getYGoalTete());

        // Test pour orientation de 2 et 4 (ou y pos tête bouge)
        posTeteExpected = 13;
        monBateauCroiseur.setAngleOrientation(2);
        Assert.assertEquals("La pos y doit être identique", posTeteExpected, monBateauCroiseur.getYGoalTete());
        monBateauCroiseur.setAngleOrientation(4);
        posTeteExpected = 7;
        Assert.assertEquals("La pos y doit être identique", posTeteExpected, monBateauCroiseur.getYGoalTete());
    }

    @Test
    public void testIsCoule() {
        Assert.assertEquals("Le bateau ne doit être coulé", false, monBateauCroiseur.isCoule());
        // Pour toute la liste de dommage, on place un dommage
        for (int i = 0; i < monBateauCroiseur.getDommages().size(); i++) {
            monBateauCroiseur.setDommage(i, true);
        }
        Assert.assertEquals("Le bateau doit être coulé", true, monBateauCroiseur.isCoule());
    }

    @Test
    public void testReparerOK() throws BateauNonReparableException {
        // Pour toute la liste de dommage, on place un dommage
        for (int i = 0; i < monBateauSousMarin.getDommages().size(); i++) {
            monBateauSousMarin.setDommage(i, true);
        }
        Assert.assertEquals("Le bateau doit être coulé", true, monBateauSousMarin.isCoule());
        // Maintenant on répare
        monBateauSousMarin.reparer(1, 0);
        Assert.assertEquals("Le bateau ne doit plus être coulé car une case est réparée", false, monBateauSousMarin.isCoule());
    }

    @Test(expected = BateauNonReparableException.class)
    public void testReparerKO() throws BateauNonReparableException {
        // On essaye de réparer un bateau qui est neuf
        monBateauCroiseur.reparer(0, 0);
    }

    @Test
    public void testGetDommage() {
        monBateauTorpilleur.setDommage(1, true);
        Assert.assertEquals("Le bateau doit avoir des dommages", true, monBateauTorpilleur.getDommage(1));
        Assert.assertEquals("Le bateau ne doit pas avoir de dommages", false, monBateauTorpilleur.getDommage(0));
    }

    @Test
    public void testPivoterPlus90DegresOrientation1() {
        // Bateau de taille paire (ici 4)
        monBateauCroiseur.setPosX(10);
        monBateauCroiseur.setPosY(10);
        Assert.assertEquals("L'orientation de mon bateau est de 1", 1, monBateauCroiseur.getAngleOrientation());
        monBateauCroiseur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 9", 9, monBateauCroiseur.getPosX());
        Assert.assertEquals("La position de y doit être de 9", 9, monBateauCroiseur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 2", 2, monBateauCroiseur.getAngleOrientation());

        // Bateau de taille impaire (ici 3)
        monBateauContreTorpilleur.setPosX(9);
        monBateauContreTorpilleur.setPosY(9);
        Assert.assertEquals("L'orientation de mon bateau est de 1", 1, monBateauContreTorpilleur.getAngleOrientation());
        monBateauContreTorpilleur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 8", 8, monBateauContreTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 8", 8, monBateauContreTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 2", 2, monBateauContreTorpilleur.getAngleOrientation());

        // Bateau de taille paire (ici 2 ou M ne doit pas bouger)
        monBateauTorpilleur.setPosX(15);
        monBateauTorpilleur.setPosY(15);
        Assert.assertEquals("L'orientation de mon bateau est de 1", 1, monBateauTorpilleur.getAngleOrientation());
        monBateauTorpilleur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 10", 15, monBateauTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 10", 15, monBateauTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 2", 2, monBateauTorpilleur.getAngleOrientation());

    }

    @Test
    public void testPivoterPlus90DegresOrientation2() {
        // Bateau de taille paire (ici 2 ou M ne doit pas bouger)
        monBateauTorpilleur.setPosX(10);
        monBateauTorpilleur.setPosY(10);
        monBateauTorpilleur.setAngleOrientation(2);
        Assert.assertEquals("L'orientation de mon bateau est de 2", 2, monBateauTorpilleur.getAngleOrientation());
        monBateauTorpilleur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 10", 10, monBateauTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 10", 10, monBateauTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 3", 3, monBateauTorpilleur.getAngleOrientation());

        // Bateau de taille impaire (ici 3)
        monBateauContreTorpilleur.setPosX(8);
        monBateauContreTorpilleur.setPosY(8);
        monBateauContreTorpilleur.setAngleOrientation(2);
        Assert.assertEquals("L'orientation de mon bateau est de 2", 2, monBateauContreTorpilleur.getAngleOrientation());
        monBateauContreTorpilleur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 7", 7, monBateauContreTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 11", 9, monBateauContreTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 3", 3, monBateauContreTorpilleur.getAngleOrientation());
    }

    @Test
    public void testPivoterPlus90DegresOrientation3(){
        // Bateau de taille paire (ici 4)
        monBateauCroiseur.setPosX(50);
        monBateauCroiseur.setPosY(50);
        monBateauCroiseur.setAngleOrientation(3);
        Assert.assertEquals("L'orientation de mon bateau est de 3", 3, monBateauCroiseur.getAngleOrientation());
        monBateauCroiseur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 51", 51, monBateauCroiseur.getPosX());
        Assert.assertEquals("La position de y doit être de 51", 51, monBateauCroiseur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 4", 4, monBateauCroiseur.getAngleOrientation());

        // Bateau de taille impaire (ici 3)
        monBateauContreTorpilleur.setPosX(70);
        monBateauContreTorpilleur.setPosY(70);
        monBateauContreTorpilleur.setAngleOrientation(3);
        Assert.assertEquals("L'orientation de mon bateau est de 3", 3, monBateauContreTorpilleur.getAngleOrientation());
        monBateauContreTorpilleur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 71", 71, monBateauContreTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 71", 71, monBateauContreTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 4", 4, monBateauContreTorpilleur.getAngleOrientation());

        // Bateau de taille paire (ici 2 ou M ne doit pas bouger)
        monBateauTorpilleur.setPosX(35);
        monBateauTorpilleur.setPosY(35);
        monBateauTorpilleur.setAngleOrientation(3);
        Assert.assertEquals("L'orientation de mon bateau est de 3", 3, monBateauTorpilleur.getAngleOrientation());
        monBateauTorpilleur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 10", 35, monBateauTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 10", 35, monBateauTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 4", 4, monBateauTorpilleur.getAngleOrientation());
    }

    @Test
    public void testPivoterPlus90DegresOrientation4(){
        // Bateau de taille paire (ici 4)
        monBateauCroiseur.setPosX(80);
        monBateauCroiseur.setPosY(80);
        monBateauCroiseur.setAngleOrientation(4);
        Assert.assertEquals("L'orientation de mon bateau est de 4", 4, monBateauCroiseur.getAngleOrientation());
        monBateauCroiseur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 81", 81, monBateauCroiseur.getPosX());
        Assert.assertEquals("La position de y doit être de 79", 79, monBateauCroiseur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 1", 1, monBateauCroiseur.getAngleOrientation());

        // Bateau de taille impaire (ici 3)
        monBateauContreTorpilleur.setPosX(60);
        monBateauContreTorpilleur.setPosY(60);
        monBateauContreTorpilleur.setAngleOrientation(4);
        Assert.assertEquals("L'orientation de mon bateau est de 4", 4, monBateauContreTorpilleur.getAngleOrientation());
        monBateauContreTorpilleur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 61", 61, monBateauContreTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 59", 59, monBateauContreTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 1", 1, monBateauContreTorpilleur.getAngleOrientation());

        // Bateau de taille paire (ici 2 ou M ne doit pas bouger)
        monBateauTorpilleur.setPosX(27);
        monBateauTorpilleur.setPosY(27);
        monBateauTorpilleur.setAngleOrientation(4);
        Assert.assertEquals("L'orientation de mon bateau est de 4", 4, monBateauTorpilleur.getAngleOrientation());
        monBateauTorpilleur.pivoterBateauPlus90Degres();
        Assert.assertEquals("La position de x doit être de 10", 27, monBateauTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 10", 27, monBateauTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 1", 1, monBateauTorpilleur.getAngleOrientation());
    }

    @Test
    public void testPivoterPlus90MoinsOrientation1() {
        // Bateau de taille paire (ici 4)
        monBateauCroiseur.setPosX(6);
        monBateauCroiseur.setPosY(6);
        Assert.assertEquals("L'orientation de mon bateau est de 1", 1, monBateauCroiseur.getAngleOrientation());
        monBateauCroiseur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 5", 5, monBateauCroiseur.getPosX());
        Assert.assertEquals("La position de y doit être de 7", 7, monBateauCroiseur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 4", 4, monBateauCroiseur.getAngleOrientation());

        // Bateau de taille impaire (ici 3)
        monBateauContreTorpilleur.setPosX(44);
        monBateauContreTorpilleur.setPosY(44);
        Assert.assertEquals("L'orientation de mon bateau est de 1", 1, monBateauContreTorpilleur.getAngleOrientation());
        monBateauContreTorpilleur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 43", 43, monBateauContreTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 45", 45, monBateauContreTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 4", 4, monBateauContreTorpilleur.getAngleOrientation());

        // Bateau de taille paire (ici 2 ou M ne doit pas bouger)
        monBateauTorpilleur.setPosX(27);
        monBateauTorpilleur.setPosY(27);
        Assert.assertEquals("L'orientation de mon bateau est de 1", 1, monBateauTorpilleur.getAngleOrientation());
        monBateauTorpilleur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 27", 27, monBateauTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 27", 27, monBateauTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 4", 4, monBateauTorpilleur.getAngleOrientation());

    }

    @Test
    public void testPivoterMoins90DegreOrientation2() {
        // Bateau de taille paire (ici 2 ou M ne doit pas bouger)
        monBateauTorpilleur.setPosX(52);
        monBateauTorpilleur.setPosY(52);
        monBateauTorpilleur.setAngleOrientation(2);
        Assert.assertEquals("L'orientation de mon bateau est de 2", 2, monBateauTorpilleur.getAngleOrientation());
        monBateauTorpilleur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 52", 52, monBateauTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 52", 52, monBateauTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 1", 1, monBateauTorpilleur.getAngleOrientation());

        // Bateau de taille impaire (ici 3)
        monBateauContreTorpilleur.setPosX(88);
        monBateauContreTorpilleur.setPosY(88);
        monBateauContreTorpilleur.setAngleOrientation(2);
        Assert.assertEquals("L'orientation de mon bateau est de 2", 2, monBateauContreTorpilleur.getAngleOrientation());
        monBateauContreTorpilleur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 89", 89, monBateauContreTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 89", 89, monBateauContreTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 1", 1, monBateauContreTorpilleur.getAngleOrientation());
    }

    @Test
    public void testPivoterMoins90DegresOrientation3(){
        // Bateau de taille paire (ici 4)
        monBateauCroiseur.setPosX(67);
        monBateauCroiseur.setPosY(67);
        monBateauCroiseur.setAngleOrientation(3);
        Assert.assertEquals("L'orientation de mon bateau est de 3", 3, monBateauCroiseur.getAngleOrientation());
        monBateauCroiseur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 68", 68, monBateauCroiseur.getPosX());
        Assert.assertEquals("La position de y doit être de 66", 66, monBateauCroiseur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 2", 2, monBateauCroiseur.getAngleOrientation());

        // Bateau de taille impaire (ici 3)
        monBateauContreTorpilleur.setPosX(82);
        monBateauContreTorpilleur.setPosY(82);
        monBateauContreTorpilleur.setAngleOrientation(3);
        Assert.assertEquals("L'orientation de mon bateau est de 3", 3, monBateauContreTorpilleur.getAngleOrientation());
        monBateauContreTorpilleur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 83", 83, monBateauContreTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 81", 81, monBateauContreTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 2", 2, monBateauContreTorpilleur.getAngleOrientation());

        // Bateau de taille paire (ici 2 ou M ne doit pas bouger)
        monBateauTorpilleur.setPosX(51);
        monBateauTorpilleur.setPosY(51);
        monBateauTorpilleur.setAngleOrientation(3);
        Assert.assertEquals("L'orientation de mon bateau est de 3", 3, monBateauTorpilleur.getAngleOrientation());
        monBateauTorpilleur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 51", 51, monBateauTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 51", 51, monBateauTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 2", 2, monBateauTorpilleur.getAngleOrientation());
    }

    @Test
    public void testPivoterMoins90DegresOrientation4(){
        // Bateau de taille paire (ici 4)
        monBateauCroiseur.setPosX(29);
        monBateauCroiseur.setPosY(29);
        monBateauCroiseur.setAngleOrientation(4);
        Assert.assertEquals("L'orientation de mon bateau est de 4", 4, monBateauCroiseur.getAngleOrientation());
        monBateauCroiseur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 28", 28, monBateauCroiseur.getPosX());
        Assert.assertEquals("La position de y doit être de 28", 28, monBateauCroiseur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 3", 3, monBateauCroiseur.getAngleOrientation());

        // Bateau de taille impaire (ici 3)
        monBateauContreTorpilleur.setPosX(72);
        monBateauContreTorpilleur.setPosY(72);
        monBateauContreTorpilleur.setAngleOrientation(4);
        Assert.assertEquals("L'orientation de mon bateau est de 4", 4, monBateauContreTorpilleur.getAngleOrientation());
        monBateauContreTorpilleur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 71", 71, monBateauContreTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 71", 71, monBateauContreTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 3", 3, monBateauContreTorpilleur.getAngleOrientation());

        // Bateau de taille paire (ici 2 ou M ne doit pas bouger)
        monBateauTorpilleur.setPosX(5);
        monBateauTorpilleur.setPosY(5);
        monBateauTorpilleur.setAngleOrientation(4);
        Assert.assertEquals("L'orientation de mon bateau est de 4", 4, monBateauTorpilleur.getAngleOrientation());
        monBateauTorpilleur.pivoterBateauMoins90Degres();
        Assert.assertEquals("La position de x doit être de 5", 5, monBateauTorpilleur.getPosX());
        Assert.assertEquals("La position de y doit être de 5", 5, monBateauTorpilleur.getPosY());
        Assert.assertEquals("L'orientation du bateau doit être passée à 3", 3, monBateauTorpilleur.getAngleOrientation());
    }

    @Test
    public void testGetAllCasesCouvertesBateauSimpleOK(){
        List<Case> liste = bateauCasesSens1.getAllCasesCouvertes();
        int longueur = bateauCasesSens1.getLongueur();
        Assert.assertEquals("La longueur = nb case qu'il couvre", longueur, liste.size());

        List<Case> liste2 = bateauCasesSens2.getAllCasesCouvertes();
        int longueur2 = bateauCasesSens2.getLongueur();
        Assert.assertEquals("La longueur = nb case qu'il couvre", longueur2, liste2.size());

        List<Case> liste3 = bateauCasesSens3.getAllCasesCouvertes();
        int longueur3 = bateauCasesSens3.getLongueur();
        Assert.assertEquals("La longueur = nb case qu'il couvre", longueur3, liste3.size());

        List<Case> liste4 = bateauCasesSens4.getAllCasesCouvertes();
        int longueur4 = bateauCasesSens4.getLongueur();
        Assert.assertEquals("La longueur = nb case qu'il couvre", longueur4, liste4.size());
    }

    @Test
    public void testGetMaVueOK(){
        //TODO :
    }

    @Test
    public void testGetMaVueBateauLongueur2_OK(){
        //TODO :
    }

    @Test
    public void testGetMaVueBateauLongueur2Largeur2_OK(){
        //TODO :
    }

}

