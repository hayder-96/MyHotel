package app.myapp.myhotel.AdapterRecycler;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.Model.InfoUser;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class Adapter_ResUser extends RecyclerView.Adapter<Adapter_ResUser.MyHolder> {

    ArrayList<InfoUser> arrayList;

    Context context;
    public Adapter_ResUser(ArrayList<InfoUser> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter_ResUser.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.split_user_re,parent,false);


        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ResUser.MyHolder holder, int position) {

        InfoUser infoUser=arrayList.get(position);
        holder.name.setText(infoUser.getName());
        holder.phone.setText(infoUser.getPhone());
        holder.email.setText(infoUser.getEmail());
        holder.country.setText(infoUser.getCountry());
        holder.namerrom.setText(infoUser.getNameroom());
        holder.typeroom.setText(infoUser.getTyperoom());
        holder.namebed.setText(infoUser.getNumbed());
        holder.typebed.setText(infoUser.getTypebed());
        holder.typetrip.setText(infoUser.getTypetrip());
        holder.access.setText(infoUser.getAccess());
        holder.leaving.setText(infoUser.getLeaving());
        holder.priceroom.setText(infoUser.getPriceroom());
       String nm=infoUser.getNamehotel();

        String user_id=infoUser.getUser_id();

        String room=infoUser.getRoom_id();


        dataMessage(user_id,holder.no,holder.yes);




        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Insert(user_id,nm);

                upDate(room);
            }
        });



        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Alert(user_id,nm);
            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name,phone,email,country,namerrom,typeroom,namebed,typebed,numgest,access,leaving,typetrip,priceroom;

        Button yes,no;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            yes=itemView.findViewById(R.id.but_yes);
            no=itemView.findViewById(R.id.but_no);

            name=itemView.findViewById(R.id.name_req);
            phone=itemView.findViewById(R.id.phone_req);
            email=itemView.findViewById(R.id.email_req);
            country=itemView.findViewById(R.id.country_req);
            namerrom=itemView.findViewById(R.id.nameroom_req);
            typeroom=itemView.findViewById(R.id.typeroom_req);
            namebed=itemView.findViewById(R.id.namebed_req);
            typebed=itemView.findViewById(R.id.typebed_req);
            numgest=itemView.findViewById(R.id.numguest_req);
            access=itemView.findViewById(R.id.access_req);
            leaving=itemView.findViewById(R.id.leaving_req);
            typetrip=itemView.findViewById(R.id.typetrip_req);
            priceroom=itemView.findViewById(R.id.priceroom_req);
        }
    }




    private void upDate(String id) {

        final String token = SaveToken.getInstanse(context).getToken().getToken();

        JSONObject js = new JSONObject();
        try {


            js.put("enable","no");


        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, LinkServer.UP_ENA+id, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


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
        VolleySetting.getInstance(context).addRequest(jsonObjectRequest);


    }








    private void show(String idi){


        final String token= SaveToken.getInstanse(context).getToken().getToken();



        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, LinkServer.HOTEL_IMAGE_URL+"/"+idi,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if (response.getBoolean("success")) {
                        JSONObject hotel = response.getJSONObject("data");



                      String s=hotel.getString("enable");

                      Toast.makeText(context,s+"enable out",Toast.LENGTH_SHORT).show();
                      if (!s.equals("yes")){
                          Toast.makeText(context,s+"enable inside",Toast.LENGTH_SHORT).show();

                          upDate(idi);

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


        })
        {
            public Map<String,String> getHeaders(){
                Map<String, String> map=new HashMap<>();
                map.put("Accept","application/json");
                map.put("Authorization","Bearer "+ token);
                return map;
            }

        };
        VolleySetting.getInstance(context).addRequest(jsonObjectRequest);



    }






    private void Insert(String id,String nm) {

        final String token = SaveToken.getInstanse(context).getToken().getToken();

        JSONObject js = new JSONObject();
        try {


            js.put("namehotel",nm);
            js.put("content","تم حجز طلبك");
            js.put("user_id",id);
            js.put("noty","no");







        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.INSERT_NOTI, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


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
        VolleySetting.getInstance(context).addRequest(jsonObjectRequest);


    }



    private void Alert(String id,String nm){

        AlertDialog.Builder builder=new AlertDialog.Builder(context);

        EditText editText=new EditText(context);
        builder.setTitle("لماذا رفضته ادخل السبب لكي يعرف المستخدم");

        builder.setView(editText);

        builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String e=editText.getText().toString().trim();

                InsertNo(id,e,nm);



            }
        });

        builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.show();

    }










    private void InsertNo(String id,String content,String nm) {

        final String token = SaveToken.getInstanse(context).getToken().getToken();


        if (TextUtils.isEmpty(content)){

            Toast.makeText(context,"الرجاء ادخال السبب",Toast.LENGTH_SHORT).show();

            return;
        }


        JSONObject js = new JSONObject();
        try {


            js.put("namehotel",nm);
            js.put("content",content);
            js.put("user_id",id);
            js.put("noty","no");







        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.INSERT_NOTI, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


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
        VolleySetting.getInstance(context).addRequest(jsonObjectRequest);


    }























    private void dataMessage(String user_id,Button b,Button bb) {

        final String token = SaveTokenUser.getInstanse(context).getToken().getToken();






        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_ID, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {

                    String s=response.getString("message");

                    Toast.makeText(context,s+"/s/",Toast.LENGTH_SHORT).show();


                    if (s==user_id){


                        b.setVisibility(View.GONE);
                        bb.setVisibility(View.GONE);
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
        VolleySetting.getInstance(context).addRequest(jsonObjectRequest);


    }




}
