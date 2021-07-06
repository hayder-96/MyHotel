package app.myapp.myhotel.SERVICE;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import app.myapp.myhotel.AdapterRecycler.Adapter_ResUser;
import app.myapp.myhotel.MainActivity;
import app.myapp.myhotel.Model.InfoUser;
import app.myapp.myhotel.Model.ItemNoty;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;
import app.myapp.myhotel.User.Hotel_User;

public class MyService extends Service {
    public MyService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Toast.makeText(getBaseContext(),"start",Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    try {
                        Thread.sleep(5000);
                        dataMessage();
                        dataMessagee();
                        datanoty();
                        Alldatanoty();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");


    }



    public void ShowNotificationn(String name,int id)
    {

        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setContentTitle(name)
                .setContentText("لديك حجز")
                .setVibrate(new long[]{500, 1000, 500, 1000, 500})
                .setSmallIcon(R.drawable.ic_photo)
                .setDefaults(Notification.DEFAULT_ALL);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntent(intent);
        taskStackBuilder.addParentStack(MainActivity.class);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.addAction(R.drawable.ic_photo, "مشاهدته", pendingIntent);
        notificationManager.notify(new Random().nextInt(), builder.build());




        upDate(id);
    }










    public void ShowNotification(String name,int id,String con)
    {

        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                .setContentTitle(name)
                .setContentText(con)
                .setVibrate(new long[]{500, 1000, 500, 1000, 500})
                .setSmallIcon(R.drawable.ic_photo)
                .setDefaults(Notification.DEFAULT_ALL);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntent(intent);
        taskStackBuilder.addParentStack(MainActivity.class);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.addAction(R.drawable.ic_photo, "مشاهدة", pendingIntent);
        notificationManager.notify(new Random().nextInt(), builder.build());




        upDatee(id);
    }















    private void dataMessage() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<InfoUser> arrayList1 =new ArrayList<>();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NOTY, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);



                        InfoUser infoUser=new InfoUser();
                        infoUser.setId(screen.getInt("id"));
                        infoUser.setName(screen.getString("name"));


                        arrayList1.add(infoUser);
                    }



                    for (InfoUser i:arrayList1){

                        ShowNotificationn(i.getName(),i.getId());

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
            jsonObject.put("noty","no");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, LinkServer.GET_HOTEL_URL+"/"+id, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    if (response.getBoolean("success")) {
                        Toast.makeText(getBaseContext(), response.getString("message"), Toast.LENGTH_SHORT).show();

                        //progressDialog.dismiss();

                    } else {

                        Toast.makeText(getBaseContext(), "error", Toast.LENGTH_SHORT).show();
                        //  progressDialog.dismiss();
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

















    private void dataMessagee() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<String> arrayList1 =new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NONOTY, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);


                        arrayList1.add(screen.getString("noty"));


                    }

                    if (arrayList1.size()!=0) {

                        Hotel_User.gettext(arrayList1.size());
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







    private void datanoty() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<ItemNoty> arrayList1 =new ArrayList<>();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NOTY_NO, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemNoty itemNoty=new ItemNoty();


                        itemNoty.setId(screen.getInt("id"));
                        itemNoty.setName(screen.getString("name"));
                        itemNoty.setContent(screen.getString("content"));


                        arrayList1.add(itemNoty);
                        }


                    for (ItemNoty t:arrayList1){

                        ShowNotification(t.getName(),t.getId(),t.getContent());
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














    private void Alldatanoty() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<ItemNoty> arrayList1 =new ArrayList<>();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_NOTY_ALL_NO, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemNoty itemNoty=new ItemNoty();


                        itemNoty.setId(screen.getInt("id"));
                        itemNoty.setName(screen.getString("name"));
                        itemNoty.setContent(screen.getString("content"));


                        arrayList1.add(itemNoty);
                    }


                    for (ItemNoty t:arrayList1){

                        ShowNotification(t.getName(),t.getId(),t.getContent());
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






















    private void upDatee(int id) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("noty","yes");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, LinkServer.GET_NOTY_NO+"/"+id, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {

                    if (response.getBoolean("success")) {
                        Toast.makeText(getBaseContext(), response.getString("message"), Toast.LENGTH_SHORT).show();

                        //progressDialog.dismiss();

                    } else {

                        Toast.makeText(getBaseContext(), "error", Toast.LENGTH_SHORT).show();
                        //  progressDialog.dismiss();
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

}