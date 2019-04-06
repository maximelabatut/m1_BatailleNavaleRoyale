package facade;

import exceptions.bateau.*;
import exceptions.partie.*;
import exceptions.identification.*;
import modele.Case;
import modele.Joueur;
import modele.Partie;
import exceptions.partie.PartieIntrouvableException;
import modele.Plateau;
import modele.bateau.Bateau;

import java.util.*;

public class Facade implements IFacade {

    public static final int NB_JOUEUR_MAX = 4;
    public static final int RAYON_TIR = 8;
    public static final int max = 99;
    public static final int min = 0;

    private Map<Integer, Joueur> joueurs; // Map <IdJoueur, modele.Joueur> DATABASE DES JOUEURS
    private Map<Integer, Partie> listeParties; // Map <idPartie, modele.Partie>
    private List<Integer> joueursEnLigne; // liste des joueurs en ligne sur l'app
    private Map<Integer, Integer> joueursDansParties; // Map <idJoueur, idPartie> savoir ou est un joueur

    /**
     * Constructeur
     * Init des pseudo DB (cf. Map ci dessus)
     */
    public Facade() {
        this.joueurs = new HashMap<>();
        this.listeParties = new HashMap<>();
        this.joueursEnLigne = new ArrayList<>();
        this.joueursDansParties = new HashMap<>();
    }

    @Override
    public int inscription(String pseudo, String password) throws PseudoDejaPrisException {
        for (Joueur j : this.joueurs.values()) {
            if (j.getPseudo().equals(pseudo)) {
                throw new PseudoDejaPrisException();
            }
        }
        Joueur monJoueur = new Joueur(pseudo, password);
        this.joueurs.put(monJoueur.getIdJoueur(), monJoueur);
        return monJoueur.getIdJoueur();
    }

    @Override
    public void desinscription(int idJoueur) throws JoueurInexistantException {
        if (this.joueurs.containsKey(idJoueur)) {
            this.joueurs.remove(idJoueur);
            if(this.joueursEnLigne.contains(idJoueur))
                this.joueursEnLigne.remove(idJoueur);
        } else {
            throw new JoueurInexistantException();
        }
    }

    @Override
    public int connexion(String pseudo, String password) throws DejaConnecteException, MotDePasseIncorrectException {
        for (int i : this.joueurs.keySet()) {
            if (joueurs.get(i).getPseudo().equals(pseudo) && joueurs.get(i).getMdp().equals(password)) {
                if (joueursEnLigne.contains(joueurs.get(i).getIdJoueur())) {
                    throw new DejaConnecteException();
                }
                this.joueursEnLigne.add(joueurs.get(i).getIdJoueur());
                return joueurs.get(i).getIdJoueur();
            }
        }
        throw new MotDePasseIncorrectException();
    }

    @Override
    public void deconnexion(int idJoueur) throws PartieIntrouvableException {
        // On supprime le joueur concerné de la liste des joueurs en ligne
        this.joueursEnLigne.remove((Object) idJoueur);

        // Si le joueur est dans une partie
        if (this.joueursDansParties.get(idJoueur) != null) {
            // On récupère l'ID de la partie et la partie concernée
            int monIdPartie = this.joueursDansParties.get(idJoueur);
            supprimerPartieEtSesJoueurs(monIdPartie);
        }
    }

    @Override
    public Joueur getJoueurById(int idJoueur) throws JoueurInexistantException {
        if (this.joueurs.containsKey(idJoueur)) {
            return this.joueurs.get(idJoueur);
        } else {
            throw new JoueurInexistantException();
        }
    }

    public Map<Integer, Joueur> getJoueurs() {
        return joueurs;
    }

    public Map<Integer, Partie> getListeParties() {
        return listeParties;
    }

    public List<Integer> getJoueursEnLigne() {
        return joueursEnLigne;
    }

    public Map<Integer, Integer> getJoueursDansParties() {
        return joueursDansParties;
    }

    @Override
    public void creerPartie(int largeur, int hauteur, Joueur hote, int nbJoueurAttendu) throws TaillePlateauIncorrecteException, NbJoueursIncorrectException {
        if (nbJoueurAttendu > NB_JOUEUR_MAX) {
            throw new NbJoueursIncorrectException();
        }
        Partie laPartie = new Partie(largeur, hauteur, hote, nbJoueurAttendu);
        listeParties.put(laPartie.getIdPartie(), laPartie);
        joueursDansParties.put(hote.getIdJoueur(), laPartie.getIdPartie());
    }

