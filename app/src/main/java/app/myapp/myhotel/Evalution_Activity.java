package app.myapp.myhotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_Comment;
import app.myapp.myhotel.AdapterRecycler.Adapter_Home_User;
import app.myapp.myhotel.AdapterRecycler.Adapter_RoomsUser;
import app.myapp.myhotel.Model.InfoHotelUser;
import app.myapp.myhotel.Model.ItemComment;
import app.myapp.myhotel.Model.Item_Rooms_user;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;
import app.myapp.myhotel.User.ViewRoomUser;

public class Evalution_Activity extends AppCompatActivity implements View.OnClickListener,PopupMenu.OnMenuItemClickListener {

    EditText editText;
    LinearLayout layout,mylayout;
    RatingBar ratingBar,myrating;
    int id;
    TextView myname,mycomment;
    RecyclerView recyclerView;
    Adapter_Comment adapter_comment;
    Context context;
    int i;
    TextView t,desc;
    ImageView imageView;
    LinearLayout layoutot;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evalution_);

        editText=findViewById(R.id.edit_ev);
        layout=findViewById(R.id.layout_ev);
        ratingBar=findViewById(R.id.ratingBar_ev);
        desc=findViewById(R.id.text_desc);
        imageView=findViewById(R.id.image_desc);


        layoutot=findViewById(R.id.lin_out);






        mylayout=findViewById(R.id.mylayout);
        recyclerView=findViewById(R.id.reccom);

        t=findViewById(R.id.textpop);
        t.setOnClickListener(this);

        myname=findViewById(R.id.text_myname);
        myrating=findViewById(R.id.my_ratingBar);
        mycomment=findViewById(R.id.my_com);





        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);

        String img=intent.getStringExtra("img");





        Picasso.get().load(img).resize(300,300).into(imageView);


        context=this;

        Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();

        dataMessageDesc(id);


        dataMessage();


        dataMessageComment(id);


    }












    private void dataMessage() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();


        ArrayList<Double> arrayList1=new ArrayList();
        ArrayList<Double> arrayList2=new ArrayList();
        ArrayList<Double> arrayList3=new ArrayList();
        ArrayList<Double> arrayList4=new ArrayList();
        ArrayList<Double> arrayList5=new ArrayList<>();



        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_COMMENT_TEST+id, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {

                    String s=response.getString("message");

                    if (s.equals("yes")){
                        Toast.makeText(getBaseContext(),"yes",Toast.LENGTH_SHORT).show();
                        layout.setVisibility(View.GONE);
                        mylayout.setVisibility(View.VISIBLE);


                        JSONObject jsonObject=response.getJSONObject("data");

                       i=jsonObject.getInt("id");
                       myname.setText(jsonObject.getString("name")+"(انت)"+"\t");
                        myrating.setRating(Float.parseFloat(jsonObject.getString("evaluation")));
                       mycomment.setText(jsonObject.getString("content"));




                        double d= Double.parseDouble(jsonObject.getString("evaluation"));


                        if (d>=0.5 && d<=1.0){


                            arrayList1.add(d);

                        }else if (d>=1.5 && d<=2.0){


                            arrayList2.add(d);
                        }else if (d>=2.5 && d<=3.0){



                            arrayList3.add(d);
                        }else if (d>=3.5 && d<=4.0){
                            arrayList4.add(d);



                        }else if (d>=4.5 && d<=5.0){
                            arrayList5.add(d);

                        }





                        double i=arrayList1.size()*1;
                        double ii=arrayList2.size()*2;
                        double iii=arrayList3.size()*3;
                        double iiii=arrayList4.size()*4;
                        double iiiii=arrayList5.size()*5;

                        double i1=i+ii+iii+iiii+iiiii;
                        double i2=arrayList1.size()+arrayList2.size()+arrayList3.size()+arrayList4.size()+arrayList5.size();

                        double i3=i1/i2;


                        String v= String.valueOf(i3);

                        Toast.makeText(getBaseContext(),v+"vvvvv",Toast.LENGTH_SHORT).show();
                        if (v!=null) {
                            upDate(v);
                        }
                    }else {

                        Toast.makeText(getBaseContext(),"no",Toast.LENGTH_SHORT).show();
                        layout.setVisibility(View.VISIBLE);
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









    private void upDate(String ev) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        JSONObject js = new JSONObject();
        try {

            js.put("ev",ev);
            js.put("id",id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.UPDATE_EVE, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {

                    Toast.makeText(getBaseContext(),"update",Toast.LENGTH_SHORT).show();
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









    private void dataMessageComment(int idi) {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();

        ArrayList<ItemComment>arrayList=new ArrayList<>();

        ArrayList<Double> arrayList1=new ArrayList();
        ArrayList<Double> arrayList2=new ArrayList();
        ArrayList<Double> arrayList3=new ArrayList();
        ArrayList<Double> arrayList4=new ArrayList();
        ArrayList<Double> arrayList5=new ArrayList<>();

        Toast.makeText(getBaseContext(),"what?",Toast.LENGTH_SHORT).show();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_COMMENT+idi, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        ItemComment itemComment=new ItemComment();

                        itemComment.setId(screen.getInt("id"));

                        itemComment.setName(screen.getString("name"));

                        Toast.makeText(getBaseContext(),screen.getString("name"),Toast.LENGTH_SHORT).show();
                        itemComment.setContent(screen.getString("content"));

                        itemComment.setRating(screen.getString("evaluation"));

                        double d= Double.parseDouble(screen.getString("evaluation"));


                        if (d>=0.5 && d<=1.0){
                            Toast.makeText(getBaseContext(),d+"",Toast.LENGTH_SHORT).show();

                            arrayList1.add(d);

                        }else if (d>=1.5 && d<=2.0){
                            Toast.makeText(getBaseContext(),d+"",Toast.LENGTH_SHORT).show();

                            arrayList2.add(d);
                        }else if (d>=2.5 && d<=3.0){

                            Toast.makeText(getBaseContext(),d+"",Toast.LENGTH_SHORT).show();

                            arrayList3.add(d);
                        }else if (d>=3.5 && d<=4.0){
                            arrayList4.add(d);
                        Toast.makeText(getBaseContext(),d+"",Toast.LENGTH_SHORT).show();


                    }else if (d>=4.5 && d<=5.0){
                            arrayList5.add(d);
                        Toast.makeText(getBaseContext(),d+"",Toast.LENGTH_SHORT).show();
                    }


                        arrayList.add(itemComment);
                    }



                    Toast.makeText(getBaseContext(),arrayList2.size()+"double",Toast.LENGTH_SHORT).show();

                  double i=arrayList1.size()*1;
                    double ii=arrayList2.size()*2;
                    double iii=arrayList3.size()*3;
                    double iiii=arrayList4.size()*4;
                    double iiiii=arrayList5.size()*5;

                    double i1=i+ii+iii+iiii+iiiii;
                    double i2=arrayList1.size()+arrayList2.size()+arrayList3.size()+arrayList4.size()+arrayList5.size();

                    double i3=i1/i2;


                   String v= String.valueOf(i3);

                    Toast.makeText(getBaseContext(),v+"df",Toast.LENGTH_SHORT).show();

                    if (v!=null) {
                        upDate(v);
                    }






                     adapter_comment=new Adapter_Comment(arrayList,context);
                    recyclerView.setAdapter(adapter_comment);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
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


    public void addcom(View view) {

        if (TextUtils.isEmpty(editText.getText().toString().trim())){

            editText.setError("اضف تعليق");
            editText.requestFocus();
            return;
        }

        String rt= String.valueOf(ratingBar.getRating());

        if (TextUtils.isEmpty(rt)){

            Toast.makeText(this,"اضف تقييم",Toast.LENGTH_SHORT).show();

            return;
        }



        save();

    }










    private void save() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();

        String name=SaveTokenUser.getInstanse(getApplicationContext()).getToken().getName();

        Toast.makeText(getBaseContext(),name+"lk",Toast.LENGTH_SHORT).show();

        JSONObject js = new JSONObject();
        String rt= String.valueOf(ratingBar.getRating());
        try {
            js.put("name",name);
            js.put("content",editText.getText().toString().trim());
            js.put("evaluation",rt);
            js.put("comment_id",id);
        } catch (Exception e) {
            e.printStackTrace();
        }



        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.INSERT_COMMENT, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {

                    finish();
                    startActivity(new Intent(getBaseContext(),ViewRoomUser.class));

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






    private void Delete(int idi) {

        final String token = SaveTokenUser.getInstanse(context).getToken().getToken();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, LinkServer.INSERT_COMMENT+"/"+idi,null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    layout.setVisibility(View.VISIBLE);
                    mylayout.setVisibility(View.GONE);


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



    @Override
    public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
        popupMenu.inflate(R.menu.menu_delete_ev);
        popupMenu.setOnMenuItemClickListener(Evalution_Activity.this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){

            case R.id.del_ev:
                Delete(i);
                break;
        }

        return false;
    }





















    private void dataMessageDesc(int idi) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_DESC+idi, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject jsonArray = response.getJSONObject("data");



                   String n=jsonArray.getString("content");



                   desc.setText(n);


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