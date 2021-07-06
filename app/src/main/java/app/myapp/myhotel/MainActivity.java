package app.myapp.myhotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.Model.ItemNoty;
import app.myapp.myhotel.RegisterAndLogin.LoginActivity;
import app.myapp.myhotel.SERVICE.MyService;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;
import app.myapp.myhotel.User.LoginActivityUser;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private String token;



    Toolbar toolbar;

    ImageView imageView;
    TextView textView,t1,t2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestQueue = Volley.newRequestQueue(this);
        if (SaveToken.getInstanse(this).is_Login()) {

            if (SaveToken.getInstanse(this).getToken() != null) {
                token = SaveToken.getInstanse(this).getToken().getToken();

            }
        } else {
            finish();
            Toast.makeText(getBaseContext(), "No save token", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, LoginActivity.class));
            return;
        }


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        imageView=findViewById(R.id.image_noty_admin);
        textView=findViewById(R.id.textView_notyy_admin);

        t1=findViewById(R.id.text_1);
        t2=findViewById(R.id.text_2);






        Alldatanotyes();
        datanotyes();


















        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getBaseContext(),NotifayAdmin.class));
                     finish();


            }
        });



        datanoty();









        Intent intent=new Intent(this, MyService.class);
        startService(intent);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layot,
                new Home()).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()) {
                    case R.id.home:


                        fragment=new Home();
                        toolbar.setVisibility(View.VISIBLE);
                        break;

                    case R.id.star:

                        fragment=new Request_User();
                        toolbar.setVisibility(View.GONE);
                        break;



                    case R.id.done:

                       // fragment=new FragmentTran();
                        toolbar.setVisibility(View.GONE);
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layot,
                        fragment).commit();
                return true;
            }
        });





    }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {


            getMenuInflater().inflate(R.menu.add_menu,menu);



            return super.onCreateOptionsMenu(menu);
        }














    private void Alldatanotyes() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<String> arrayList1 =new ArrayList<>();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NOTY_ALL_YES, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);


                        arrayList1.add(screen.getString("noty"));

                    }


                    t1.setText(arrayList1.size()+"");




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






    private void datanotyes() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<String> arrayList1 =new ArrayList<>();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NOTY_YES, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        arrayList1.add(screen.getString("noty"));



                    }


                    t2.setText(arrayList1.size()+"");



                    if (t1.getText().toString().trim()!=null && t2.getText().toString().trim() !=null) {


                        int n = Integer.parseInt(t1.getText().toString().trim());
                        int v = Integer.parseInt(t2.getText().toString().trim());

                        if (n != 0 && v != 0) {
                            textView.setText(n + v + "");
                        }
                    }











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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_item:

                Intent intent=new Intent(getBaseContext(),AddHotel.class);
                finish();
                startActivity(intent);

                return true;
            case R.id.out_log:
                SaveToken.getInstanse(getBaseContext()).user_Logout();
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
                finish();
        }

        return false;
    }



    private void datanoty() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<String> arrayList1 =new ArrayList<>();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NOTY_NO, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);




                        arrayList1.add(screen.getString("content"));


                    }

                    if (arrayList1.size()!=0) {

                       textView.setText(arrayList1.size()+"");

                    }




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



















}