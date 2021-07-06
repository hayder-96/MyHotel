package app.myapp.myhotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_Home;
import app.myapp.myhotel.AdapterRecycler.Adapter_Rooms;
import app.myapp.myhotel.Model.InfoHotel;
import app.myapp.myhotel.Model.Item_Rooms;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class ViewRooms extends AppCompatActivity {

    RecyclerView recyclerView;
    int id;
    Context context;
    String im;
     Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rooms);

        recyclerView=findViewById(R.id.recc);

        toolbar=findViewById(R.id.toolbar_view);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);
        im=intent.getStringExtra("image");


        setSupportActionBar(toolbar);

        context=this;
        Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();

        dataMessage();

    }














    public void addroom(View view) {

        Intent intent=new Intent(getBaseContext(),AddNextHotel.class);

        intent.putExtra("id",id);
        intent.putExtra("im",im);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_descreption,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.id_add_desc:

                Intent intent=new Intent(getBaseContext(),Desc_Activity.class);
                intent.putExtra("id",id);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void dataMessage() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<Item_Rooms> arrayList1 =new ArrayList<>();




        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_IMAGE_HOTEL_URL+id, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        Item_Rooms item_rooms=new Item_Rooms();

                        item_rooms.setId(screen.getInt("id"));

                        item_rooms.setImage(screen.getString("imageroom1"));

                        item_rooms.setName_room(screen.getString("nameroom"));

                        item_rooms.setType_room(screen.getString("typeroom"));

                        item_rooms.setPrice_room(screen.getString("priceroom"));

                        item_rooms.setEnable(screen.getString("enable"));
                        item_rooms.setShow(screen.getString("show"));


                        arrayList1.add(item_rooms);


                    }


                    Adapter_Rooms adapter_rooms=new Adapter_Rooms(arrayList1,context);

                    recyclerView.setAdapter(adapter_rooms);
                    LayoutManager layoutManager=new LinearLayoutManager(getBaseContext());

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