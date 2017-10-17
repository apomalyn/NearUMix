package squiddle.sheshire.apomalyn.qc.ca.nearumix.modele;

import java.util.List;

/**
 * Created by 1701094 on 2017-10-17.
 */

public class Utilisateur {


    /**
     * Id d'utilisateur
     */
    private int id;

    /**
     * Adresse mail de l'utilisateur
     */
    private String mail;

    /**
     * Nom d'utilisateur
     */
    private String nom;

    /**
     * Niveau d'utilisateur
     */
    private int niveau;

    /**
     * Nombre de points d'expérience
     */
    private int experience;

    /**
     * Liste d'amis
     */
    private List<Utilisateur> liste_amis;

    /**
     * Liste noire
     */
    private List<Utilisateur> liste_noire;

    /**
     * Liste des points d'influence déjà visités par le joueur.
     */
    private List<PointInfluence> pi_visites;

    /**
     * Constructeurs
     */
    public Utilisateur(int id, String mail, String nom, int niveau, int experience, List<Utilisateur> liste_amis, List<Utilisateur> liste_noire, List<PointInfluence> pi_visites) {
        this.id = id;
        this.mail = mail;
        this.nom = nom;
        this.niveau = niveau;
        this.experience = experience;
        this.liste_amis = liste_amis;
        this.liste_noire = liste_noire;
        this.pi_visites = pi_visites;
    }

    public Utilisateur(int id, String mail, String nom, int niveau, int experience) {
        this.mail = mail;
        this.nom = nom;
        this.niveau = niveau;
        this.experience = experience;
        this.liste_amis = liste_amis;
        this.liste_noire = liste_noire;
        this.pi_visites = pi_visites;
    }

    public Utilisateur(String mail, String nom, int niveau, int experience) {
        this.mail = mail;
        this.nom = nom;
        this.niveau = niveau;
        this.experience = experience;
    }

    /**
     * Accesseurs
     */
    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getNom() {
        return nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getExperience() {
        return experience;
    }

    public List<Utilisateur> getListe_amis() {
        return liste_amis;
    }

    public List<Utilisateur> getListe_noire() {
        return liste_noire;
    }

    public List<PointInfluence> getPi_visites() {
        return pi_visites;
    }
    /**
     * Mutateurs
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setListe_amis(List<Utilisateur> liste_amis) {
        this.liste_amis = liste_amis;
    }

    public void setListe_noire(List<Utilisateur> liste_noire) {
        this.liste_noire = liste_noire;
    }

    public void setPi_visités(List<PointInfluence> pi_visites) {
        this.pi_visites = pi_visites;
    }

    /**
     * Methodes
     */
    public void ajouterAmis(Utilisateur u){
        if(u != null)
        liste_amis.add(u);
        else{
            //Message d'erreur style Toast pour l'instant?
        }
    }

    public void ajouterBlocage(Utilisateur u){
        if(u != null)
            liste_noire.add(u);
        else{
            //Message d'erreur style Toast pour l'instant?
        }
    }

    public void ajouterPIVisite(PointInfluence pi){
        if(pi != null)
            pi_visites.add(pi);
        else{
            //Message d'erreur style Toast pour l'instant?
        }
    }
}
