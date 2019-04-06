package facade;

import exceptions.bateau.BateauNonReparableException;
import exceptions.bateau.PasTonBateauException;
import exceptions.bateau.SelectionVideException;
import exceptions.partie.*;
import modele.Case;
import modele.Joueur;
import modele.Partie;
import exceptions.identification.*;
import modele.Plateau;
import modele.bateau.Bateau;

import java.util.List;
import java.util.Map;

public interface IFacade {

    /**
     * Permet d'inscrire un joueur dans la base de données
     *
     * @param pseudo   du joueur en String
     * @param password du joueur en String
     * @return l'id du joueur une fois inscrit
     * @throws PseudoDejaPrisException
     */
    int inscription(String pseudo, String password) throws PseudoDejaPrisException;

    /**
     * Permet de désinscrire un joueur de la base de donnée
     *
     * @param idJoueur
     * @throws JoueurInexistantException si le joueur n'existe pas (jamais levée normalement)
     */
    void desinscription(int idJoueur) throws JoueurInexistantException;

    /**
     * Permet à un joueur inscrit de se connecter au jeu
     *
     * @param pseudo   du joueur en String
     * @param password du joueur en String
     * @return l'id du joueur une fois connecté
     * @throws DejaConnecteException        si le joueur est déjà connecté
     * @throws MotDePasseIncorrectException si le joueur à rentré un mauvais login/mdp
     */
    int connexion(String pseudo, String password) throws DejaConnecteException, MotDePasseIncorrectException;

    /**
     * Permet à un joueur de se déconnecter du jeu. S'il était dans une partie, celle-ci est supprimée.
     *
     * @param idJoueur l'id du joueur à supprimer
     * @throws PartieIntrouvableException si la partie n'existe pas (jamais levée)
     */
    void deconnexion(int idJoueur) throws PartieIntrouvableException;

    /**
     * Retourne le joueur dont l'id est passé en paramètre
     *
     * @param idJoueur l'id du joueur recherché
     * @return le Joueur concerné
     * @throws JoueurInexistantException si le joueur n'existe pas
     */
    Joueur getJoueurById(int idJoueur) throws JoueurInexistantException;

    /**
     * Retourne la partie dont l'id est passé en paramètre
     *
     * @param idPartie l'id de la partie recherchée
     * @return la Partie concernée
     * @throws PartieIntrouvableException si la partie n'existe pas
     */
    Partie getPartieById(int idPartie) throws PartieIntrouvableException;

    /**
     * Getter sur la Map<idJoueur , Joueur> de la Facade
     *
     * @return
     */
    Map<Integer, Joueur> getJoueurs();

    /**
     * Getter sur la Map <idJoueur , idPartie> de la Facade
     *
     * @return
     */
    Map<Integer, Integer> getJoueursDansParties();

    /**
     * Getter sur la Map <idPartie , Partie> de la Facade
     *
     * @return
     */
    Map<Integer, Partie> getListeParties();

    /**
     * Getter sur la List des joueurs connectés de la Facade
     *
     * @return
     */
    List<Integer> getJoueursEnLigne();

    /**
     * Permet de créér un partie, avec un plateau, un joueur hote et le nombre de joueurs attendu
     *
     * @param largeur         du plateau qui sera créé
     * @param hauteur         du plateau qui sera créé
     * @param hote            le joueur créant la partie
     * @param nbJoueurAttendu le nombre de joueurs attendus pour la partie créée
     * @throws TaillePlateauIncorrecteException si le tableau n'est pas carré ou si la taille < 100
     * @throws NbJoueursIncorrectException      si le nombre de joueur est > 4
     */
    void creerPartie(int largeur, int hauteur, Joueur hote, int nbJoueurAttendu) throws TaillePlateauIncorrecteException, NbJoueursIncorrectException;

    /**
     * Permet à un joueur de rejoindre une partie si celle-ci existe, que le joueur existe et qu'il n'est pas déjà
     * dans une autre partie
     *
     * @param idPartie l'id de la partie concernée
     * @param idJoueur l'id du joueur concerné
     * @throws NbJoueurMaxException       si la partie est déjà pleine
     * @throws JoueurInexistantException  si le joueur n'existe pas
     * @throws PartieIntrouvableException si la partie n'existe pas
     */
    void rejoindrePartie(int idPartie, int idJoueur) throws NbJoueurMaxException, JoueurInexistantException, PartieIntrouvableException;

    /**
     * Permet de récupérer la liste des joueurs dans une partie si celle-ci existe
     *
     * @param idPartie l'id de la partie concernée
     * @return un liste de joueur (ceux dans la partie)
     * @throws PartieIntrouvableException si la partie n'existe pas
     */
    List<Joueur> getJoueurPartie(int idPartie) throws PartieIntrouvableException;

    /**
     * Permet de tester si une partie est terminée
     *
     * @param idPartie l'id de la partie concernée
     * @return un boolean true ou false selon si la partie est terminée ou non
     * @throws PartieIntrouvableException si la partie n'existe pas
     */
    boolean isPartieTerminee(int idPartie) throws PartieIntrouvableException;

    /**
     * Méthode permettant de supprimer une partie et tout ce qui lui est lié (les joueurs qui y étaient sortent de celle-ci)
     *
     * @param idPartie l'id de la partie à supprimer
     * @throws PartieIntrouvableException si la partie n'existe pas
     */
    void supprimerPartieEtSesJoueurs(int idPartie) throws PartieIntrouvableException;

