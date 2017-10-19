package squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO;

import com.google.android.gms.maps.model.LatLng;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.Musique;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.PointInfluence;

/**
 * Created by apomalyn on 03/10/17.
 */

public class PointInfluenceDAO {

    private final String TAG = "Utilisateur";

    private BaseDeDonnees bd = null;

    private List<PointInfluence> listePointInfluence = null;

    private static PointInfluenceDAO instance = null;

    private PointInfluenceDAO(){
        this.bd = new BaseDeDonnees();
    }

    public static PointInfluenceDAO getInstance(){
        if(instance == null)
            instance = new PointInfluenceDAO();
        return instance;
    }

    public PointInfluence getPointInfluence(int id){
        if(this.listePointInfluence == null){
            getPointsInfluence();
        }

        for (PointInfluence point : this.listePointInfluence) {
            if(point.getId() == id){
                return point;
            }
        }
        return null;
    }

    public PointInfluence getPointInfluenceParNom(String nom){
        if(this.listePointInfluence == null){
            getPointsInfluence();
        }

        for (PointInfluence point : this.listePointInfluence) {
            if(point.getNom().equals(nom)){
                return point;
            }
        }
        return null;
    }
/*
    public List<PointInfluence> getListePointInfluence(){
        if(listePointInfluence == null){

        }
    }*/

    public List<PointInfluence> getPointsInfluence(){
        this.listePointInfluence = new ArrayList<>();

        HashMap<String, String> donnees = this.bd.envoyerRequete(BaseDeDonnees.GET_POINTS_INFLUENCE);
        HashMap<String, String> point;
        HashMap<String, String> musique;

        for(Map.Entry<String, String> entry : donnees.entrySet()) {
            String clef = entry.getKey();
            String valeur = entry.getValue();


            point = this.bd.convertirXMLenHashMap(valeur, clef);
            musique = this.bd.convertirXMLenHashMap(point.get("musique"), "musique");

            this.listePointInfluence.add(new PointInfluence(
                    Integer.parseInt(point.get("id")),
                    point.get("nom"),
                    new LatLng(Double.parseDouble(point.get("latitude")), Double.parseDouble(point.get("longitude"))),
                    new Musique(Integer.parseInt(musique.get("id")), musique.get("nom"), musique.get("auteur"),Integer.parseInt(musique.get("annee"))),
                    PointInfluence.ETAT_VOTE,
                    Integer.parseInt(point.get("visite"))
            ));
        }

        return listePointInfluence;
    }

    public boolean votePointInfluence(PointInfluence point, boolean pourContre){
        HashMap<String, String> parametres = new HashMap<>();

        parametres.put("id", "" + point.getId());
        if(pourContre){
            parametres.put("votePour", "" + 1);
        }else{
            parametres.put("voteContre", "" + 1);
        }

        this.bd.envoyerRequete(BaseDeDonnees.VOTER_MUSIQUE, parametres);
        return true;
    }

    public void modifierPointInfluence(PointInfluence pointInfluence){

    }
}
