package squiddle.sheshire.apomalyn.qc.ca.nearumix;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO.PointInfluenceDAO;
import squiddle.sheshire.apomalyn.qc.ca.nearumix.modele.PointInfluence;

public class VueCarte extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private PointInfluenceDAO point_influence_dao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_carte);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        point_influence_dao = PointInfluenceDAO.getInstance();
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

        PointInfluence pi = point_influence_dao.getPointInfluence(0);
        LatLng matane = pi.getCoordonnees();
        mMap.addMarker(new MarkerOptions().position(matane).title(pi.getNom()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(matane));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent_aller_vers_vue_PI = new Intent (VueCarte.this, VuePointInfluence.class);
                intent_aller_vers_vue_PI.putExtra("id_PI", point_influence_dao.getPointInfluenceParNom(marker.getTitle()).getId());
                startActivityForResult(intent_aller_vers_vue_PI, -1);
                return true;
            }
        });
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
