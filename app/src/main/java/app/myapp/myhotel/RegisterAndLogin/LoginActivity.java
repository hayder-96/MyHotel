package app.myapp.myhotel.RegisterAndLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.MainActivity;
import app.myapp.myhotel.Model.User;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    LoginButton signInButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.edit_email_login);
        password=findViewById(R.id.edit_password_login);

//
//
//        signInButton = findViewById(R.id.login_button);
//
//
//        callbackManager=CallbackManager.Factory.create();
//
//        signInButton.setReadPermissions(Arrays.asList("email","gaming_profile"));
//




//
//
//
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//
//                signInButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//
//
//                        Profile(loginResult.getAccessToken());
//
//
//
//
//
//
//                    }
//
//                    @Override
//                    public void onCancel() {
//
//                    }
//
//                    @Override
//                    public void onError(FacebookException error) {
//
//                    }
//                });
//
//            }
//        });
//







    }



















    public void Login(View view) {
        Login();
    }







    private void Login(){

        final String email1=email.getText().toString().trim();
        final String password1=password.getText().toString().trim();


        if (TextUtils.isEmpty(email1)){
            email.setError("Enter your email");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password1)){
            password.setError("Enter your password");
            password.requestFocus();
            return;
        }


        StringRequest stringRequest=new StringRequest(Request.Method.POST, LinkServer.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("success")) {

                                JSONObject object = jsonObject.getJSONObject("data");
                                SaveToken.getInstanse(getBaseContext()).saveUser(new User(object.getString("token"),jsonObject.getString("message")));
                                startActivity(new Intent(getBaseContext(), MainActivity.class));
                                finish();

                            } else {
                                Toast.makeText(getBaseContext(), "message", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }

                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }


        }
        )

        {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> map=new HashMap<>();
                map.put("Content-Type","application/json");
                map.put("email",email1);
                map.put("password",password1);

                return map ;
            }
        };
        VolleySetting.getInstance(getBaseContext()).addRequest(stringRequest);

    }

    public void Registerr(View view) {

        startActivity(new Intent(getBaseContext(),RegisterActivity.class));
        finish();
    }








}