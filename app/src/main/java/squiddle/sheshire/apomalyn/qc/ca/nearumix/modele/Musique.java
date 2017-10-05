package squiddle.sheshire.apomalyn.qc.ca.nearumix.modele;

/**
 * Created by apomalyn on 03/10/17.
 */

public class Musique {

    /**
     * id de la musique
     */
    private int id;

    /**
     * Nom de la musique
     */
    private String nom;

    /**
     * Nom de l'interprete
     */
    private String interprete;

    /**
     * Annee de parution
     */
    private int annee;

    /**
     * url pour acceder a la musique
     */
    private String url;

    public Musique(int id, String nom, String interprete, int annee, String url) {
        this.id = id;
        this.nom = nom;
        this.interprete = interprete;
        this.annee = annee;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getInterprete() {
        return interprete;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
