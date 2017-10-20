package squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO;

import android.util.Log;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        HashMap<String, String> parametres = new HashMap<>();
        parametres.put("id", "" + id);
        HashMap<String, String> donnees = this.bd.envoyerRequete(BaseDeDonnees.GET_UTILISATEUR, parametres);
        if(donnees.containsKey("erreur")){
            Log.e(TAG, donnees.get("erreur"));
            return null;
        }

        if(donnees.get("listeAmis") != null){

        }

        return new Utilisateur(Integer.parseInt(donnees.get("id")), donnees.get("email"), donnees.get("pseudonyme"), Integer.parseInt(donnees.get("niveau")), Integer.parseInt(donnees.get("xp")), null, null, null );

    }

    public Utilisateur getUtilisateurParMail(String mail){
        HashMap<String, String> parametres = new HashMap<>();
        parametres.put("email", mail);
        HashMap<String, String> donnees = this.bd.envoyerRequete(BaseDeDonnees.GET_UTILISATEUR, parametres);
        if(donnees.containsKey("erreur")){
            Log.e(TAG, donnees.get("erreur"));
            return null;
        }

        List<Utilisateur> listeAmis = new ArrayList<>();

        if(donnees.get("listeAmis") != null || donnees.get("listeAmis").equals("")){
            HashMap<String, String> listeAmisHashMap = this.bd.convertirXMLenHashMap(donnees.get("listeAmis"), "listeAmis");
            HashMap<String, String> amis;

            if(listeAmisHashMap != null) {
                for (Map.Entry<String, String> entry : listeAmisHashMap.entrySet()) {
                    String clef = entry.getKey();
                    String valeur = entry.getValue();

                    amis = this.bd.convertirXMLenHashMap(valeur, clef);

                    listeAmis.add(new Utilisateur(
                            Integer.parseInt(amis.get("id")),
                            amis.get("email"),
                            amis.get("pseudonyme"),
                            Integer.parseInt(amis.get("niveau")),
                            Integer.parseInt(amis.get("xp"))
                    ));
                }
            }
        }

        return new Utilisateur(Integer.parseInt(donnees.get("id")), donnees.get("email"), donnees.get("pseudonyme"), Integer.parseInt(donnees.get("niveau")), Integer.parseInt(donnees.get("xp")), listeAmis, null, null );
    }

    public boolean modiferUtilisateur(){
        HashMap<String, String> resultat = this.bd.envoyerRequete(BaseDeDonnees.MODIFIER_UTILISATEUR, utilisateurCourant.toHashMap());

        if(resultat.containsKey("erreur")){
            Log.e(TAG, resultat.get("erreur"));
            return false;
        }
        return true;
    }

    /**
     * @param id du nouvel amis
     * @return
     */
    public boolean ajouterAmis(int id){
        HashMap<String, String> donnees = new HashMap<>();
        donnees.put("amis", "" + id);
        donnees.put("utilisateur", "" + utilisateurCourant.getId());

        HashMap<String, String> resultat = this.bd.envoyerRequete(BaseDeDonnees.AJOUTER_AMIS, donnees);

        if(resultat.containsKey("erreur")){
            Log.e(TAG, resultat.get("erreur"));
            return false;
        }
        setUtilisateurCourant(utilisateurCourant.getMail());

        return true;
    }

    public boolean supprimerAmis(Utilisateur amis){
        HashMap<String, String> donnees = new HashMap<>();
        donnees.put("utilisateur", "" + utilisateurCourant.getId());
        donnees.put("amis", "" + amis.getId());

        HashMap<String, String> resultat = this.bd.envoyerRequete(BaseDeDonnees.SUPPRIMER_AMIS, donnees);

        if(resultat.containsKey("erreur")){
            Log.e(TAG, resultat.get("erreur"));
            return false;
        }

        utilisateurCourant.supprimerAmis(amis);
        return true;
    }


    /**
     * Retourne la liste des amis de l'utilisateur courant
     */
    public List<Utilisateur> getListeAmis(){
        return utilisateurCourant.getListe_amis();
    }

    public Utilisateur ajouterUtilisateur(String pseudonyme, String email){
        HashMap<String, String> donnees = new HashMap<>();
        donnees.put("pseudonyme", pseudonyme);
        donnees.put("email", email);

        HashMap<String, String> resultat = this.bd.envoyerRequete(BaseDeDonnees.AJOUTER_UTILISATEUR, donnees);

        if(resultat.containsKey("erreur")){
            Log.e(TAG, resultat.get("erreur"));
            return null;
        }

        utilisateurCourant = new Utilisateur(Integer.parseInt(donnees.get("id")), donnees.get("email"), donnees.get("pseudonyme"), Integer.parseInt(donnees.get("niveau")), Integer.parseInt(donnees.get("xp")));
        return utilisateurCourant;
    }
}
