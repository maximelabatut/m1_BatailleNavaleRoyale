package actions;

import exceptions.partie.JoueurInexistantException;
import exceptions.partie.NbJoueursIncorrectException;
import exceptions.partie.PartieIntrouvableException;
import exceptions.partie.TaillePlateauIncorrecteException;
import modele.Case;
import modele.Joueur;
import modele.bateau.Bateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Creation partie.
 */
public class CreationPartie extends Environnement {

    private int nbJoueurs;
    private String pseudo;
    private int idPartie;
    private int hote;
    private int canBeLaunch;
    private List<String> listeJoueursConnectes;
    private List<Joueur> listeJoueursInvitation;
    private List<String> joueursEnLigne = new ArrayList<>();
    private List<String> partiesEnCours = new ArrayList<>();
    private List<Joueur> joueursDansPartie = new ArrayList<>();
    private Case[][] plateau;
    private int coinPlateau;
    private List<Bateau> bateaux;
    private List<Integer> coordsX;
    private List<Integer> coordsY;
    private List<Integer> orientation;

    @Override
    public String execute(){
        try {
            if(!this.getVariablesSession().containsKey("hote") && !this.getVariablesSession().containsKey("idPartie")){
                this.hote = 1;
                //On récupère le joueur grâce à l'identifiant stocker en variables de session
                Joueur joueur = getFacade().getJoueurById((int) getVariablesSession().get("monId"));
                //On initialise la partie
                getFacade().creerPartie(100,100, joueur, nbJoueurs);
                //On récupère l'id de la partie du joueur
                Map<Integer, Integer> listeJoueursDansPartie = getFacade().getJoueursDansParties();
                this.idPartie = listeJoueursDansPartie.get(joueur.getIdJoueur());
                this.getVariablesSession().put("hote", "hote");
                this.getVariablesSession().put("idPartie", this.idPartie);
                //On génère la liste des joueurs
                List<Joueur> listeJoueurs = getFacade().getJoueurPartie(this.idPartie);
                this.listeJoueursConnectes = new ArrayList<>();
                for (Joueur j : listeJoueurs) {
                    listeJoueursConnectes.add(j.getPseudo());
                }
                this.canBeLaunch = 0;

                this.pseudo = joueur.getPseudo();
            }else{
                if(getFacade().getPartieById((Integer) getVariablesSession().get("idPartie")).isLancee()){
                    int idJoueur = (int) getVariablesSession().get("monId");
                    Joueur j = getFacade().getJoueurById(idJoueur);
                    List<Bateau> bateaux = j.getListeBateau();
                    this.plateau = getFacade().getPartieById((int) getVariablesSession().get("idPartie")).getPlateau().getLePlateau();
                    this.coinPlateau = getFacade().positionJoueurSurPlateau(idJoueur);
                    this.bateaux = j.getListeBateau();

                    this.orientation = new ArrayList<>();
                    this.orientation.add(1);
                    this.orientation.add(2);
                    this.orientation.add(3);
                    this.orientation.add(4);

                    this.coordsX = new ArrayList<>();
                    this.coordsY = new ArrayList<>();
                    int decalX=0;
                    int decalY=0;
                    switch (coinPlateau){
                        case 1:
                            decalX = 0;
                            decalY = 0;
                            break;
                        case 2:
                            decalX = 0;
                            decalY = plateau.length/4+plateau.length/2;
                            break;
                        case 3:
                            decalX = plateau.length/4+plateau.length/2;
                            decalY = 0;
                            break;
                        case 4:
                            decalX = plateau.length/4+plateau.length/2;
                            decalY = plateau.length/4+plateau.length/2;
                            break;
                    }

                    for(int i=0; i<plateau.length/4;i++){
                        coordsX.add(i+decalX);
                    }
                    for(int i=0; i<plateau.length/4;i++){
                        coordsY.add(i+decalY);
                    }

                    return "partieLancee";
                }
                if(!this.getVariablesSession().containsKey("hote")){
                    this.hote = 0;
                    this.canBeLaunch = 0;
                }else{
                    this.hote = 1;
                    if(getFacade().getPartieById((Integer) this.getVariablesSession().get("idPartie")).getNbJoueurAttendu()
                            == getFacade().getPartieById((int) this.getVariablesSession().get("idPartie")).getMesJoueurs().size()){
                        this.canBeLaunch = 1;
                    }else{
                        this.canBeLaunch = 0;
                    }
                }
                //On récupère le joueur grâce à l'identifiant stocker en variables de session
                Joueur joueur = getFacade().getJoueurById((Integer) this.getVariablesSession().get("monId"));
                //On récupère l'id de la partie du joueur
                Map<Integer, Integer> listeJoueursDansPartie = getFacade().getJoueursDansParties();
                this.idPartie = listeJoueursDansPartie.get(joueur.getIdJoueur() );

                //On génère la liste des joueurs
                List<Joueur> listeJoueurs = getFacade().getJoueurPartie(this.idPartie);
                this.listeJoueursConnectes = new ArrayList<>();
                for (Joueur j : listeJoueurs) {
                    listeJoueursConnectes.add(j.getPseudo());
                }

                this.pseudo = joueur.getPseudo();
            }
        } catch (JoueurInexistantException | TaillePlateauIncorrecteException | PartieIntrouvableException | NbJoueursIncorrectException e) {
            return ERROR;
        }
        return SUCCESS; // Redirige vers salon.jsp
    }

