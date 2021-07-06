package app.myapp.myhotel;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    double l,o;

    Intent intent;
    String namehotel, rating, country, city, manger, phone,email, image1, image2, image3;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        intent = getIntent();
        namehotel = intent.getStringExtra("namehotel");
        rating = intent.getStringExtra("rating");
        country = intent.getStringExtra("country");
        city = intent.getStringExtra("city");
        manger = intent.getStringExtra("manger");
        phone = intent.getStringExtra("phone");
        image1 = intent.getStringExtra("image1");
        image2 = intent.getStringExtra("image2");
        image3 = intent.getStringExtra("image3");
        email = intent.getStringExtra("email");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.setOnMapLongClickListener(this);
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }






    @Override
    public void onMapLongClick(LatLng latLng) {


        mMap.addMarker(new MarkerOptions().position(latLng).title("hotel").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        l=latLng.latitude;
        o=latLng.longitude;



    }



    public void addLoction(View view) {

        Intent intent=new Intent(getBaseContext(),AddHotel.class);

        intent.putExtra("namehotel",namehotel);
        intent.putExtra("image1",image1);
        intent.putExtra("image2",image2);
        intent.putExtra("image3",image3);
        intent.putExtra("country",country);
        intent.putExtra("city",city);
        intent.putExtra("manger",manger);
        intent.putExtra("phone",phone);
        intent.putExtra("rating",rating);
        intent.putExtra("email",email);
        intent.putExtra("l",l);
        intent.putExtra("o",o);
        startActivity(intent);
        finish();

    }
}