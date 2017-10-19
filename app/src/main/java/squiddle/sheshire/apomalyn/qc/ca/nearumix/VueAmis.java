package squiddle.sheshire.apomalyn.qc.ca.nearumix;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO.UtilisateurDAO;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.Utilisateur;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.parametre.VueProfil;

public class VueAmis extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected ListView vue_liste_amis = null;
    UtilisateurDAO utilisateurDAO = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_amis);
        utilisateurDAO = UtilisateurDAO.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Liste des amis");

        vue_liste_amis = (ListView)findViewById(R.id.liste_amis);
        afficherAmis();


        vue_liste_amis.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView vue_liste_amis = (ListView)view.getParent();

                @SuppressWarnings("unchecked")
                HashMap<String, String> amis = (HashMap<String, String>)vue_liste_amis.getItemAtPosition((int)i);

//                Utilisateur usuppr = utilisateurDAO.getUtilisateurParId(Integer.parseInt(amis.get("id")));
//                utilisateurDAO.supprimerAmis(usuppr);
                //TODO Pop up pour supprimer amis
                Intent changerVue = new Intent(VueAmis.this,ConfirmerSuppressionAmis.class);
                changerVue.putExtra("ami", Integer.parseInt(amis.get("id")));
                startActivityForResult(changerVue, 1);

            }
        });



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

    public void afficherAmis()
    {
        utilisateurDAO.getUtilisateurCourant();
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                utilisateurDAO.getInstance().getUtilisateurCourant().getListeAmisToHashMap(),
                android.R.layout.two_line_list_item,
                new String[] {"nom", "niveau", "id"},
                new int[] {android.R.id.text1, android.R.id.text2}
        );

        vue_liste_amis.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.vue_amis, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.profil)
        {
            Intent changementVersProfil = new Intent(VueAmis.this,VueProfilUtilisateur.class);
            startActivity(changementVersProfil);
        } else if (id == R.id.parametre) {
            Intent changementVersCarte = new Intent(VueAmis.this, VueProfil.class);
            startActivity(changementVersCarte);
            // Handle the camera action
        } else if (id == R.id.deconnexion) {
            Intent changementVersConnexion = new Intent(VueAmis.this, VueConnexion.class);
            startActivity(changementVersConnexion);

        } else if(id == R.id.imageView){
            Intent chargementVersQRCode = new Intent(VueAmis.this, VueQRCode.class);
            startActivity(chargementVersQRCode);
        } else if(id == R.id.amis){
            Intent changementVersAmis = new Intent(VueAmis.this,VueAmis.class);
            startActivity(changementVersAmis);
        } else if(id == R.id.ajoutAmis){
            Intent changementVersAjoutAmis = new Intent(VueAmis.this , VueAjouterAmis.class);
            startActivity(changementVersAjoutAmis);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case 1:
                afficherAmis();
                break;
        }
    }
}
