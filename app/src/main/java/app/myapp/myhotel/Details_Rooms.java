package app.myapp.myhotel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_Home;
import app.myapp.myhotel.Model.InfoHotel;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.ServerApi.MultipartRequest;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class Details_Rooms extends AppCompatActivity {

    ScrollView layout1,layout2;
    ImageView imageView1,imageView2,imageView3;

    TextView nameroom,typeroom,priceroom,namebed,typebed,numguest,facities,comfort,kids,animal,access,breckfast,numroom;

    ImageView imageView_edit1,imageView_edit2,imageView_edit3;
    EditText nameroom_edit,typeroom_edit,priceroom_edit,namebed_edit,typebed_edit,numguest_edit,facities_edit,comfort_edit,kids_edit,animal_edit,access_edit,breckfast_edit,numroom_edit;

    Button b;
    String im1, im2, im3,i1,i2,i3;

    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__rooms);

        layout1=findViewById(R.id.id_layout1);
        layout2=findViewById(R.id.layout2);


        b=findViewById(R.id.butt_edit);
        imageView1=findViewById(R.id.image_detail1);
        imageView2=findViewById(R.id.image_detail2);
        imageView3=findViewById(R.id.image_detail3);

        nameroom_edit=findViewById(R.id.detail_edit_name_room);
        typeroom_edit=findViewById(R.id.detali_edit_type_room);
        priceroom_edit=findViewById(R.id.tetail_edit_price_room);
        namebed_edit=findViewById(R.id.detail_edit_name_bed);
        typebed_edit=findViewById(R.id.detali_edit_type_bed);
        numguest_edit=findViewById(R.id.detail_edit_numguest);
        facities_edit=findViewById(R.id.detail_edit_facilites);
        comfort_edit=findViewById(R.id.detail_edit_comfort);
        kids_edit=findViewById(R.id.detail_edit_kids);
        animal_edit=findViewById(R.id.detail_edit_animal);
        access_edit=findViewById(R.id.detail_edit_access);
        breckfast_edit=findViewById(R.id.detail_edit_breckfast);
        numroom_edit=findViewById(R.id.detail_edit_numroom);




        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getBaseContext(),ViewPhoto.class);
                intent.putExtra("1",1);
                intent.putExtra("i1",i1);
                intent.putExtra("i2",i2);
                intent.putExtra("i3",i3);
                startActivity(intent);


            }
        });


        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),ViewPhoto.class);
                intent.putExtra("1",2);
                intent.putExtra("i1",i1);
                intent.putExtra("i2",i2);
                intent.putExtra("i3",i3);
                startActivity(intent);
            }
        });


        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),ViewPhoto.class);
                intent.putExtra("1",3);
                intent.putExtra("i1",i1);
                intent.putExtra("i2",i2);
                intent.putExtra("i3",i3);
                startActivity(intent);
            }
        });




















        imageView_edit1=findViewById(R.id.image_edit_detail1);
        imageView_edit2=findViewById(R.id.image_edit_detail2);
        imageView_edit3=findViewById(R.id.image_edit_detail3);

        nameroom=findViewById(R.id.detail_name_room);
        typeroom=findViewById(R.id.detali_type_room);
        priceroom=findViewById(R.id.tetail_price_room);
        namebed=findViewById(R.id.detail_name_bed);
        typebed=findViewById(R.id.detali_type_bed);
        numguest=findViewById(R.id.detail_numguest);
        facities=findViewById(R.id.detail_facilites);
        comfort=findViewById(R.id.detail_comfort);
        kids=findViewById(R.id.detail_kids);
        animal=findViewById(R.id.detail_animal);
        access=findViewById(R.id.detail_access);
        breckfast=findViewById(R.id.detail_breckfast);
        numroom=findViewById(R.id.detail_numroom);







        imageView_edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                } else {

                    ActivityCompat.requestPermissions(Details_Rooms.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);


                }
            }
        });


        imageView_edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else {

                    ActivityCompat.requestPermissions(Details_Rooms.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);


                }
            }
        });


        imageView_edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 3);
                } else {

                    ActivityCompat.requestPermissions(Details_Rooms.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);


                }
            }
        });





























        Intent intent=getIntent();
        i=intent.getIntExtra("id", -1);


        Toast.makeText(this,i+"",Toast.LENGTH_SHORT).show();
       show(i);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {

                Uri uri = data.getData();
                im1 = uri.toString().trim();
                Picasso.get().load(uri.toString()).resize(200, 200).into(imageView_edit1);

            }
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            if (data != null) {

                Uri uri2 = data.getData();
                im2 = uri2.toString().trim();
                Picasso.get().load(uri2.toString()).resize(200, 200).into(imageView_edit2);

            }
        }

        if (requestCode == 3 && resultCode == RESULT_OK) {
            if (data != null) {

                Uri uri3 = data.getData();
                im3 = uri3.toString().trim();
                Picasso.get().load(uri3.toString()).resize(200, 200).into(imageView_edit3);

            }
        }
    }












































    private void show(final int id){


        final String token= SaveToken.getInstanse(getBaseContext()).getToken().getToken();



        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, LinkServer.HOTEL_IMAGE_URL+"/"+id,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if (response.getBoolean("success")) {
                        JSONObject hotel = response.getJSONObject("data");




                        Picasso.get().load(hotel.getString("imageroom1")).resize(200,300).into(imageView1);
                        Picasso.get().load(hotel.getString("imageroom2")).resize(200,300).into(imageView2);
                        Picasso.get().load(hotel.getString("imageroom3")).resize(200,300).into(imageView3);


                        i1=hotel.getString("imageroom1");
                        i2=hotel.getString("imageroom2");
                        i3=hotel.getString("imageroom3");


                        nameroom.setText(hotel.getString("nameroom"));

                        Toast.makeText(getBaseContext(),"fdfd"+hotel.getString("nameroom"),Toast.LENGTH_SHORT).show();
                        typeroom.setText(hotel.getString("typeroom"));
                        namebed.setText(hotel.getString("numbed"));
                        typebed.setText(hotel.getString("typebed"));
                        priceroom.setText(hotel.getString("priceroom"));
                        facities.setText(hotel.getString("Facilities"));
                        numguest.setText(hotel.getString("numguest"));
                        kids.setText(hotel.getString("kids"));
                        animal.setText(hotel.getString("animals"));
                        breckfast.setText(hotel.getString("breckfast"));
                        numroom.setText(hotel.getString("numroom"));
                        access.setText(hotel.getString("access"));
                        comfort.setText(hotel.getString("meansofcomfort"));





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
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);



    }

    public void edit(View view) {

        String sv=((Button)view).getText().toString().trim();
        if (sv.equals("تعديل")) {
            layout2.setVisibility(View.VISIBLE);
            layout1.setVisibility(View.GONE);
            showw(i);
            b.setText("تم");
        }else {

            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            mulityy();
            convers();
            b.setText("تعديل");
        }
    }







    private void mulityy() {


        String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();
        Map<String, String> map1 = new HashMap<>();
        map1.put("Accept", "application/json");
        map1.put("Authorization", "Bearer " + token);


        MultipartRequest request = new MultipartRequest(LinkServer.UPDATE_IMAGE_URL, map1,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });


        request.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        request.addPart(new MultipartRequest.FormPart("priceroom", priceroom_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("nameroom", nameroom_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("typeroom", typeroom_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("numbed", namebed_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("typebed", typebed_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("numroom", numroom_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("kids", kids_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("animals", animal_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("breckfast", breckfast_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("Facilities", facities_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("meansofcomfort", comfort_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("numguest", numguest_edit.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("access", access_edit.getText().toString().trim()));

        request.addPart(new MultipartRequest.FormPart("id", String.valueOf(i)));


        if (im1 != null && !im1.isEmpty()) {
            try {


                Bitmap bitmapp;
                bitmapp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(im1));


                Bitmap bitmap = Bitmap.createScaledBitmap(bitmapp, 200, 200, false);


                request.addPart(new MultipartRequest.FilePart("imageroom1", "*/*", im1, getFileDataFromDrawable(bitmap)));

            } catch (Exception e) {

            }
        }

        if (im2 != null && !im2.isEmpty()) {

            try {
                Bitmap bitmapp1;
                bitmapp1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(im2));


                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmapp1, 200, 200, false);


                request.addPart(new MultipartRequest.FilePart("imageroom2", "*/*", im2, getFileDataFromDrawable(bitmap1)));

            } catch (Exception e) {

            }
        }


        if (im3 != null && !im3.isEmpty()) {

            try{
            Bitmap bitmapp2;
            bitmapp2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(im3));


            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmapp2, 200, 200, false);


            request.addPart(new MultipartRequest.FilePart("imageroom3", "*/*", im3, getFileDataFromDrawable(bitmap2)));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
        

        VolleySetting.getInstance(getBaseContext()).addRequest(request);
    }


    public byte[] getFileDataFromDrawable(Bitmap bitmap1) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();

    }
















private void convers(){

    if (im1!=null && !im1.isEmpty()){
        Picasso.get().load(im1).resize(200,300).into(imageView1);
    }

    if (im2!=null && !im2.isEmpty()) {
        Picasso.get().load(im2).resize(200, 300).into(imageView2);
    }
    if (im3!=null && !im3.isEmpty()) {
        Picasso.get().load(im3).resize(200, 300).into(imageView3);
    }
    nameroom.setText(nameroom_edit.getText().toString().trim());
    typeroom.setText(typeroom_edit.getText().toString().trim());
    namebed.setText(namebed_edit.getText().toString().trim());
    typebed.setText(typebed_edit.getText().toString().trim());
    priceroom.setText(priceroom_edit.getText().toString().trim());
    facities.setText(facities_edit.getText().toString().trim());
    numguest.setText(numguest_edit.getText().toString().trim());
    kids.setText(kids_edit.getText().toString().trim());
    animal.setText(animal_edit.getText().toString().trim());
    breckfast.setText(breckfast_edit.getText().toString().trim());
    numroom.setText(numroom_edit.getText().toString().trim());
    access.setText(access_edit.getText().toString().trim());
    comfort.setText(comfort_edit.getText().toString().trim());



}


    private void showw(final int id){


        final String token= SaveToken.getInstanse(getBaseContext()).getToken().getToken();



        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, LinkServer.HOTEL_IMAGE_URL+"/"+id,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if (response.getBoolean("success")) {
                        JSONObject hotel = response.getJSONObject("data");




                        Picasso.get().load(hotel.getString("imageroom1")).resize(200,300).into(imageView_edit1);
                        Picasso.get().load(hotel.getString("imageroom2")).resize(200,300).into(imageView_edit2);
                        Picasso.get().load(hotel.getString("imageroom3")).resize(200,300).into(imageView_edit3);
                        nameroom_edit.setText(hotel.getString("nameroom"));
                        typeroom_edit.setText(hotel.getString("typeroom"));
                        namebed_edit.setText(hotel.getString("numbed"));
                        typebed_edit.setText(hotel.getString("typebed"));
                        priceroom_edit.setText(hotel.getString("priceroom"));
                        facities_edit.setText(hotel.getString("Facilities"));
                        numguest_edit.setText(hotel.getString("numguest"));
                        kids_edit.setText(hotel.getString("kids"));
                        Toast.makeText(getBaseContext(),hotel.getString("kids"),Toast.LENGTH_SHORT).show();

                        animal_edit.setText(hotel.getString("animals"));
                        breckfast_edit.setText(hotel.getString("breckfast"));
                        numroom_edit.setText(hotel.getString("numroom"));
                        access_edit.setText(hotel.getString("access"));
                        comfort_edit.setText(hotel.getString("meansofcomfort"));





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
        VolleySetting.getInstance(getBaseContext()).addRequest(jsonObjectRequest);



    }
}