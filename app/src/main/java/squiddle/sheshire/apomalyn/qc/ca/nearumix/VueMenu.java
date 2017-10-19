package squiddle.sheshire.apomalyn.qc.ca.nearumix;

import android.*;
import android.Manifest;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO.PointInfluenceDAO;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO.UtilisateurDAO;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.PointInfluence;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.parametre.VueProfil;

public class VueMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private Fragment mapFragment;
    private GoogleMap mMap;
    private PointInfluenceDAO point_influence_dao = null;

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        point_influence_dao = PointInfluenceDAO.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String string = "Vous etes ici";
                        mMap.addMarker(new MarkerOptions().position(latLng).title(string));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.2f));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
        else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude,longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude,longitude,1);
                        String string = addressList.get(0).getLocality() + " , ";
                        string += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(string));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.2f));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });

            }
//        Intent intent = new Intent(this, AlertReceiver.class);
//
//
//        PendingIntent pending = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//// On ajoute une alerte de proximité si on s'approche ou s'éloigne du bâtiment de Simple IT
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        locationManager.addProximityAlert(48.872808, 2.33517, 150, -1, pending);
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
        getMenuInflater().inflate(R.menu.vue_menu, menu);
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
            Intent changementVersProfil = new Intent(VueMenu.this,VueUtilisateur.class);
            startActivity(changementVersProfil);
        } else if (id == R.id.parametre) {
            Intent changementVersCarte = new Intent(VueMenu.this, VueProfil.class);
            startActivity(changementVersCarte);
            // Handle the camera action
        } else if (id == R.id.deconnexion) {
            Intent changementVersConnexion = new Intent(VueMenu.this, VueConnexion.class);
            startActivity(changementVersConnexion);

        } else if(id == R.id.imageView){
            Intent chargementVersQRCode = new Intent(VueMenu.this, VueQRCode.class);
            startActivity(chargementVersQRCode);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        //mMap.addMarker(new MarkerOptions().position(point_influence_dao.getPointInfluence(0).getCoordonnees()).title(point_influence_dao.getPointInfluence(0).getNom()));

        List<PointInfluence> liste_pi = point_influence_dao.getPointsInfluence();

        for(PointInfluence pi : liste_pi) {
            mMap.addMarker(new MarkerOptions().position(pi.getCoordonnees()).title(pi.getNom()));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(pi.getCoordonnees()));
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().equals("Vous etes ici")){
                    return true;
                }
                Intent intent_aller_vers_vue_PI = new Intent (VueMenu.this, VuePI.class);
                intent_aller_vers_vue_PI.putExtra("id_PI", point_influence_dao.getPointInfluenceParNom(marker.getTitle()).getId());
                startActivityForResult(intent_aller_vers_vue_PI, -1);
                return true;
            }
        });

        /*PointInfluence pi = point_influence_dao.getPointInfluence(0);
        LatLng matane = pi.getCoordonnees();
        mMap.addMarker(new MarkerOptions().position(matane).title(pi.getNom()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(matane));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent_aller_vers_vue_PI = new Intent(VueMenu.this, VuePointInfluence.class);
                intent_aller_vers_vue_PI.putExtra("id_PI", point_influence_dao.getPointInfluenceParNom(marker.getTitle()).getId());
                startActivityForResult(intent_aller_vers_vue_PI, -1);
                return true;
            }
        });*/
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
    }

    //@Override
    //public void onPointerCaptureChanged(boolean hasCapture) {
//
  //  }
}
