package squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO;

import java.util.ArrayList;
import java.util.List;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.Utilisateur;

/**
 * Created by 1701094 on 2017-10-17.
 */

public class UtilisateurDAO {

    public Utilisateur getUtilisateurParId(int id){
        return new Utilisateur(0, "jean-eugene@gmail.com", "Jean-Eugène", 13, 0, null, null, null );
    }

    public Utilisateur getUtilisateurParMail(String mail){
        return new Utilisateur(0, "jean-eugene@gmail.com", "Jean-Eugène", 13, 0, null, null, null );
    }

    //Retourne la liste d'amis de L'utilisateur dont l'id est passé en parametre
    public List<Utilisateur> getListeAmis(int id){
        List<Utilisateur> liste_amis = new ArrayList<>();
        liste_amis.add(new Utilisateur(1, "jean-pierre@gmail.com", "Jean-Pierre", 10, 0, null, null, null ));
        liste_amis.add(new Utilisateur(2, "jean-michel@gmail.com", "Jean-Michel", 5, 0, null, null, null ));
        return liste_amis;
    }

}