    @Override
    public void rejoindrePartie(int idPartie, int idJoueur) throws NbJoueurMaxException, JoueurInexistantException, PartieIntrouvableException {
        if (listeParties.containsKey(idPartie) && !joueursDansParties.containsKey(idJoueur)) {
            Partie maPartie = this.listeParties.get(idPartie);
            if (maPartie.getMesJoueurs().size() < NB_JOUEUR_MAX) {
                maPartie.ajouterJoueur(getJoueurById(idJoueur));
                joueursDansParties.put(idJoueur, idPartie);
            }
        } else {
            throw new PartieIntrouvableException();
        }
    }

    @Override
    public List<Joueur> getJoueurPartie(int idPartie) throws PartieIntrouvableException {
        if (listeParties.containsKey(idPartie)) {
            return listeParties.get(idPartie).getMesJoueurs();
        }
        throw new PartieIntrouvableException();
    }

    @Override
    public boolean isPartieTerminee(int idPartie) throws PartieIntrouvableException {
        if (listeParties.containsKey(idPartie)) {
            return listeParties.get(idPartie).isPartieTerminee();
        }
        throw new PartieIntrouvableException();
    }

    @Override
    public void supprimerPartieEtSesJoueurs(int idPartie) throws PartieIntrouvableException {
        Partie maPartie = getPartieById(idPartie);
        // On supprime la partie et tous les joueurs s'y trouvant
        List<Joueur> maListeJoueurs = maPartie.getMesJoueurs();
        // On supprime tous les joueurs de la partie dans laquelle ils étaient associés
        for (int i = 0; i < maListeJoueurs.size(); i++) {
            this.joueursDansParties.remove(maListeJoueurs.get(i).getIdJoueur());
        }
        // On supprime la partie de la base de données
        this.listeParties.remove(idPartie);
    }

    @Override
    public void supprimerJoueurPartie(int idPartie, int idJoueur) throws PartieIntrouvableException, JoueurInexistantException, NbJoueursIncorrectException {
        Partie maPartie = getPartieById(idPartie);
        Joueur monJoueur = getJoueurById(idJoueur);
        if (maPartie.getMesJoueurs().contains(monJoueur) && !maPartie.isLancee()) {
            maPartie.supprimerJoueur(idJoueur);
        }
    }

    @Override
    public void lancerPartie(int idJoueur) throws PartieIntrouvableException, NbJoueursIncorrectException, TaillePlateauIncorrecteException {
        // Si le joueur est dans une partie
        if (this.joueursDansParties.containsKey(idJoueur)) {
            int idPartie = this.joueursDansParties.get(idJoueur);
            Partie laPartie = getPartieById(idPartie);
            if (laPartie.getHote().getIdJoueur() == idJoueur) {
                laPartie.lancerPartie();
            }
        } else {
            throw new PartieIntrouvableException();
        }
    }

    @Override
    public int positionJoueurSurPlateau(int idJoueur) throws JoueurInexistantException, PartieIntrouvableException {
        if (this.joueursDansParties.containsKey(idJoueur)) {
            Partie maPartie = getPartieById(this.joueursDansParties.get(idJoueur));
            return maPartie.getPositionJoueur(idJoueur);
        } else {
            throw new JoueurInexistantException();
        }
    }

