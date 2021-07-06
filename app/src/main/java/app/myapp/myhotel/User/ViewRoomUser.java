package app.myapp.myhotel.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_Rooms;
import app.myapp.myhotel.AdapterRecycler.Adapter_RoomsUser;
import app.myapp.myhotel.Evalution_Activity;
import app.myapp.myhotel.Model.Item_Rooms;
import app.myapp.myhotel.Model.Item_Rooms_user;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class ViewRoomUser extends AppCompatActivity {


    RecyclerView recyclerView,recyclerView2;
    Context context;
    int id,admin_id;
    Toolbar toolbar;
    String img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room_user);

        recyclerView=findViewById(R.id.recy_user);

         toolbar=findViewById(R.id.tol);
         setSupportActionBar(toolbar);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);
        admin_id=intent.getIntExtra("admin_id",-1);

        img=intent.getStringExtra("img");

        context=this;


        dataMessage();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_evaluation,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){


            case R.id.viewa:

                Intent intent1=new Intent(getBaseContext(),Evalution_Activity.class);
                intent1.putExtra("id",id);
                intent1.putExtra("img",img);
                startActivity(intent1);

                break;

            case R.id.filter_price:

                dataMessagee();

                break;

            case R.id.me_det:

                Intent intent=new Intent(getBaseContext(),Evalution_Activity.class);
                intent.putExtra("id",id);
                intent.putExtra("img",img);
                startActivity(intent);

                break;


        }

        return super.onOptionsItemSelected(item);


    }

    private void dataMessage() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<Item_Rooms_user> arrayList1 =new ArrayList<>();





        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_ROOMS_USER_HOTEL_URL+id, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        Item_Rooms_user item_rooms=new Item_Rooms_user();





                        item_rooms.setId(screen.getInt("id"));
                        item_rooms.setImage(screen.getString("imageroom1"));
                        screen.getString("imageroom2");
                        screen.getString("imageroom3");
                        item_rooms.setName_room(screen.getString("nameroom"));
                        item_rooms.setType_room(screen.getString("typeroom"));
                        item_rooms.setPrice_room(Integer.parseInt(screen.getString("priceroom")));
                        item_rooms.setEnable(screen.getString("enable"));
                        item_rooms.setAdmin_id(screen.getInt("details_id"));
                        screen.getString("numbed");
                        screen.getString("typebed");
                        screen.getString("Facilities");
                        screen.getString("numguest");
                        screen.getString("kids");
                        screen.getString("animals");
                        screen.getString("breckfast");
                        screen.getString("numroom");
                        screen.getString("access");
                        screen.getString("meansofcomfort");


                        arrayList1.add(item_rooms);


                    }


                    Adapter_RoomsUser adapter_rooms=new Adapter_RoomsUser(arrayList1,context,admin_id);

                    recyclerView.setAdapter(adapter_rooms);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getBaseContext());

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);

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

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<Item_Rooms_user> arrayList1 =new ArrayList<>();




        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_IMAGE_HOTEL_URL+id, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        Item_Rooms_user item_rooms=new Item_Rooms_user();

                        item_rooms.setId(screen.getInt("id"));
                        item_rooms.setImage(screen.getString("imageroom1"));
                        screen.getString("imageroom2");
                        screen.getString("imageroom3");
                        item_rooms.setName_room(screen.getString("nameroom"));
                        item_rooms.setType_room(screen.getString("typeroom"));
                        item_rooms.setPrice_room(Integer.parseInt(screen.getString("priceroom")));
                        item_rooms.setEnable(screen.getString("enable"));
                        item_rooms.setAdmin_id(screen.getInt("details_id"));
                        screen.getString("numbed");
                        screen.getString("typebed");
                        screen.getString("Facilities");
                        screen.getString("numguest");
                        screen.getString("kids");
                        screen.getString("animals");
                        screen.getString("breckfast");
                        screen.getString("numroom");
                        screen.getString("access");
                        screen.getString("meansofcomfort");

                        arrayList1.add(item_rooms);

                    }

                    Collections.sort(arrayList1, new Comparator<Item_Rooms_user>() {
                        @Override
                        public int compare(Item_Rooms_user lhs,Item_Rooms_user rhs) {

                            return rhs.getPrice_room() > lhs.getPrice_room() ? -1 : (rhs.getPrice_room() < lhs.getPrice_room() ) ? 1 : 0;
                        }
                    });




                    Adapter_RoomsUser adapter_rooms=new Adapter_RoomsUser(arrayList1,context,admin_id);

                    recyclerView.setAdapter(adapter_rooms);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getBaseContext());
                   // Collections.sort(arrayList1,Item_Rooms_user.comparator);

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);

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