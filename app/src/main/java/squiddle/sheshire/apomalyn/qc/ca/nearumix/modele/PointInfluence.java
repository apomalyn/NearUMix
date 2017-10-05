package squiddle.sheshire.apomalyn.qc.ca.nearumix.modele;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by apomalyn on 03/10/17.
 */

public class PointInfluence {

    /**
     * Etats
     */
    public static final int ETAT_VOTE = 0;
    public static final int ETAT_CHANGE = 1;

    /**
     * id du point d'influence
     */
    private int id;

    /**
     * Nom du point d'influence
     */
    private String nom;

    /**
     * Coordonnees du point
     */
    private LatLng coordonnees;

    /**
     * Musique diffusee sur le point
     */
    private Musique musique = null;

    /**
     * Etat actuel du point, VOTE ou CHANGE
     */
    private int etatCourant;

    /**
     * Nombre de visiteurs afin de calculer le taux d'influence du point
     */
    private int nombreDeVisiteurs = 0;

    public PointInfluence(int id, String nom, LatLng coordonnees, Musique musique, int etatCourant, int nombreDeVisiteurs) {
        this.id = id;
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.musique = musique;
        this.etatCourant = etatCourant;
        this.nombreDeVisiteurs = nombreDeVisiteurs;
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

    public LatLng getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(LatLng coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Musique getMusique() {
        return musique;
    }

    public void setMusique(Musique musique) {
        this.musique = musique;
    }

    public int getEtatCourant() {
        return etatCourant;
    }

    public void setEtatCourant(int etatCourant) {
        this.etatCourant = etatCourant;
    }

    public int getNombreDeVisiteurs() {
        return nombreDeVisiteurs;
    }

    public void setNombreDeVisiteurs(int nombreDeVisiteurs) {
        this.nombreDeVisiteurs = nombreDeVisiteurs;
    }

    public void ajouterVisiteur(){
        this.nombreDeVisiteurs++;
    }
}
