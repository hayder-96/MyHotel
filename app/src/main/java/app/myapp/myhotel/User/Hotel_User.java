package app.myapp.myhotel.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_Home;
import app.myapp.myhotel.AdapterRecycler.Adapter_Home_User;
import app.myapp.myhotel.Home;
import app.myapp.myhotel.MainActivity;
import app.myapp.myhotel.MapsActivity;
import app.myapp.myhotel.Model.InfoHotel;
import app.myapp.myhotel.Model.InfoHotelUser;
import app.myapp.myhotel.Model.Item_Rooms_user;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class Hotel_User extends AppCompatActivity {


    Toolbar toolbar;
    String co,ci;
    Context context;
    RecyclerView recyclerView;
    Adapter_Home_User adapter_home;
    static  TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel__user);



        toolbar=findViewById(R.id.toolba);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.id_rec);

        context=this;

        imageView=findViewById(R.id.image_notyy);

        textView=findViewById(R.id.textView_notyy);



        Intent intent=getIntent();

        co=intent.getStringExtra("country");

        ci=intent.getStringExtra("city");



        dataMessage();








        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1=new Intent(Hotel_User.this,NotifyActivity.class);
                startActivity(intent1);
                finish();
            }
        });










    }




    public static void gettext(int s){

        textView.setText(s+"");

    }








    private void dataMessage() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<InfoHotelUser> arrayList1 =new ArrayList<>();


        JSONObject js = new JSONObject();
        try {
            js.put("city",ci);
            js.put("coutry",co);
        } catch (Exception e) {
            e.printStackTrace();
        }



        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.GET_SEARCH_HOTEL, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);


                        InfoHotelUser infoHotel=new InfoHotelUser();

                        infoHotel.setId(screen.getInt("id"));
                        infoHotel.setNamehotel(screen.getString("namehotel"));
                        infoHotel.setEvaluation(screen.getString("evaluation"));
                        infoHotel.setImage1(screen.getString("image1"));
                        infoHotel.setImage2(screen.getString("image2"));
                        infoHotel.setImage3(screen.getString("image3"));
                        infoHotel.setCountry(screen.getString("country"));
                        infoHotel.setCity(screen.getString("city"));
                        infoHotel.setLatitude(screen.getString("latitude"));
                        infoHotel.setLongtude(screen.getString("longtude"));
                        infoHotel.setEv(screen.getString("ev"));
                        infoHotel.setAdmin_id(screen.getInt("admin_id"));




                        arrayList1.add(infoHotel);


                    }

                   Adapter_Home_User adapter_home=new Adapter_Home_User(arrayList1,context);

                    recyclerView.setAdapter(adapter_home);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setHasFixedSize(true);
                   // Collections.sort(arrayList1,InfoHotelUser.comparator);
                    adapter_home.notifyDataSetChanged();


                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> map = new HashMap<>();
                map.put("Accept", "application/json");
                map.put("Authorization", "Bearer  " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);




    }




    private void dataMessagee() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<InfoHotelUser> arrayList1 =new ArrayList<>();


        JSONObject js = new JSONObject();
        try {
            js.put("city",ci);
            js.put("coutry",co);
        } catch (Exception e) {
            e.printStackTrace();
        }



        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.GET_SEARCH_HOTEL, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);


                        InfoHotelUser infoHotel=new InfoHotelUser();

                        infoHotel.setId(screen.getInt("id"));
                        infoHotel.setNamehotel(screen.getString("namehotel"));
                        infoHotel.setEvaluation(screen.getString("evaluation"));
                        infoHotel.setImage1(screen.getString("image1"));
                        infoHotel.setImage2(screen.getString("image2"));
                        infoHotel.setImage3(screen.getString("image3"));
                        infoHotel.setCountry(screen.getString("country"));
                        infoHotel.setCity(screen.getString("city"));
                        infoHotel.setLatitude(screen.getString("latitude"));
                        infoHotel.setLongtude(screen.getString("longtude"));
                        infoHotel.setEv(screen.getString("ev"));
                        infoHotel.setAdmin_id(screen.getInt("admin_id"));
                        arrayList1.add(infoHotel);
                      //  adapter_home.notifyDataSetChanged();
                    }


                    //ArrayList<InfoHotelUser> arrayList1=dataMessagee();
                    Collections.sort(arrayList1, new Comparator<InfoHotelUser>() {
                        @Override
                        public int compare(InfoHotelUser lhs,InfoHotelUser rhs) {
                            // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                            return Float.parseFloat(lhs.getEvaluation()) > Float.parseFloat(rhs.getEvaluation()) ? -1 : (Float.parseFloat(lhs.getEvaluation()) < Float.parseFloat(rhs.getEvaluation()) ) ? 1 : 0;
                        }
                    });
                    adapter_home=new Adapter_Home_User(arrayList1,context);

                    recyclerView.setAdapter(adapter_home);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setHasFixedSize(true);
                  //  Collections.sort(arrayList1,InfoHotelUser.comparator);
                    adapter_home.notifyDataSetChanged();



                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> map = new HashMap<>();
                map.put("Accept", "application/json");
                map.put("Authorization", "Bearer  " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);




    }


    private void dataMessageee() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<InfoHotelUser> arrayList1 =new ArrayList<>();


        JSONObject js = new JSONObject();
        try {
            js.put("city",ci);
            js.put("coutry",co);
        } catch (Exception e) {
            e.printStackTrace();
        }



        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.GET_SEARCH_HOTEL, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);


                        InfoHotelUser infoHotel=new InfoHotelUser();

                        infoHotel.setId(screen.getInt("id"));
                        infoHotel.setNamehotel(screen.getString("namehotel"));
                        infoHotel.setEvaluation(screen.getString("evaluation"));
                        infoHotel.setImage1(screen.getString("image1"));
                        infoHotel.setImage2(screen.getString("image2"));
                        infoHotel.setImage3(screen.getString("image3"));
                        infoHotel.setCountry(screen.getString("country"));
                        infoHotel.setCity(screen.getString("city"));
                        infoHotel.setLatitude(screen.getString("latitude"));
                        infoHotel.setLongtude(screen.getString("longtude"));
                        infoHotel.setEv(screen.getString("ev"));
                        infoHotel.setAdmin_id(screen.getInt("admin_id"));
                        arrayList1.add(infoHotel);
                        //  adapter_home.notifyDataSetChanged();
                    }


                    //ArrayList<InfoHotelUser> arrayList1=dataMessagee();
                    adapter_home=new Adapter_Home_User(arrayList1,context);

                    recyclerView.setAdapter(adapter_home);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setHasFixedSize(true);
                    Collections.sort(arrayList1,InfoHotelUser.comparator2);
                    adapter_home.notifyDataSetChanged();



                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> map = new HashMap<>();
                map.put("Accept", "application/json");
                map.put("Authorization", "Bearer  " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);




    }






    private void dataMessagev() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<InfoHotelUser> arrayList1 =new ArrayList<>();


        JSONObject js = new JSONObject();
        try {
            js.put("city",ci);
            js.put("coutry",co);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Toast.makeText(getBaseContext(),"out",Toast.LENGTH_SHORT).show();
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.GET_SEARCH_HOTEL, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);


                        InfoHotelUser infoHotel=new InfoHotelUser();

                        infoHotel.setId(screen.getInt("id"));
                        infoHotel.setNamehotel(screen.getString("namehotel"));
                        infoHotel.setEvaluation(screen.getString("evaluation"));
                        infoHotel.setImage1(screen.getString("image1"));
                        infoHotel.setImage2(screen.getString("image2"));
                        infoHotel.setImage3(screen.getString("image3"));
                        infoHotel.setCountry(screen.getString("country"));
                        infoHotel.setCity(screen.getString("city"));
                        infoHotel.setLatitude(screen.getString("latitude"));
                        infoHotel.setLongtude(screen.getString("longtude"));
                        infoHotel.setAdmin_id(screen.getInt("admin_id"));
                        infoHotel.setEv(screen.getString("ev"));
                        arrayList1.add(infoHotel);


                    }

                    Adapter_Home_User adapter_home=new Adapter_Home_User(arrayList1,context);

                    recyclerView.setAdapter(adapter_home);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setHasFixedSize(true);
                     Collections.sort(arrayList1,InfoHotelUser.comparator3);
                    adapter_home.notifyDataSetChanged();


                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> map = new HashMap<>();
                map.put("Accept", "application/json");
                map.put("Authorization", "Bearer  " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);




    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_user,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.reg_hotel:

                startActivity(new Intent(this, MainActivity.class));

                break;

            case R.id.order_name:
                dataMessageee();

                break;

            case R.id.order_ev:

                dataMessagee();
                break;

            case R.id.order_eve:

                dataMessagev();
                break;

            case R.id.reg_out:
                SaveTokenUser.getInstanse(this).user_Logout();

                startActivity(new Intent(this,LoginActivityUser.class));
                break;

            case R.id.my_res:

                startActivity(new Intent(getBaseContext(),MyReservation.class));




        }


        return super.onOptionsItemSelected(item);
    }


}