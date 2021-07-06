package app.myapp.myhotel.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_notifyUser;
import app.myapp.myhotel.Model.ItemNoty;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class NotifyActivity extends AppCompatActivity {


   static RecyclerView recyclerView;
    Adapter_notifyUser adapter_notifyUser ;
    ArrayList<ItemNoty> arrayList;

       static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);


        recyclerView=findViewById(R.id.rec_noty_user);


       // textView=findViewById(R.id.text_test);



        dataMessagee();


        ItemTouchHelper.SimpleCallback itemTouchHelper= new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {


                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                //arrayList.remove(viewHolder.getAdapterPosition());

                adapter_notifyUser.deletePlanet(viewHolder.getAdapterPosition());
              // int id=arrayList.get(viewHolder.getAdapterPosition()).getId();

//               Toast.makeText(getBaseContext(),id+"id",Toast.LENGTH_SHORT).show();
//                Toast.makeText(getBaseContext(),viewHolder.getAdapterPosition()+"postion",Toast.LENGTH_SHORT).show();

                adapter_notifyUser.notifyDataSetChanged();
            }
        };



        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    Thread.sleep(3000);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            arrayList= dataMessageYes();
                            adapter_notifyUser=new Adapter_notifyUser(arrayList,getBaseContext());
                            recyclerView.setAdapter(adapter_notifyUser);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                          //Adapter_notifyUser  adapter_notifyUser=new Adapter_notifyUser(recyclerView);
                            new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);
                            recyclerView.setHasFixedSize(true);
                            adapter_notifyUser.notifyDataSetChanged();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();












    }

    private void dataMessagee() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<Integer> arrayList1 =new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NONOTY, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);


                       int id=screen.getInt("id");


                       arrayList1.add(id);

                    }


                    for (Integer i:arrayList1){

                        upDate(i);
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











    private ArrayList<ItemNoty> dataMessageYes() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<ItemNoty> arrayList1 =new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_YESNOTY, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemNoty itemNoty=new ItemNoty();

                        itemNoty.setId(screen.getInt("id"));
                        itemNoty.setName(screen.getString("namehotel"));
                        itemNoty.setContent(screen.getString("content"));
                        itemNoty.setTime(screen.getString("created_at"));

                         arrayList1.add(itemNoty);

                         adapter_notifyUser.notifyDataSetChanged();

                    }


//
//                    Adapter_notifyUser adapter_notifyUser=new Adapter_notifyUser(arrayList1);
//                    recyclerView.setAdapter(adapter_notifyUser);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
//                    recyclerView.setHasFixedSize(true);




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

        return arrayList1;
    }






    private void upDate(int id) {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("noty","yes");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, LinkServer.UP_NOTY+id, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    if (response.getBoolean("success")) {
                        Toast.makeText(getBaseContext(), response.getString("message"), Toast.LENGTH_SHORT).show();





                    } else {

                        Toast.makeText(getBaseContext(), "error", Toast.LENGTH_SHORT).show();

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
                map.put("Authorization", "Bearer " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);


    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(),Hotel_User.class));
        finish();
        super.onBackPressed();
    }
}