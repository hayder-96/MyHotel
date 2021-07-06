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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.ServerApi.MultipartRequest;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class AddNextHotel extends AppCompatActivity {



    String nr, tr, nb, tb;
    Spinner spinner_nameroom, spinner_typeroom, spinner_name_bad, spinner_type_bed;
    ImageView imageroom1, imageroom2, imageroom3;
    String im1, im2, im3;
    String s, k, a, b;
    String c1="",c2="",c3="",c4="",c5="",c6="",h1="",h2="",h3="",h4="",h5="",h6="",g;
    EditText editText,text_desc;

    int id;
    String im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_next_hotel);


        spinner_nameroom = findViewById(R.id.spinner_name_room);
        spinner_typeroom = findViewById(R.id.spinner_type_room);
        spinner_name_bad = findViewById(R.id.spinner_name_bad);
        spinner_type_bed = findViewById(R.id.spinner_type_bad);
        imageroom1 = findViewById(R.id.imageView_room1);
        imageroom2 = findViewById(R.id.imageView_room2);
        imageroom3 = findViewById(R.id.imageView_room3);
         editText=findViewById(R.id.price_room);



        imageroom1.setImageResource(R.drawable.ic_photo24);
        imageroom2.setImageResource(R.drawable.ic_photo24);
        imageroom3.setImageResource(R.drawable.ic_photo24);


        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);
        im=intent.getStringExtra("im");



        imageroom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                } else {

                    ActivityCompat.requestPermissions(AddNextHotel.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);


                }
            }
        });


        imageroom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else {

                    ActivityCompat.requestPermissions(AddNextHotel.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);


                }
            }
        });


        imageroom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 3);
                } else {

                    ActivityCompat.requestPermissions(AddNextHotel.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);


                }
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rooms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_nameroom.setAdapter(adapter);
        spinner_nameroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                nr = parent.getItemAtPosition(position).toString().trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.type_rooms, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_typeroom.setAdapter(adapter2);
        spinner_typeroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tr = parent.getItemAtPosition(position).toString().trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.name_bad, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_name_bad.setAdapter(adapter3);
        spinner_name_bad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                nb = parent.getItemAtPosition(position).toString().trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.type_bad, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type_bed.setAdapter(adapter4);
        spinner_type_bed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tb = parent.getItemAtPosition(position).toString().trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {

                Uri uri = data.getData();
                im1 = uri.toString().trim();
                Picasso.get().load(uri.toString()).resize(200, 200).into(imageroom1);

            }
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            if (data != null) {

                Uri uri2 = data.getData();
                im2 = uri2.toString().trim();
                Picasso.get().load(uri2.toString()).resize(200, 200).into(imageroom2);

            }
        }

        if (requestCode == 3 && resultCode == RESULT_OK) {
            if (data != null) {

                Uri uri3 = data.getData();
                im3 = uri3.toString().trim();
                Picasso.get().load(uri3.toString()).resize(200, 200).into(imageroom3);

            }
        }


    }













    private void save() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        if (TextUtils.isEmpty(text_desc.getText().toString().trim())){

            text_desc.setError("ادخل الاسم");
            text_desc.requestFocus();
            return;
        }




        JSONObject js = new JSONObject();
        try {
            js.put("userrating",im);
            js.put("content",text_desc.getText().toString().trim());
            js.put("desc_id",id);

        } catch (Exception e) {
            e.printStackTrace();
        }



        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.INSERT_DESC, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    finish();




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









    @Override
    public void onBackPressed() {




        super.onBackPressed();
    }


    public void radio_no_kids(View view) {

        k = ((RadioButton) view).getText().toString().trim();


    }

    public void radio_yes_kids(View view) {

        k = ((RadioButton) view).getText().toString().trim();

    }

    public void radio_no_animals(View view) {
        a = ((RadioButton) view).getText().toString().trim();
    }

    public void radio_yes_animals(View view) {
        a = ((RadioButton) view).getText().toString().trim();
    }

    public void radio_yes_breakfast(View view) {
        b = ((RadioButton) view).getText().toString().trim();
    }

    public void radio_no_breakfast(View view) {
        b = ((RadioButton) view).getText().toString().trim();
    }

    public void cheackBox1(View view) {

        c1=((CheckBox)view).getText().toString();

    }

    public void cheackBox2(View view) {
        c2=((CheckBox)view).getText().toString();

    }

    public void cheackBox3(View view) {
        c3=((CheckBox)view).getText().toString();

    }

    public void cheackBox4(View view) {
        c4=((CheckBox)view).getText().toString();

    }

    public void cheackBox5(View view) {
        c5=((CheckBox)view).getText().toString();

    }

    public void cheackBox6(View view) {
        c6=((CheckBox)view).getText().toString();

    }

    public void radio1(View view) {
        s = ((RadioButton) view).getText().toString().trim();

    }

    public void radio2(View view) {
        s = ((RadioButton) view).getText().toString().trim();

    }

    public void radio3(View view) {
        s = ((RadioButton) view).getText().toString().trim();

    }

    public void radio4(View view) {
        s = ((RadioButton) view).getText().toString().trim();

    }

    public void save(View view) {


        if (TextUtils.isEmpty(nr)) {
            Toast.makeText(getBaseContext(), "اضف اسم الغرفة", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(tr)) {
            Toast.makeText(getBaseContext(), "اضف نوع الغرفة", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(nb)) {
            Toast.makeText(getBaseContext(), "اضف اسم السرير", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(tb)) {
            Toast.makeText(getBaseContext(), "اضف نوع السرير", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(s)) {
            Toast.makeText(getBaseContext(), "اضف عدد الغرف", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(k)) {
            Toast.makeText(getBaseContext(), "هناك معلومة ناقصة", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(a)) {
            Toast.makeText(getBaseContext(), "هناك معلومة ناقصة", Toast.LENGTH_SHORT).show();

            return;
        }


        if (TextUtils.isEmpty(b)) {
            Toast.makeText(getBaseContext(), "هناك معلومة ناقصة", Toast.LENGTH_SHORT).show();

            return;
        }


        if (TextUtils.isEmpty(im1)) {
            Toast.makeText(getBaseContext(), "اضف الصورة1", Toast.LENGTH_SHORT).show();


            return;
        }

        if (TextUtils.isEmpty(im2)) {
            Toast.makeText(getBaseContext(), "اضف الصورة2", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(im3)) {
            Toast.makeText(getBaseContext(), "اضف الصورة3", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            editText.setError("ادخل سعر الغرفة");
            editText.requestFocus();
            Toast.makeText(getBaseContext(), "اضف الصورة3", Toast.LENGTH_SHORT).show();
            return;
        }






        mulityy();


    }


    private void mulityy() {


        String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();
        Map<String, String> map1 = new HashMap<>();
        map1.put("Accept", "application/json");
        map1.put("Authorization", "Bearer " + token);


        MultipartRequest request = new MultipartRequest(LinkServer.HOTEL_IMAGE_URL, map1,
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





        request.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        request.addPart(new MultipartRequest.FormPart("priceroom",editText.getText().toString().trim()));
        request.addPart(new MultipartRequest.FormPart("nameroom",nr));
        request.addPart(new MultipartRequest.FormPart("typeroom",tr));
        request.addPart(new MultipartRequest.FormPart("numbed",nb));
        request.addPart(new MultipartRequest.FormPart("typebed",tb));
        request.addPart(new MultipartRequest.FormPart("numroom",s));
        request.addPart(new MultipartRequest.FormPart("kids",k));
        request.addPart(new MultipartRequest.FormPart("animals",a));
        request.addPart(new MultipartRequest.FormPart("breckfast",b));
        String c=c1+" "+c2+" "+c3+" "+c4+" "+c5+" "+c6;
        request.addPart(new MultipartRequest.FormPart("Facilities",c));
        String h=h1+" "+h2+" "+h3+" "+h4+" "+h5+" "+h6;
        request.addPart(new MultipartRequest.FormPart("meansofcomfort",h));
        request.addPart(new MultipartRequest.FormPart("numguest",g));
        request.addPart(new MultipartRequest.FormPart("access","6:00 to 12:00"));

        request.addPart(new MultipartRequest.FormPart("details_id",String.valueOf(id)));


        try {

            Bitmap bitmapp;
            bitmapp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(im1));


            Bitmap bitmap = Bitmap.createScaledBitmap(bitmapp, 200, 200, false);


            request.addPart(new MultipartRequest.FilePart("imageroom1", "*/*",im1, getFileDataFromDrawable(bitmap)));





            Bitmap bitmapp1;
            bitmapp1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(im2));


            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmapp1, 200, 200, false);


            request.addPart(new MultipartRequest.FilePart("imageroom2", "*/*",im2, getFileDataFromDrawable(bitmap1)));





            Bitmap bitmapp2;
            bitmapp2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(im3));


            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmapp2, 200, 200, false);


            request.addPart(new MultipartRequest.FilePart("imageroom3", "*/*",im3, getFileDataFromDrawable(bitmap2)));

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

    public void cheackBox7(View view) {
        h1=((CheckBox)view).getText().toString();
    }

    public void cheackBox8(View view) {
         h2=((CheckBox)view).getText().toString();



    }

    public void cheackBox9(View view) {
        h3=((CheckBox)view).getText().toString();

    }

    public void cheackBox10(View view) {
        h4=((CheckBox)view).getText().toString();

    }

    public void cheackBox11(View view) {
        h5=((CheckBox)view).getText().toString();

    }

    public void cheackBox12(View view) {
        h6=((CheckBox)view).getText().toString();

    }

    public void radio_one_guest(View view) {
        g=((RadioButton)view).getText().toString();
    }

    public void radio_tow_guest(View view) {
        g=((RadioButton)view).getText().toString();

    }

    public void radio_three_guest(View view) {
        g=((RadioButton)view).getText().toString();

    }

    public void radio_four_guest(View view) {
        g=((RadioButton)view).getText().toString();

    }
}