    /**
     * Gets nb joueurs.
     *
     * @return the nb joueurs
     */
    public int getNbJoueurs() {
        return nbJoueurs;
    }

    /**
     * Sets nb joueurs.
     *
     * @param nbJoueurs the nb joueurs
     */
    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    /**
     * Gets pseudo.
     *
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Sets pseudo.
     *
     * @param pseudo the pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Gets liste joueurs invitation.
     *
     * @return the liste joueurs invitation
     */
    public List<Joueur> getListeJoueursInvitation() {
        return listeJoueursInvitation;
    }

    /**
     * Sets liste joueurs invitation.
     *
     * @param listeJoueursInvitation the liste joueurs invitation
     */
    public void setListeJoueursInvitation(List<Joueur> listeJoueursInvitation) {
        this.listeJoueursInvitation = listeJoueursInvitation;
    }

    /**
     * Gets id partie.
     *
     * @return the id partie
     */
    public int getIdPartie() {
        return idPartie;
    }

    /**
     * Sets id partie.
     *
     * @param idPartie the id partie
     */
    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    /**
     * Gets liste joueurs connectes.
     *
     * @return the liste joueurs connectes
     */
    public List<String> getListeJoueursConnectes() {
        return listeJoueursConnectes;
    }

    /**
     * Sets liste joueurs connectes.
     *
     * @param listeJoueursConnectes the liste joueurs connectes
     */
    public void setListeJoueursConnectes(List<String> listeJoueursConnectes) {
        this.listeJoueursConnectes = listeJoueursConnectes;
    }

    /**
     * Gets can be launch.
     *
     * @return the can be launch
     */
    public int getCanBeLaunch() {
        return canBeLaunch;
    }

    /**
     * Sets can be launch.
     *
     * @param canBeLaunch the can be launch
     */
    public void setCanBeLaunch(int canBeLaunch) {
        this.canBeLaunch = canBeLaunch;
    }

    /**
     * Gets joueurs dans partie.
     *
     * @return the joueurs dans partie
     */
    public List<Joueur> getJoueursDansPartie() {
        return joueursDansPartie;
    }

    /**
     * Sets joueurs dans partie.
     *
     * @param joueursDansPartie the joueurs dans partie
     */
    public void setJoueursDansPartie(List<Joueur> joueursDansPartie) {
        this.joueursDansPartie = joueursDansPartie;
    }

    /**
     * Gets hote.
     *
     * @return the hote
     */
    public int getHote() {
        return hote;
    }

    /**
     * Sets hote.
     *
     * @param hote the hote
     */
    public void setHote(int hote) {
        this.hote = hote;
    }

    /**
     * Gets joueurs en ligne.
     *
     * @return the joueurs en ligne
     */
    public List<String> getJoueursEnLigne() {
        return joueursEnLigne;
    }

    /**
     * Sets joueurs en ligne.
     *
     * @param joueursEnLigne the joueurs en ligne
     */
    public void setJoueursEnLigne(List<String> joueursEnLigne) {
        this.joueursEnLigne = joueursEnLigne;
    }

    /**
     * Gets parties en cours.
     *
     * @return the parties en cours
     */
    public List<String> getPartiesEnCours() {
        return partiesEnCours;
    }

    /**
     * Sets parties en cours.
     *
     * @param partiesEnCours the parties en cours
     */
    public void setPartiesEnCours(List<String> partiesEnCours) {
        this.partiesEnCours = partiesEnCours;
    }


    /**
     * Get plateau case [ ] [ ].
     *
     * @return the case [ ] [ ]
     */
    public Case[][] getPlateau() {
        return plateau;
    }

    /**
     * Sets plateau.
     *
     * @param plateau the plateau
     */
    public void setPlateau(Case[][] plateau) {
        this.plateau = plateau;
    }

    /**
     * Gets coin plateau.
     *
     * @return the coin plateau
     */
    public int getCoinPlateau() {
        return coinPlateau;
    }

    /**
     * Sets coin plateau.
     *
     * @param coinPlateau the coin plateau
     */
    public void setCoinPlateau(int coinPlateau) {
        this.coinPlateau = coinPlateau;
    }

    /**
     * Gets bateaux.
     *
     * @return the bateaux
     */
    public List<Bateau> getBateaux() {
        return bateaux;
    }

    /**
     * Sets bateaux.
     *
     * @param bateaux the bateaux
     */
    public void setBateaux(List<Bateau> bateaux) {
        this.bateaux = bateaux;
    }

    public List<Integer> getCoordsX() {
        return coordsX;
    }

    public void setCoordsX(List<Integer> coordsX) {
        this.coordsX = coordsX;
    }

    public List<Integer> getCoordsY() {
        return coordsY;
    }

    public void setCoordsY(List<Integer> coordsY) {
        this.coordsY = coordsY;
    }

    public List<Integer> getOrientation() {
        return orientation;
    }

    public void setOrientation(List<Integer> orientation) {
        this.orientation = orientation;
    }
}
