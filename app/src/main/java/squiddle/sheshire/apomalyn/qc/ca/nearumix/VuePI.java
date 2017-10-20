package squiddle.sheshire.apomalyn.qc.ca.nearumix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO.PointInfluenceDAO;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO.UtilisateurDAO;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.PointInfluence;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.parametre.VueProfil;

public class VuePI extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    PointInfluenceDAO point_influence_dao;
    PointInfluence pi_courant;
    protected TextView nom_pi;
    protected TextView nom_musique;
    protected ProgressBar avancement_musique;
    protected Button bouton_lecture;
    protected Button boutonVotePour;
    protected Button boutonVoteContre;
    protected Button bouton_retour_carte;
    private UtilisateurDAO utilisateurDAO;

    private boolean aVote = false;
    private String aDejaVoter = "Vous avez deja vote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_pi);
        Bundle parametres = this.getIntent().getExtras();

        utilisateurDAO = UtilisateurDAO.getInstance();
        point_influence_dao = PointInfluenceDAO.getInstance();
        pi_courant = point_influence_dao.getPointInfluence(parametres.getInt("id_PI"));

        nom_pi = (TextView) findViewById(R.id.nom_pi);
        nom_musique = (TextView) findViewById(R.id.nom_musique);
        avancement_musique = (ProgressBar) findViewById(R.id.avancement_musique);
        bouton_lecture = (Button) findViewById(R.id.bouton_lecture);
        boutonVotePour = (Button) findViewById(R.id.bouton_votePour);
        boutonVoteContre = (Button) findViewById(R.id.bouton_voteContre);
        bouton_retour_carte = (Button) findViewById(R.id.bouton_retour_carte);

        nom_pi.setText(pi_courant.getNom());
        nom_musique.setText(pi_courant.getMusique().getNom());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(pi_courant.getNom());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View vue = navigationView.getHeaderView(0);
        TextView titre = (TextView)vue.findViewById(R.id.pseudoMenu);
        titre.setText(utilisateurDAO.getUtilisateurCourant().getNom());

        TextView mail = (TextView)vue.findViewById(R.id.mailMenu);
        mail.setText(utilisateurDAO.getUtilisateurCourant().getMail());
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void retourCarte(View vue) {
        this.finish();
    }

    public void votePour(View view) {
        if (!this.aVote) {
            this.point_influence_dao.votePointInfluence(this.pi_courant, true);
            this.aVote = true;
        } else{
            Toast message = Toast.makeText(getApplicationContext(), aDejaVoter, Toast.LENGTH_SHORT);
            message.show();
        }
    }

    public void voteContre(View view) {
        if (!this.aVote) {
            this.point_influence_dao.votePointInfluence(this.pi_courant, false);
            this.aVote = true;
        } else{
            Toast message = Toast.makeText(getApplicationContext(), aDejaVoter, Toast.LENGTH_SHORT);
            message.show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vue_pi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.carte){
            Intent changementVersCarte = new Intent(VuePI.this,VueMenu.class);
            startActivity(changementVersCarte);
        }
        else if (id == R.id.profil) {
            Intent changementVersProfil = new Intent(VuePI.this, VueUtilisateur.class);
            startActivity(changementVersProfil);
        } else if (id == R.id.parametre) {
            Intent changementVersCarte = new Intent(VuePI.this, VueProfil.class);
            startActivity(changementVersCarte);
            // Handle the camera action
        } else if (id == R.id.deconnexion) {
            Intent changementVersConnexion = new Intent(VuePI.this, VueConnexion.class);
            startActivity(changementVersConnexion);

        } else if (id == R.id.imageView) {
            Intent chargementVersQRCode = new Intent(VuePI.this, VueQRCode.class);
            startActivity(chargementVersQRCode);
        } else if(id == R.id.amis){
            Intent chargementVersAmis = new Intent(VuePI.this,VueAmis.class);
            startActivity(chargementVersAmis);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
