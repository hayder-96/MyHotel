package app.myapp.myhotel.Shaerd;

import android.content.Context;
import android.content.SharedPreferences;

import app.myapp.myhotel.Model.User;
import app.myapp.myhotel.Model.User_u;

public class SaveTokenUser {






    private static final String SHARED_TOKEN="shared_token_user";
    private static final String TOKEN_SHARED="token_user";
    private static final String NAME_SHARED="name_shared";


    private static SaveTokenUser vollySing_instanse;
    private static Context context;

    public SaveTokenUser(Context context) {
        this.context=context;
    }

    public static synchronized SaveTokenUser getInstanse(Context context){
        if (vollySing_instanse==null){
            vollySing_instanse=new SaveTokenUser(context);
        }

        return vollySing_instanse;
    }

    public void saveUser(User_u user){

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_TOKEN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(TOKEN_SHARED,user.getToken());
        editor.putString(NAME_SHARED,user.getName());
        editor.apply();
    }




    public User_u getToken(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_TOKEN,Context.MODE_PRIVATE);

        return new User_u(sharedPreferences.getString(TOKEN_SHARED,null),sharedPreferences.getString(NAME_SHARED,null));

    }



    public boolean is_Login(){

        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_TOKEN,Context.MODE_PRIVATE);

        return sharedPreferences.getString(TOKEN_SHARED,null)!=null;
    }
    public void user_Logout(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_TOKEN,Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();


    }
}
