package app.myapp.myhotel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.ServerApi.MultipartRequest;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class AddHotel extends AppCompatActivity {

    EditText namehotel,country,city,manger,phone,email,desc;
    ImageView imageView1,imageView2,imageView3;
    RatingBar ratingBar;
    String im1,im2,im3;
    double l,o;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

        namehotel = findViewById(R.id.text_name_hotel);
        country=findViewById(R.id.hotel_country);
        city=findViewById(R.id.hotel_city);
        manger=findViewById(R.id.hotel_manger);
        phone=findViewById(R.id.hotel_phone);
        email=findViewById(R.id.email_hotel);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        ratingBar = findViewById(R.id.ratingBar);
        t=findViewById(R.id.text_loc);




        imageView1.setImageResource(R.drawable.ic_photo24);
        imageView2.setImageResource(R.drawable.ic_photo24);
        imageView3.setImageResource(R.drawable.ic_photo24);

















        getData();





        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                } else {

                    ActivityCompat.requestPermissions(AddHotel.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 4);


                }

            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else {

                    ActivityCompat.requestPermissions(AddHotel.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 4);


                }
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 3);
                } else {

                    ActivityCompat.requestPermissions(AddHotel.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 4);


                }
            }
        });

    }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 1 && resultCode == RESULT_OK) {
                if (data != null) {

                  Uri  uri = data.getData();
                  im1=uri.toString().trim();
                    Picasso.get().load(uri.toString()).resize(200, 200).into(imageView1);

                }
            }
            if (requestCode == 2 && resultCode == RESULT_OK) {
                if (data != null) {

                    Uri  uri2 = data.getData();
                    im2=uri2.toString().trim();
                    Picasso.get().load(uri2.toString()).resize(200, 200).into(imageView2);

                }
            }

            if (requestCode == 3 && resultCode == RESULT_OK) {
                if (data != null) {

                    Uri  uri3 = data.getData();
                    im3=uri3.toString().trim();
                    Picasso.get().load(uri3.toString()).resize(200, 200).into(imageView3);

                }
            }





    }

    public void next(View view) {


        String nh=namehotel.getText().toString().trim();
        String co=country.getText().toString().trim();
        String ci=city.getText().toString().trim();
        String ma=manger.getText().toString().trim();
        String ph=phone.getText().toString().trim();
        String em=email.getText().toString().trim();
        String rt= String.valueOf(ratingBar.getRating()).trim();




        if (TextUtils.isEmpty(im1)){
            Toast.makeText(getBaseContext(),"اضف الصورة1",Toast.LENGTH_SHORT).show();


            return;
        }

        if (TextUtils.isEmpty(im2)){
            Toast.makeText(getBaseContext(),"اضف الصورة2",Toast.LENGTH_SHORT).show();
            return;
        }





        if (TextUtils.isEmpty(im3)){
            Toast.makeText(getBaseContext(),"اضف الصورة3",Toast.LENGTH_SHORT).show();
            return;
        }






        if (TextUtils.isEmpty(nh)){
            namehotel.setError("ادخل اسم الفندق");
            namehotel.requestFocus();
            return;
        }






        if (TextUtils.isEmpty(co)){
            country.setError("ادخل اسم الدولة");
            country.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(ci)){
            city.setError("ادخل اسم المدينة");
            city.requestFocus();
            return;
        }




        if (TextUtils.isEmpty(ma)){
            manger.setError("ادخل اسم صاحب الفندق");
            manger.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(ph)){
            phone.setError("ادخل رقم الهاتف");
            phone.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(em)){
            email.setError("ادخل الايميل");
            email.requestFocus();
            return;
        }

        if (ratingBar.getRating()==0.0){
          Toast.makeText(getBaseContext(),"ادخل درجة الفندق",Toast.LENGTH_SHORT).show();
            return;
        }




        if (TextUtils.isEmpty(t.getText().toString().trim())){
            Toast.makeText(getBaseContext(),"ادخل الموقع",Toast.LENGTH_SHORT).show();
            return;
        }


        Toast.makeText(getBaseContext(),"complate",Toast.LENGTH_SHORT).show();



        mulityy();

    }







    private void getData(){


        Intent intent=getIntent();

        namehotel.setText(intent.getStringExtra("namehotel"));



       if (intent.getStringExtra("rating")!=null) {
           ratingBar.setRating(Float.parseFloat(intent.getStringExtra("rating")));
       }
       if (intent.getStringExtra("image1")!=null) {
           imageView1.setImageURI(Uri.parse(intent.getStringExtra("image1")));
           im1=intent.getStringExtra("image1");
           im2=intent.getStringExtra("image2");
           im3=intent.getStringExtra("image3");
           imageView2.setImageURI(Uri.parse(intent.getStringExtra("image2")));
           imageView3.setImageURI(Uri.parse(intent.getStringExtra("image3")));
       }

        country.setText(intent.getStringExtra("country"));
        city.setText(intent.getStringExtra("city"));
        manger.setText(intent.getStringExtra("manger"));
        phone.setText(intent.getStringExtra("phone"));
        email.setText(intent.getStringExtra("email"));

        l=intent.getDoubleExtra("l",0);
        o=intent.getDoubleExtra("o",0);



        if (l!=0 && o!=0){
            t.setText("تم جلب الموقع");
        }

        Toast.makeText(getApplicationContext(),l+"/"+o,Toast.LENGTH_SHORT).show();



    }























    private void mulityy() {


        String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();
        Map<String, String> map1 = new HashMap<>();
        map1.put("Accept", "application/json");
        map1.put("Authorization", "Bearer " + token);


        MultipartRequest request = new MultipartRequest(LinkServer.GET_HOTEL_URL, map1,
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



        String nh=namehotel.getText().toString().trim();
        String co=country.getText().toString().trim();
        String ci=city.getText().toString().trim();
        String ma=manger.getText().toString().trim();
        String ph=phone.getText().toString().trim();
        String rt= String.valueOf(ratingBar.getRating());
        String em=email.getText().toString().trim();

        request.addPart(new MultipartRequest.FormPart("namehotel",nh));
        request.addPart(new MultipartRequest.FormPart("evaluation",rt));
        request.addPart(new MultipartRequest.FormPart("manger",ma));
        request.addPart(new MultipartRequest.FormPart("number",ph));
        request.addPart(new MultipartRequest.FormPart("country",co));
        request.addPart(new MultipartRequest.FormPart("city",ci));
        request.addPart(new MultipartRequest.FormPart("email",em));
        request.addPart(new MultipartRequest.FormPart("enable","no"));
        request.addPart(new MultipartRequest.FormPart("latitude",String.valueOf(l)));
        request.addPart(new MultipartRequest.FormPart("longtude",String.valueOf(o)));


        request.addPart(new MultipartRequest.FormPart("name1","1"));
        request.addPart(new MultipartRequest.FormPart("name2","2"));
        request.addPart(new MultipartRequest.FormPart("name3","3"));





        try {


            Bitmap bitmapp;
            bitmapp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(im1));
            Bitmap bitmap = Bitmap.createScaledBitmap(bitmapp, 200, 200, false);
            request.addPart(new MultipartRequest.FilePart("image1", "*/*",im1, getFileDataFromDrawable(bitmap)));





            Bitmap bitmapp1;
            bitmapp1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(im2));
            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmapp1, 200, 200, false);
            request.addPart(new MultipartRequest.FilePart("image2", "*/*",im2, getFileDataFromDrawable(bitmap1)));





            Bitmap bitmapp2;
            bitmapp2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(im3));
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmapp2, 200, 200, false);
            request.addPart(new MultipartRequest.FilePart("image3", "*/*",im3, getFileDataFromDrawable(bitmap2)));


        } catch (IOException e) {
            e.printStackTrace();
        }



        Intent intent = new Intent(getBaseContext(), MainActivity.class);

        startActivity(intent);

        finish();
        VolleySetting.getInstance(getBaseContext()).addRequest(request);
    }


    public byte[] getFileDataFromDrawable(Bitmap bitmap1) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();

    }



    public void location(View view) {


        String nh=namehotel.getText().toString().trim();
        String co=country.getText().toString().trim();
        String ci=city.getText().toString().trim();
        String ma=manger.getText().toString().trim();
        String ph=phone.getText().toString().trim();
        String rt= String.valueOf(ratingBar.getRating());
        String em=email.getText().toString().trim();



        Intent intent = new Intent(getBaseContext(),MapsActivity.class);
        intent.putExtra("namehotel",nh);
        intent.putExtra("image1",im1);
        intent.putExtra("image2",im2);
        intent.putExtra("image3",im3);
        intent.putExtra("country",co);
        intent.putExtra("city", ci);
        intent.putExtra("manger", ma);
        intent.putExtra("phone",ph);
        intent.putExtra("rating",rt);
        intent.putExtra("email",em);
        startActivity(intent);
        finish();

    }
}