package app.myapp.myhotel.User;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.MainActivity;
import app.myapp.myhotel.Model.User;
import app.myapp.myhotel.Model.User_u;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class LoginActivityUser extends AppCompatActivity {

    LoginButton signInButton;
    CallbackManager callbackManager;
    EditText email, password;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        email = findViewById(R.id.edit_email_login_user);
        password = findViewById(R.id.edit_password_login_user);



      //  imageView=findViewById(R.id.image_facebook);
    signInButton = findViewById(R.id.login_button);


    callbackManager=CallbackManager.Factory.create();

        signInButton.setReadPermissions(Arrays.asList("email","gaming_profile","gaming_user_picture"));




        signInButton.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){


        signInButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {




                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,GraphResponse response) {


                                try {

                                    JSONObject data = response.getJSONObject();
                                    if (data.has("picture")) {
                                        String profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");

                                        Picasso.get().load(profilePicUrl).into(imageView);
                                    }





                                    Toast.makeText(getApplicationContext(), object.getString("name")+"/"+object.getString("email"), Toast.LENGTH_LONG).show();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email,picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();


             //   Profile(loginResult.getAccessToken());


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }
    });


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


        StringRequest stringRequest=new StringRequest(Request.Method.POST, LinkServer.LOGIN_URL_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("success")) {

                                JSONObject object = jsonObject.getJSONObject("data");
                                SaveTokenUser.getInstanse(getBaseContext()).saveUser(new User_u(object.getString("token")));
                                Toast.makeText(getBaseContext(),object.getString("token"),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getBaseContext(),MainActivityUser.class));
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










    private void Profile(final AccessToken accessToken){

        Bundle bundle=new Bundle();
        bundle.putString("fields","name");

        GraphRequest graphRequest=new GraphRequest(accessToken,"me",bundle,null, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {

                GraphRequest.GraphJSONObjectCallback callbackEmail = new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject me, GraphResponse response) {


                        try {

                            final JSONObject json = response.getJSONObject();





                            Toast.makeText(getBaseContext(),accessToken.getUserId()+"/"+me.getString("email"),Toast.LENGTH_SHORT).show();


                            email.setText(me.getString("name")+"/"+me.getString("email"));
                            password.setText(accessToken.getUserId()+"/"+json.getString("email"));

                        } catch (Exception e) {

                        }

                    }
                };
                callbackEmail.onCompleted(response.getJSONObject(), response);
            }

        });


        graphRequest.executeAsync();















    }












    public void Registerr(View view) {

        startActivity(new Intent(getBaseContext(),RegisterActivityUser.class));
        finish();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}