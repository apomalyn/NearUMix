package squiddle.sheshire.apomalyn.qc.ca.nearumix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO.UtilisateurDAO;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.Utilisateur;

public class VueProfilUtilisateur extends AppCompatActivity  {

    UtilisateurDAO utilisateurDAO;
    protected TextView profil_titre;
    protected TextView titre_nom;
    protected TextView titre_niveau;
    protected TextView titre_exp;

    protected TextView champ_nom;
    protected TextView champ_niveau;
    protected TextView champ_exp;
    protected Button bouton_liste_amis;
    protected Button bouton_liste_noire;
    protected Button bouton_retour_carte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_menu);
        profil_titre=(TextView)findViewById(R.id.profil_titre);
        titre_nom=(TextView)findViewById(R.id.titre_nom);
        titre_niveau=(TextView)findViewById(R.id.titre_niveau);
        titre_exp=(TextView)findViewById(R.id.titre_exp);

        bouton_liste_amis=(Button)findViewById(R.id.bouton_liste_amis);
        bouton_liste_noire=(Button)findViewById(R.id.bouton_liste_noire);
        bouton_retour_carte=(Button)findViewById(R.id.bouton_retour_carte);

        champ_nom = (TextView)findViewById(R.id.champs_nom);
        champ_niveau = (TextView)findViewById(R.id.champs_niveau);
        champ_exp = (TextView)findViewById(R.id.champs_exp);

        utilisateurDAO = UtilisateurDAO.getInstance();
        Utilisateur utilisateur = utilisateurDAO.getUtilisateurCourant();

        champ_nom.setText("" + utilisateur.getNom());
        champ_niveau.setText("" + utilisateur.getNiveau());
        champ_exp.setText("" + utilisateur.getExperience());
    }

    public void retourCarte(View vue){
        this.finish();
    }

    public void changerVersAmis(View vue){
        Intent changerVue = new Intent(VueProfilUtilisateur.this,VueAmis.class);
        startActivity(changerVue);
    }
}
