package app.myapp.myhotel.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class AcceptUser_Activity extends AppCompatActivity {


    EditText name,phone,email,leaving,country;
    String r,nameroom,typeroom,priceroom,typebed,numroom,numguest,access;
    int i,ii,id;
    TextView com,de;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_user_);

        name=findViewById(R.id.editText_name);
        phone=findViewById(R.id.edit_phone);
        email=findViewById(R.id.editText_email);
        country=findViewById(R.id.editText_country);

        com=findViewById(R.id.but_comming);
        de=findViewById(R.id.but_departure);


        Intent intent=getIntent();


        ii=intent.getIntExtra("i",-1);
        id=intent.getIntExtra("id",-1);
        Toast.makeText(this,id+"id",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,ii+"i",Toast.LENGTH_SHORT).show();

        nameroom=intent.getStringExtra("nameroom");
        typeroom=intent.getStringExtra("typeroom");
        priceroom=intent.getStringExtra("priceroom");
        typebed=intent.getStringExtra("typebed");
        i=intent.getIntExtra("admin_id",-1);
        numroom=intent.getStringExtra("numroom");
        numguest=intent.getStringExtra("numguest");
        access=intent.getStringExtra("access");

        Toast.makeText(this,i+"admin_id",Toast.LENGTH_SHORT).show();



























    }

    public void radiojob(View view) {

        r=((RadioButton)view).getText().toString().trim();

    }

    public void radiotourism(View view) {
        r=((RadioButton)view).getText().toString().trim();
    }


















    private void save() {

        final String token = SaveTokenUser.getInstanse(getBaseContext()).getToken().getToken();



        if (TextUtils.isEmpty(name.getText().toString().trim())){

            name.setError("ادخل الاسم");
            name.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phone.getText().toString().trim())){

            phone.setError("ادخل الرقم");
            phone.requestFocus();
            return;
        }



        if (TextUtils.isEmpty(email.getText().toString().trim())){

            email.setError("ادخل الحساب");
            email.requestFocus();
            return;
        }






        if (TextUtils.isEmpty(country.getText().toString().trim())){

            country.setError("ادخل البلد");
            country.requestFocus();
            return;
        }





        if (TextUtils.isEmpty(com.getText().toString().trim())){

            com.setError("حدد تاريح المجيء");

            return;
        }




        if (TextUtils.isEmpty(de.getText().toString().trim())){

            de.setError("ادخل تاريخ المغادرة");

            return;
        }


        if (TextUtils.isEmpty(r)){

            Toast.makeText(getBaseContext(),"اختر نوع الرحلة",Toast.LENGTH_SHORT).show();


            return;
        }






        JSONObject js = new JSONObject();
        try {
            js.put("id",i);
            js.put("name",name.getText().toString().trim());
            js.put("phone",phone.getText().toString().trim());
            js.put("email",email.getText().toString().trim());
            js.put("country",country.getText().toString().trim());
            js.put("typetrip",r);
            js.put("typeroom",typeroom);
            js.put("nameroom",nameroom);
            js.put("priceroom",priceroom);
            js.put("access",access);
            String l=com.getText().toString().trim()+"/"+de.getText().toString().trim();
            js.put("leaving",l);
            js.put("typebed",typebed);
            js.put("numbed",numroom);
            js.put("numguest",numguest);
            js.put("admin_id",id);
            js.put("room_id",ii);
            js.put("noty","yes");
        } catch (Exception e) {
            e.printStackTrace();
        }



        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.INSERT_USER_HOTEL, js, new Response.Listener<JSONObject>() {


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


    public void insert(View view) {

        save();
    }

    public void history1(View view) {

        Calendar c = Calendar.getInstance();
        DatePickerDialog dialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String _year = String.valueOf(year);
                String _month = (month+1) < 10 ? "0" + (month+1) : String.valueOf(month+1);
                String _date = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                String _pickedDate = _year + "-" + _month + "-" + _date;

                com.setText(_pickedDate);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.MONTH));
        dialog1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog1.show();
    }

    public void history2(View view) {


        Calendar c = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String _year = String.valueOf(year);
                String _month = (month+1) < 10 ? "0" + (month+1) : String.valueOf(month+1);
                String _date = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                String _pickedDate = _year + "-" + _month + "-" + _date;

                de.setText(_pickedDate);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.MONTH));
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.show();
    }
}