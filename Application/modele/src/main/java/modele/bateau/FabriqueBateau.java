package modele.bateau;


import modele.bateau.types.*;

public class FabriqueBateau {

    /*
    Par convention pour le type :
    1 = ContreTorpilleur
    2 = Torpilleur
    3 = PorteAvion
    4 = SousMarin
    5 = Croiseur
     */

    public Bateau creerBateau(int type) {
        switch (type) {
            case 1:
                return new ContreTorpilleur();
            case 2:
                return new Torpilleur();
            case 3:
                return new PorteAvion();
            case 4:
                return new SousMarin();
            case 5:
                return new Croiseur();
            default:
                return null;
        }
    }
}
