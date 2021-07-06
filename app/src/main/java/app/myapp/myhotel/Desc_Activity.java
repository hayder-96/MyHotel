package app.myapp.myhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class Desc_Activity extends AppCompatActivity {

    LinearLayout layout1,layout2;
    TextView text_desc;
    EditText edit_desc,getEdit_desc;
    Button button;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_);


        layout1=findViewById(R.id.layout_desc);
        layout2=findViewById(R.id.layout_text);
        text_desc=findViewById(R.id.textView_desc);
        edit_desc=findViewById(R.id.activity_desc);

        getEdit_desc=findViewById(R.id.edit_edit_desc);

        button=findViewById(R.id.button_desc);


        Intent intent=getIntent();
       id=intent.getIntExtra("id",-1);

       dataMessageDesc(id);

    }










    private void dataMessageDesc(int idi) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_DESC+idi, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {

                    String s=response.getString("message");

                    if (s.equals("yes")) {

                        JSONObject jsonArray = response.getJSONObject("data");


                        String uri = jsonArray.getString("userrating");
                        String n = jsonArray.getString("content");


                        text_desc.setText(n);
                        layout2.setVisibility(View.VISIBLE);
                    }else {
                        layout1.setVisibility(View.VISIBLE);
                        layout2.setVisibility(View.GONE);
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






    private void save() {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();


        if (TextUtils.isEmpty(edit_desc.getText().toString().trim())){

            edit_desc.setError("ادخل الاسم");
            edit_desc.requestFocus();
            return;
        }




        JSONObject js = new JSONObject();
        try {
            js.put("userrating","");
            js.put("content",edit_desc.getText().toString().trim());
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

    public void addDesc(View view) {

        save();
    }














    public void editDesc(View view) {

        String s=((Button)view).getText().toString().trim();

        if (s.equals("تعديل")) {
            getEdit_desc.setVisibility(View.VISIBLE);
            text_desc.setVisibility(View.GONE);
            button.setText("تم");
            getEdit_desc.setText(text_desc.getText().toString());
        }else {

            button.setText("تعديل");
            getEdit_desc.setVisibility(View.GONE);
            text_desc.setText(getEdit_desc.getText().toString());
            text_desc.setVisibility(View.VISIBLE);

            upDate(id,getEdit_desc.getText().toString().trim());

        }
    }












    private void upDate(int id,String ed) {

        final String token = SaveToken.getInstanse(getBaseContext()).getToken().getToken();

        JSONObject js = new JSONObject();
        try {

            js.put("content",ed);
            js.put("id",id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LinkServer.UPDATE_DESC, js, new Response.Listener<JSONObject>() {


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


}