    /**
     * Méthode permettant de supprimer un joueur dans une partie non lancée
     * (si il quitte le salon ou il attend le lancement de la partie)
     *
     * @param idPartie l'id de la partie concernée
     * @param idJoueur l'id du joueur concerné
     * @throws PartieIntrouvableException  si la partie n'existe pas
     * @throws NbJoueursIncorrectException si la partie est déjà vide
     * @throws JoueurInexistantException   si le joueur n'existe pas
     */
    void supprimerJoueurPartie(int idPartie, int idJoueur) throws PartieIntrouvableException, NbJoueursIncorrectException, JoueurInexistantException;

    /**
     * Permet de lancer la partie par un jouer qui doit être l'hote de celle-ci
     *
     * @param idJoueur l'id du joueur qui lance la partie
     * @throws PartieIntrouvableException       si le joueur n'appartient pas à une partie
     * @throws NbJoueursIncorrectException      si le nombre de joueur dans la partie n'est pas correct
     * @throws TaillePlateauIncorrecteException si la taille du plateau est incorrecte (impossible car codé en dur)
     */
    void lancerPartie(int idJoueur) throws PartieIntrouvableException, NbJoueursIncorrectException, TaillePlateauIncorrecteException;

    /**
     * Méthode permettant de retourner la position de la zone du joueur sur le plateau (son coin attribué)
     * Par conventiion : 1 = haut gauche / 2 = haut droire / 3 = bas gauche / 4 = bas droite
     *
     * @param idJoueur l'id du joueur concerné
     * @return le numéro correspondant au coin concerné
     * @throws JoueurInexistantException  si le joueur n'existe pas
     * @throws PartieIntrouvableException si la partie n'existe pas
     */
    int positionJoueurSurPlateau(int idJoueur) throws JoueurInexistantException, PartieIntrouvableException;

    void effectuerTir(int impactX, int impactY, int idJoueur) throws PartieIntrouvableException, SelectionVideException;

    /**
     * Retourne le plateau tel que doit le voir le joueur donne en param
     * @param idJoueur
     * @param idPartie
     * @return
     * @throws JoueurInexistantException
     * @throws PartieIntrouvableException
     */
    Case[][] getPlateauAffichageable(int idJoueur, int idPartie) throws JoueurInexistantException, PartieIntrouvableException;

    /**
     * Méthode permettant de placer un bateau pour un joueur donnée à une position donnée pour un bateau donné
     *
     * @param idJoueur   l'id du joueur qui doit palcer son bateau
     * @param posXMoteur la position en X du moteur du bateau sur le plateau
     * @param posYMoteur la position en Y du moteur du bateau du plateau
     * @param b          le bateau
     * @throws JoueurInexistantException  si le joueur n'existe pas
     * @throws PartieIntrouvableException si la partie n'existe pas
     */
    void placerBateau(int idJoueur, int posXMoteur, int posYMoteur, Bateau b, int angleOrientation) throws JoueurInexistantException, PartieIntrouvableException, PositionBateauHorsPlateauException;

    /**
     * Méthode permettant de reparer une zone d'un bateau donné pour un joueur donné
     *
     * @param x        la zone x du bateau
     * @param y        la zone y du bateau
     * @param idJoueur l'id du joueur réparant son bateau
     * @param leBateau le bateau en question
     * @throws PasTonBateauException       le bateau ne correspond pas à son bateau
     * @throws BateauNonReparableException le bateau ne peut pas être réparé
     */
    void reparer(int x, int y, int idJoueur, Bateau leBateau) throws PasTonBateauException, BateauNonReparableException;

    /**
     * Méthode permettant de supprimer un bateau coulé
     *
     * @param idJoueur l'id du joueur dont on coule le bateau
     * @param leBateau le bateau en question
     */
    void bateauCoule(int idJoueur, Bateau leBateau);

    /**
     * Methode qui mets a jour l'affichage d'un joueur apres qu'un tir ait été effectué
     * @param x
     * @param y
     * @param idJoueur
     * @param idPartie
     * @param rayon
     */
    void updatePlateauAffichageable(int x, int y, int idJoueur, int idPartie, int rayon);

    /**
     * Le joueur avance son bateau à des coordonées précises
     * On part du principe que si un moteur est touché alors le bateau ne peut avancer même s'il en a deux
     * @param idPartie
     * @param idJoueur
     * @param x
     * @param y
     * @throws deplacementImpossibleException
     * @throws PartieIntrouvableException
     */
    void avancer(int idPartie, int idJoueur, int x, int y)throws deplacementImpossibleException,PartieIntrouvableException ;

    /**
     * Le joueur tourne son bateau vers +90° le bateau
     *
     * @param idJoueur identifiant du joueur
     * @throws deplacementImpossibleException the deplacement impossible exception
     */
    void tournerDroite(int idPartie, int idJoueur)throws deplacementImpossibleException ,PartieIntrouvableException ;

    /**
     * Le joueur tourne son bateau vers -90° le bateau
     * @param idPartie
     * @param idJoueur
     * @throws deplacementImpossibleException
     * @throws PartieIntrouvableException
     */
    void tournerGauche(int idPartie, int idJoueur)throws deplacementImpossibleException ,PartieIntrouvableException ;

}
