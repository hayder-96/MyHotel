package app.myapp.myhotel.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_Home;
import app.myapp.myhotel.Model.InfoHotel;
import app.myapp.myhotel.R;
import app.myapp.myhotel.RegisterAndLogin.LoginActivity;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class MainActivityUser extends AppCompatActivity {


    private RequestQueue requestQueue;
    private String token;
    Context context;
    RecyclerView recyclerView;
    private Date date;
    EditText co,ci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        requestQueue = Volley.newRequestQueue(this);
        if (SaveTokenUser.getInstanse(this).is_Login()) {

            if (SaveToken.getInstanse(this).getToken() != null) {
                token = SaveTokenUser.getInstanse(this).getToken().getToken();


            }
        } else {
            finish();
            Toast.makeText(getBaseContext(), "No save token", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, LoginActivityUser.class));
            return;
        }

        co=findViewById(R.id.edit_country);
        ci=findViewById(R.id.edit_city);

         context=this;
         recyclerView=findViewById(R.id.recc);
    }



    public void search(View view) {


        if (TextUtils.isEmpty(co.getText().toString().trim())){

            co.setError("ادخل البلد");
            co.requestFocus();

            return;
        }

        if (TextUtils.isEmpty(ci.getText().toString().trim())){

            ci.setError("ادخل المدينة");
            ci.requestFocus();
            return;
        }
        Intent intent=new Intent(this,Hotel_User.class);
        intent.putExtra("country",co.getText().toString().trim());
        intent.putExtra("city",ci.getText().toString().trim());
        startActivity(intent);


    }



}