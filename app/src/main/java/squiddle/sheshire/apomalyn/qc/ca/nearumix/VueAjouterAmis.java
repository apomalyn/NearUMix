package squiddle.sheshire.apomalyn.qc.ca.nearumix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO.UtilisateurDAO;

public class VueAjouterAmis extends AppCompatActivity {

    protected EditText ajout;

    protected UtilisateurDAO utilisateurDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_amis);

        this.ajout = (EditText)findViewById(R.id.nomAmis);

        this.utilisateurDAO = UtilisateurDAO.getInstance();
    }

    public void ajouter(View view){
        String nom = this.ajout.getText().toString();
        utilisateurDAO.ajouterAmis(utilisateurDAO.getUtilisateurParMail(nom).getId());

        utilisateurDAO.setUtilisateurCourant(utilisateurDAO.getUtilisateurCourant().getMail());
        this.finish();
    }
}
