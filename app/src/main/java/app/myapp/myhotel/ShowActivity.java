package app.myapp.myhotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_RoomsUser;
import app.myapp.myhotel.AdapterRecycler.Adapter_Show;
import app.myapp.myhotel.Model.ItemShow;
import app.myapp.myhotel.Model.Item_Rooms_user;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class ShowActivity extends AppCompatActivity {

    int id;
    RecyclerView recyclerView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        recyclerView=findViewById(R.id.reec);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);

        context=this;


        dataMessage();
    }



    private void dataMessage() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<ItemShow> arrayList1 =new ArrayList<>();





        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_SHOW+id, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemShow item_rooms=new ItemShow();

                        item_rooms.setId(screen.getInt("id"));

                        item_rooms.setName(screen.getString("name"));
                        item_rooms.setContent(screen.getString("content"));
                        item_rooms.setTime(screen.getString("created_at"));



                        arrayList1.add(item_rooms);


                    }


                    Adapter_Show adapter_rooms=new Adapter_Show(arrayList1,context);

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
}