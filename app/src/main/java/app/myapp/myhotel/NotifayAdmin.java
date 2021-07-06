package app.myapp.myhotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_notifyUser;
import app.myapp.myhotel.Model.ItemNoty;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;
import app.myapp.myhotel.User.Hotel_User;

public class NotifayAdmin extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_notifyUser adapter_notifyUser;
    Context context;
    ArrayList<ItemNoty> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifay_admin);

        recyclerView=findViewById(R.id.recy_noty_admin);

        context=this;


       Alldatanotyes();
        dataMessageee();




        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dataMessageyES();
                            dataMessageAllyES();

                            arrayList=new ArrayList<>();

                            adapter_notifyUser=new Adapter_notifyUser(arrayList,context);
                            recyclerView.setAdapter(adapter_notifyUser);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                            recyclerView.setHasFixedSize(true);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        thread.start();








    }

















    private void dataMessageyES() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<ItemNoty> arrayList1 =new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_OPEN_YES, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemNoty itemNoty=new ItemNoty();

                        itemNoty.setId(screen.getInt("id"));
                        itemNoty.setName(screen.getString("noty"));
                        itemNoty.setContent(screen.getString("content"));
                        itemNoty.setTime(screen.getString("created_at"));

                        Toast.makeText(getBaseContext(),screen.getString("content"),Toast.LENGTH_SHORT).show();
                        arrayList1.add(itemNoty);


                        adapter_notifyUser.notifyDataSetChanged();

                    }

                    arrayList.addAll(arrayList1);


//
//
//                    Adapter_notifyUser adapter_notifyUser=new Adapter_notifyUser(arrayList1,context);
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


    }




    private void dataMessageAllyES() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<ItemNoty> arrayList1 =new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_OPEN_ALL, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemNoty itemNoty=new ItemNoty();

                        itemNoty.setId(screen.getInt("id"));
                        itemNoty.setName(screen.getString("noty"));
                        itemNoty.setContent(screen.getString("content"));
                         itemNoty.setTime(screen.getString("created_at"));

                        Toast.makeText(getBaseContext(),screen.getString("content"),Toast.LENGTH_SHORT).show();
                        arrayList1.add(itemNoty);

                         adapter_notifyUser.notifyDataSetChanged();

                    }

                    arrayList.addAll(arrayList1);


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

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<Integer> arrayList1 =new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NOTY_YES, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);


                        int ii=screen.getInt("id");
                        String n=screen.getString("noty");

                        if (n.equals("yes")){
                            upDate(ii);
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




    private void dataMessagee() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<Integer> arrayList1 =new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NOTY_ALL_YES, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);


                        int id=screen.getInt("id");

                        String n=screen.getString("noty");

                        Toast.makeText(getBaseContext(),id+"i",Toast.LENGTH_SHORT).show();


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








    private void Alldatanotyes() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<Integer> arrayList1 =new ArrayList<>();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NOTY_ALL_YES, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);


                        arrayList1.add(screen.getInt("id"));

                        int ii=screen.getInt("id");
                        String n=screen.getString("noty");

                        if (n.equals("yes")){
                            upDate(ii);
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


























    private void upDate(int id) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("noty","open");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, LinkServer.GET_NOTY_NO+"/"+id, jsonObject, new Response.Listener<JSONObject>() {
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
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();
        super.onBackPressed();
    }
}