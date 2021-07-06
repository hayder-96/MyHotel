package app.myapp.myhotel.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_ResUser;
import app.myapp.myhotel.Model.InfoUser;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class MyReservation extends AppCompatActivity {

    RecyclerView recyclerView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);

        recyclerView=findViewById(R.id.rec_my_r);

        context=this;

        dataMessage();
    }



















    private void dataMessage() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<InfoUser> arrayList1 =new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.INSERT_USER_HOTEL, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);



                        InfoUser infoUser=new InfoUser();
                        infoUser.setId(screen.getInt("id"));
                        infoUser.setName(screen.getString("name"));
                        infoUser.setPhone(screen.getString("phone"));
                        infoUser.setEmail(screen.getString("email"));
                        infoUser.setCountry(screen.getString("country"));
                        infoUser.setNameroom(screen.getString("nameroom"));
                        infoUser.setTyperoom(screen.getString("typeroom"));
                        infoUser.setNumbed(screen.getString("numbed"));
                        infoUser.setTypebed(screen.getString("priceroom"));
                        infoUser.setPriceroom(screen.getString("typebed"));
                        infoUser.setAccess(screen.getString("access"));
                        infoUser.setLeaving(screen.getString("leaving"));
                        infoUser.setNumguest(screen.getString("numguest"));
                        infoUser.setTypetrip(screen.getString("typetrip"));
                        infoUser.setRoom_id(screen.getString("room_id"));
                        infoUser.setNamehotel(screen.getString("namehotel"));
                        infoUser.setUser_id(screen.getString("user_id"));



                        arrayList1.add(infoUser);
                    }

                    Adapter_ResUser adapter_resUser=new Adapter_ResUser(arrayList1,context);
                    recyclerView.setAdapter(adapter_resUser);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    recyclerView.setHasFixedSize(true);
                    adapter_resUser.notifyDataSetChanged();





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