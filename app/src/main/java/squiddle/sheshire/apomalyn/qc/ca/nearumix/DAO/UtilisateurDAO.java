package squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.Utilisateur;

/**
 * Created by 1701094 on 2017-10-17.
 */

public class UtilisateurDAO {

    private final String TAG = "Utilisateur";

    private BaseDeDonnees bd = null;

    private static UtilisateurDAO instance = null;

    private Utilisateur utilisateurCourant = null;

    private UtilisateurDAO(){
        this.bd = new BaseDeDonnees();
    }

    public static UtilisateurDAO getInstance(){
        if(instance == null)
            instance = new UtilisateurDAO();
        return instance;
    }

    public Utilisateur getUtilisateurCourant(){
        return this.utilisateurCourant;
    }

    public Utilisateur setUtilisateurCourant(String email){
        this.utilisateurCourant = this.getUtilisateurParMail(email);

        return this.utilisateurCourant;
    }

    public Utilisateur getUtilisateurParId(int id){
        return new Utilisateur(0, "jean-eugene@gmail.com", "Jean-Eugène", 13, 0, null, null, null );
    }

    public Utilisateur getUtilisateurParMail(String mail){
        HashMap<String, String> parametres = new HashMap<>();
        parametres.put("email", mail);
        HashMap<String, String> donnees = this.bd.envoyerRequete(BaseDeDonnees.GET_UTILISATEUR, parametres);
        if(donnees.containsKey("erreur")){
            Log.e(TAG, donnees.get("erreur"));
            return null;
        }

        return new Utilisateur(Integer.parseInt(donnees.get("id")), donnees.get("email"), donnees.get("pseudonyme"), Integer.parseInt(donnees.get("niveau")), Integer.parseInt(donnees.get("xp")), null, null, null );
    }

    //Retourne la liste d'amis de L'utilisateur dont l'id est passé en parametre
    public List<Utilisateur> getListeAmis(int id){
        List<Utilisateur> liste_amis = new ArrayList<>();
        liste_amis.add(new Utilisateur(1, "jean-pierre@gmail.com", "Jean-Pierre", 10, 0, null, null, null ));
        liste_amis.add(new Utilisateur(2, "jean-michel@gmail.com", "Jean-Michel", 5, 0, null, null, null ));
        return liste_amis;
    }

}
