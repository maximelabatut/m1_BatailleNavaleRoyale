package modele;

public class Case {

    private boolean isEau;
    private String imgPath;
    private int x;
    private int y;

    /**
     * Constructeur de la classe
     * @param x coordonnees
     * @param y coordonnes
     */
    public Case(int x, int y) {
        this.isEau = true;
        this.imgPath = "./img/eau.png";
        this.x = x;
        this.y = y;
    }

    public boolean isEau() {
        return isEau;
    }

    public void setEau(boolean eau) {
        isEau = eau;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
