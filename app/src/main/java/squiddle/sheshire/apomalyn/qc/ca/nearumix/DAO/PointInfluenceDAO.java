package squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

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

        return new PointInfluence(0, "test PI", new LatLng(48.8526, -67.518), new Musique(0, "test musique", "Balacouille", 2017, ""), PointInfluence.ETAT_VOTE, 0);
    }

    public PointInfluence getPointInfluenceParNom(String nom){
        return new PointInfluence(0, "test PI", new LatLng(48.8526, -67.518), new Musique(0, "test musique", "Balacouille", 2017, ""), PointInfluence.ETAT_VOTE, 0);



    }
/*
    public List<PointInfluence> getListePointInfluence(){
        if(listePointInfluence == null){

        }
    }*/

    public List<PointInfluence> getPointsInfluence(){
        List<PointInfluence> listePointsInfluence = new ArrayList<>();

        listePointInfluence.add(new PointInfluence(0, "test PI", new LatLng(48.8526, -67.518), new Musique(0, "est-ce vraiment une musique?!", "Balacouille", 2017, ""), PointInfluence.ETAT_VOTE, 0));
        listePointInfluence.add(new PointInfluence(1, "test PI 2", new LatLng(47.8526, -67.518), new Musique(1, "test musique 2", "Queenalen", 2017, ""), PointInfluence.ETAT_VOTE, 0));
        listePointInfluence.add(new PointInfluence(2, "test PI 3", new LatLng(46.8526, -67.518), new Musique(2, "test musique 3", "Mes testicules", 2017, ""), PointInfluence.ETAT_VOTE, 0));

        return listePointsInfluence;
    }

    public void modifierPointInfluence(PointInfluence pointInfluence){

    }
}
