package app.myapp.myhotel.User;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import app.myapp.myhotel.R;

public class MapsUser extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Intent intent;
    String l,o,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_user);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        intent=getIntent();
        n=intent.getStringExtra("name");
        l=intent.getStringExtra("lat");
        o=intent.getStringExtra("log");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        double d=Double.parseDouble(l);
        double dd=Double.parseDouble(o);
        LatLng sydney = new LatLng(d, dd);
        mMap.addMarker(new MarkerOptions().position(sydney).title(n));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}