package squiddle.sheshire.apomalyn.qc.ca.nearumix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.Utilisateur;

public class VueProfilUtilisateur extends AppCompatActivity {

    Utilisateur utilisateur_courant;
    protected TextView profil_titre;
    protected TextView titre_nom;
    protected TextView titre_mail;
    protected TextView titre_niveau;
    protected TextView titre_exp;
    protected Button bouton_liste_amis;
    protected Button bouton_liste_noire;
    protected Button bouton_retour_carte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_profil_utilisateur);
        profil_titre=(TextView)findViewById(R.id.profil_titre);
        titre_nom=(TextView)findViewById(R.id.titre_nom);
        titre_mail=(TextView)findViewById(R.id.titre_mail);
        titre_niveau=(TextView)findViewById(R.id.titre_niveau);
        titre_exp=(TextView)findViewById(R.id.titre_exp);
        bouton_liste_amis=(Button)findViewById(R.id.bouton_liste_amis);
        bouton_liste_noire=(Button)findViewById(R.id.bouton_liste_noire);
        bouton_retour_carte=(Button)findViewById(R.id.bouton_retour_carte);


    }

    public void retourCarte(View vue){
        this.finish();
    }
}
