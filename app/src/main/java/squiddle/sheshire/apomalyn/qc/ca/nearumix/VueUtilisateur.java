package squiddle.sheshire.apomalyn.qc.ca.nearumix;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.Utilisateur;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.parametre.VueProfil;

public class VueUtilisateur extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profil_titre=(TextView)findViewById(R.id.profil_titre);
        titre_nom=(TextView)findViewById(R.id.titre_nom);
        titre_mail=(TextView)findViewById(R.id.titre_mail);
        titre_niveau=(TextView)findViewById(R.id.titre_niveau);
        titre_exp=(TextView)findViewById(R.id.titre_exp);
        bouton_liste_amis=(Button)findViewById(R.id.bouton_liste_amis);
        bouton_liste_noire=(Button)findViewById(R.id.bouton_liste_noire);
        bouton_retour_carte=(Button)findViewById(R.id.bouton_retour_carte);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        getMenuInflater().inflate(R.menu.vue_utilisateur, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.profil)
        {
            Intent changementVersProfil = new Intent(VueUtilisateur.this,VueProfilUtilisateur.class);
            startActivity(changementVersProfil);
        } else if (id == R.id.parametre) {
            Intent changementVersCarte = new Intent(VueUtilisateur.this, VueProfil.class);
            startActivity(changementVersCarte);
            // Handle the camera action
        } else if (id == R.id.deconnexion) {
            Intent changementVersConnexion = new Intent(VueUtilisateur.this, VueConnexion.class);
            startActivity(changementVersConnexion);

        } else if(id == R.id.imageView){
            Intent chargementVersQRCode = new Intent(VueUtilisateur.this, VueQRCode.class);
            startActivity(chargementVersQRCode);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
