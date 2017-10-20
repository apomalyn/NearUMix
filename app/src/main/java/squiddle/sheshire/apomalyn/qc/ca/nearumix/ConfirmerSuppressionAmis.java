package squiddle.sheshire.apomalyn.qc.ca.nearumix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO.UtilisateurDAO;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.R;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.Utilisateur;

public class ConfirmerSuppressionAmis extends AppCompatActivity {

    private UtilisateurDAO utilisateurDAO = null;
    private TextView titre;
    private Button oui;
    private Button non;
    private Utilisateur utilisateurSuppr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmer_suppression_amis);
        utilisateurDAO = UtilisateurDAO.getInstance();
        titre=(TextView)findViewById(R.id.titreSuppression);
        TextView ami = (TextView)findViewById(R.id.nomAmi);
        Bundle parametres = this.getIntent().getExtras();
        utilisateurSuppr = utilisateurDAO.getUtilisateurParId(parametres.getInt("ami"));
        ami.setText(utilisateurSuppr.getNom());
        oui=(Button) findViewById(R.id.boutonOui);
        non=(Button) findViewById(R.id.boutonNon);
    }

    public void ouiB(View vue){
        if(utilisateurDAO.supprimerAmis(utilisateurSuppr)){
            utilisateurDAO.setUtilisateurCourant(utilisateurDAO.getUtilisateurCourant().getMail());
            utilisateurDAO.getUtilisateurCourant().supprimerAmis(utilisateurSuppr);
            this.finish();
        }

    }

    public void nonB(View vue){
        this.finish();
    }
}
