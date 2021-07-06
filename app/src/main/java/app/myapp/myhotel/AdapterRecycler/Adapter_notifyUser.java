package app.myapp.myhotel.AdapterRecycler;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import net.time4j.Moment;
import net.time4j.format.expert.Iso8601Format;

import org.json.JSONObject;
import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import app.myapp.myhotel.Evalution_Activity;
import app.myapp.myhotel.Model.ItemNoty;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveTokenUser;
import app.myapp.myhotel.Shaerd.VolleySetting;
import app.myapp.myhotel.User.NotifyActivity;

public class Adapter_notifyUser extends RecyclerView.Adapter<Adapter_notifyUser.MyHolder> {

    ArrayList<ItemNoty> arrayList;
    Context context;
    ArrayList<ItemNoty> arrayList2;

    RecyclerView recyclerView;


    public Adapter_notifyUser(ArrayList<ItemNoty> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    public Adapter_notifyUser(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public Adapter_notifyUser.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.split_notify_user, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_notifyUser.MyHolder holder, int position) {

        ItemNoty itemNoty=arrayList.get(position);


       int id=itemNoty.getId();
        holder.name.setText(itemNoty.getName());
        holder.content.setText(itemNoty.getContent());
        holder.time.setText(formatdate(itemNoty.getTime()));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void deletePlanet(int pos) {

        ItemNoty p = arrayList.get(pos);
        arrayList.remove(pos);
        int id = p.getId();
        Delete(id);

    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name,content,time;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.text_name_noty);
            content=itemView.findViewById(R.id.text_content_noty);
            time=itemView.findViewById(R.id.textView_time);
        }








    }








    private void Delete(int id) {

        final String token = SaveTokenUser.getInstanse(context).getToken().getToken();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, LinkServer.DELETE_NOTY+"/"+id,null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {




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







    private String formatdate(String newDate){



        CharSequence  niceDateStr=null;

        Date date=null;

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy H:m:s");

            try {
                inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                 date=inputFormat.parse(newDate);
                 niceDateStr = DateUtils.getRelativeTimeSpanString(date.getTime() , Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            return niceDateStr.toString();

}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
//        long dateInMillis = 0;
//        Date date= null;
//        try {
//            date = format.parse(newDate);
//            dateInMillis=date.getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//      //  SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
//       // return sm.format(date);
//        return dateInMillis;
//
//    }
}
