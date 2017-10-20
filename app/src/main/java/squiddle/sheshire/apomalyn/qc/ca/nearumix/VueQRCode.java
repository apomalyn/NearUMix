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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO.UtilisateurDAO;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.parametre.VueProfil;

public class VueQRCode extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private UtilisateurDAO utilisateurDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_qrcode);
        utilisateurDAO = UtilisateurDAO.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mon QRCode");

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
        ImageView myImage = (ImageView) findViewById(R.id.imageView);
        try {
            myImage.setImageBitmap(UtilisateurDAO.getInstance().getUtilisateurCourant().getQRCode());
        } catch (WriterException e) {
            e.printStackTrace();
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
        getMenuInflater().inflate(R.menu.vue_qrcode, menu);
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

        if(id == R.id.carte){
            Intent changementVersCarte = new Intent(VueQRCode.this,VueMenu.class);
            startActivity(changementVersCarte);
        }
        else if(id == R.id.profil)
        {
            Intent changementVersProfil = new Intent(VueQRCode.this,VueUtilisateur.class);
            startActivity(changementVersProfil);
        } else if (id == R.id.parametre) {
            Intent changementVersCarte = new Intent(VueQRCode.this, VueProfil.class);
            startActivity(changementVersCarte);
            // Handle the camera action
        } else if (id == R.id.deconnexion) {
            Intent changementVersConnexion = new Intent(VueQRCode.this, VueConnexion.class);
            startActivity(changementVersConnexion);

        } else if(id == R.id.imageView){
            Intent chargementVersQRCode = new Intent(VueQRCode.this, VueQRCode.class);
            startActivity(chargementVersQRCode);
        } else if(id == R.id.amis){
            Intent changementVersAmis = new Intent(VueQRCode.this,VueAmis.class);
            startActivity(changementVersAmis);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