    @Override
    public void effectuerTir(int impactX, int impactY, int idJoueur) throws PartieIntrouvableException, SelectionVideException {
        //récupération de la partie
        int idPartie = this.joueursDansParties.get(idJoueur);
        Partie laPartie = getPartieById(idPartie);

        //récupération de la liste des joueurs pour simplifier le code plus bas
        List<Joueur> lesJoueurs = laPartie.getMesJoueurs();

        //récupération du plateau
        Plateau monPlateau = laPartie.getPlateau();

        //vérification si la case est de l'eau
        if (monPlateau.getLePlateau()[impactX][impactY].isEau()) {
            throw new SelectionVideException();
        } else {
            //parcours des joueurs pour savoir lequel a été touché
            for (int i = 0; i < lesJoueurs.size(); i++) {
                //parcours de la liste de ces bateaux
                for (int j = 0; j < lesJoueurs.get(i).getListeBateau().size(); j++) {
                    //parcours des cases des bateaux pour savoir si ils sont situé dans la zone d'impact
                    for (int k = 0; k < lesJoueurs.get(i).getListeBateau().get(j).getAllCasesCouvertes().size(); k++) {
                        //si la case du bateau en X est égale à impactX et en Y est égale à impactY
                        if (lesJoueurs.get(i).getListeBateau().get(j).getAllCasesCouvertes().get(k).getX() == impactX && lesJoueurs.get(i).getListeBateau().get(j).getAllCasesCouvertes().get(k).getY() == impactY) {
                            //Alors je parcours la liste des domages de ce bateau en question
                            lesJoueurs.get(i).getListeBateau().get(j).impact(impactX, impactY, true);
                            updatePlateauAffichageable(impactX, impactY, idJoueur, idPartie, RAYON_TIR);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Case[][] getPlateauAffichageable(int idJoueur, int idPartie) throws JoueurInexistantException, PartieIntrouvableException{

        try {
            Partie p = getPartieById(idPartie);
            Case[][] monPlateau = p.getPlateau().copy(); // COPIER LE PLATEAU
            Joueur joueur = getJoueurById(idJoueur);
            List<Bateau> listeBateaux = joueur.getListeBateau();
            Set<Case> listeCases = new HashSet<>();
            for(Bateau b : listeBateaux){
                listeCases.addAll(b.getMaVue());
            }
            for(int i = 0; i<p.getPlateau().getTaillePlateau(); i++){
                for(int j = 0; j<p.getPlateau().getTaillePlateau(); j++){
                    if(listeCases.contains(monPlateau[i][j])){
                        if(!monPlateau[i][j].isEau()){
                            monPlateau[i][j].setImgPath("././img/bateau.png");
                        }
                        else{
                            monPlateau[i][j].setImgPath("././img/eau.png");
                        }
                    }
                    else{
                        monPlateau[i][j].setImgPath("././img/brouillard.png");
                    }
                }
            }
            return monPlateau;
        }
        catch (PartieIntrouvableException e) {
            // pas bien le catch silencieux !
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void placerBateau(int idJoueur, int posXMoteur, int posYMoteur, Bateau b, int angleOrietation) throws JoueurInexistantException, PartieIntrouvableException, PositionBateauHorsPlateauException {
        if (this.joueursDansParties.containsKey(idJoueur)) {
            Partie maPartie = getPartieById(this.joueursDansParties.get(idJoueur));
            maPartie.placerBateau(idJoueur, posXMoteur, posYMoteur, b, angleOrietation);
        } else {
            throw new JoueurInexistantException();
        }
    }

    @Override
    public void reparer(int x, int y, int idJoueur, Bateau leBateau) throws PasTonBateauException, BateauNonReparableException {
        Joueur monJoueur = this.joueurs.get(idJoueur);
        if (monJoueur.getListeBateau().contains(leBateau)) {
            leBateau.reparer(x, y);
        } else {
            throw new PasTonBateauException();
        }
    }

    @Override
    public void bateauCoule(int idJoueur, Bateau leBateau) {
        Joueur monJoueur = this.joueurs.get(idJoueur);
        if (leBateau.isCoule()) {
            if(monJoueur.getListeBateau().contains(leBateau))
                monJoueur.getListeBateau().remove(leBateau);
        }
    }

    @Override
    public void updatePlateauAffichageable(int x, int y, int idJoueur, int idPartie, int rayon) {
        //TODO : update le plateau apres un tir (et donc à la fin du tour)
    }

    @Override
    public void avancer(int idPartie, int idJoueur, int x, int y) throws deplacementImpossibleException, PartieIntrouvableException {
        int joueurCourant = getPartieById(idPartie).getMesJoueurs().get(idJoueur).getIdJoueur();
        for(int i = 0; i <= getPartieById(idPartie).getMesJoueurs().get(joueurCourant).getListeBateau().size(); i++) {
            Bateau leBateau = getPartieById(idPartie).getMesJoueurs().get(joueurCourant).getListeBateau().get(i);
            if(!leBateau.isMoteurTouche()){
                if (leBateau.getPosX() == x && leBateau.getXGoalTete() == x) {
                    if((leBateau.getPosY()-y)<=leBateau.getVitesse() && (leBateau.getPosY()-y)>=1){
                        leBateau.setPosY(y);
                    }else{
                        throw new deplacementImpossibleException();
                    }
                }
                if (leBateau.getPosY() == y && leBateau.getYGoalTete() == y) {
                    if((leBateau.getPosX()-x)<=leBateau.getVitesse() && (leBateau.getPosX()-x)>=1){
                        leBateau.setPosX(x);
                    }else{
                        throw new deplacementImpossibleException();
                    }
                }
            }else{
                throw new deplacementImpossibleException();
            }

        }
    }

    @Override
    public void tournerDroite(int idPartie ,int idJoueur) throws deplacementImpossibleException, PartieIntrouvableException {
        int joueurCourant = getPartieById(idPartie).getMesJoueurs().get(idJoueur).getIdJoueur();
        for (int i = 0; i <= getPartieById(idPartie).getMesJoueurs().get(joueurCourant).getListeBateau().size(); i++) {
            Bateau leBateau = getPartieById(idPartie).getMesJoueurs().get(joueurCourant).getListeBateau().get(i);
            if(!leBateau.isMoteurTouche()) {
                //déplacement si le moteur est en haut et la tête en bas
                if (leBateau.isVertical() && leBateau.getPosX() > leBateau.getXGoalTete() && ((leBateau.getLongueur() - leBateau.getPivotBateau() + leBateau.getPosY()) >= min)) {
                    leBateau.pivoterBateauMoins90Degres();
                } else {
                    throw new deplacementImpossibleException();
                }

                //déplacement si le moteur est en bas et la tête en haut
                if (leBateau.isVertical() && leBateau.getPosX() < leBateau.getXGoalTete() && ((leBateau.getLongueur() - leBateau.getPivotBateau() + leBateau.getPosY()) <= max)) {
                    leBateau.pivoterBateauMoins90Degres();
                } else {
                    throw new deplacementImpossibleException();
                }

                //déplacement si le moteur est à gauche et la tête à droite
                if (leBateau.isHorizontal() && leBateau.getPosY() < leBateau.getYGoalTete() && ((leBateau.getLongueur() - leBateau.getPivotBateau() + leBateau.getPosX()) <= max)) {
                    leBateau.pivoterBateauMoins90Degres();
                } else {
                    throw new deplacementImpossibleException();
                }

                //déplacement si le moteur est à droite et la tête à gauche
                if (leBateau.isHorizontal() && leBateau.getPosY() < leBateau.getYGoalTete() && ((leBateau.getLongueur() - leBateau.getPivotBateau() + leBateau.getPosX()) >= min)) {
                    leBateau.pivoterBateauMoins90Degres();
                } else {
                    throw new deplacementImpossibleException();
                }
            }
        }
    }

    @Override
    public void tournerGauche(int idPartie, int idJoueur) throws deplacementImpossibleException, PartieIntrouvableException {
        int joueurCourant = getPartieById(idPartie).getMesJoueurs().get(idJoueur).getIdJoueur();
        for (int i = 0; i <= getPartieById(idPartie).getMesJoueurs().get(joueurCourant).getListeBateau().size(); i++) {
            Bateau leBateau = getPartieById(idPartie).getMesJoueurs().get(joueurCourant).getListeBateau().get(i);
            if(!leBateau.isMoteurTouche()){
                //déplacement si le moteur est en haut et la tête en bas
                if (leBateau.isVertical() && leBateau.getPosX()>leBateau.getXGoalTete() && ((leBateau.getLongueur()-leBateau.getPivotBateau() + leBateau.getPosY())<= max)) {
                    leBateau.setPosY(leBateau.getYGoalTete());
                    leBateau.setPosX(leBateau.getXGoalTete() + leBateau.getLongueur()-1);
                } else {
                    throw new deplacementImpossibleException();
                }

                //déplacement si le moteur est en bas et la tête en haut
                if (leBateau.isVertical() && leBateau.getPosX()>leBateau.getXGoalTete() && ((leBateau.getLongueur()-leBateau.getPivotBateau() + leBateau.getPosY())>= min)){
                    leBateau.setPosY(leBateau.getYGoalTete());
                    leBateau.setPosX(leBateau.getXGoalTete() - leBateau.getLongueur()+1);
                } else {
                    throw new deplacementImpossibleException();
                }

                //déplacement si le moteur est à gauche et la tête à droite
                if (leBateau.isHorizontal() && leBateau.getPosY()>leBateau.getYGoalTete() && ((leBateau.getLongueur()-leBateau.getPivotBateau() + leBateau.getPosX())>=min)){
                    leBateau.setPosX(leBateau.getXGoalTete());
                    leBateau.setPosY(leBateau.getXGoalTete() + leBateau.getLongueur()-1);
                } else {
                    throw new deplacementImpossibleException();
                }

                //déplacement si le moteur est à droite et la tête à gauche
                if (leBateau.isHorizontal() && leBateau.getPosY()>leBateau.getYGoalTete() && ((leBateau.getLongueur()-leBateau.getPivotBateau() + leBateau.getPosX())<=max)){
                    leBateau.setPosX(leBateau.getXGoalTete());
                    leBateau.setPosY(leBateau.getXGoalTete() - leBateau.getLongueur()+1);
                } else {
                    throw new deplacementImpossibleException();
                }
            }
        }
    }

    @Override
    public Partie getPartieById(int idPartie) throws PartieIntrouvableException {
        if (listeParties.get(idPartie) != null) {
            return listeParties.get(idPartie);
        }
        throw new PartieIntrouvableException();
    }


}
