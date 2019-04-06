package modele;

import modele.bateau.Bateau;
import modele.bateau.FabriqueBateau;

import java.util.ArrayList;
import java.util.List;

public class Joueur implements IJoueur {

    private static int id = 0;
    private FabriqueBateau fabriqueBateau;

    private int idJoueur;
    private String pseudo;
    private String mdp;
    private List<Bateau> listeBateau;
    private final int pointActionRestant = 5;

    /**
     * Constructeur de la classe
     * @param pseudo
     * @param mdp
     */
    public Joueur(String pseudo, String mdp){
        this.idJoueur = id;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.fabriqueBateau = new FabriqueBateau();
        initialiserBateaux();
        id++;
    }

    /**
     * afin de décharger le constructeur
     * cette methode vient initialiser la liste des bateaux d'un joueur en
     * tapant dans la fabrique de bateau
     *
     * evolutif
     */
    public void initialiserBateaux(){
        this.listeBateau = new ArrayList<>();
        this.listeBateau.add(fabriqueBateau.creerBateau(1));
        this.listeBateau.add(fabriqueBateau.creerBateau(2));
        this.listeBateau.add(fabriqueBateau.creerBateau(3));
        this.listeBateau.add(fabriqueBateau.creerBateau(4));
        this.listeBateau.add(fabriqueBateau.creerBateau(5));
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public List<Bateau> getListeBateau() {
        return listeBateau;
    }

    public int getPointactionrestant() {
        return pointActionRestant;
    }
}