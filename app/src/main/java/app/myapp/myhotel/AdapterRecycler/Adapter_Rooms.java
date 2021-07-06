package app.myapp.myhotel.AdapterRecycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.Details_Rooms;
import app.myapp.myhotel.Evalution_Activity;
import app.myapp.myhotel.Model.Item_Rooms;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.VolleySetting;
import app.myapp.myhotel.ShowActivity;

public class Adapter_Rooms extends RecyclerView.Adapter<Adapter_Rooms.MyHolder>  {


    ArrayList<Item_Rooms> arrayList;
    Context context;

    public Adapter_Rooms(ArrayList<Item_Rooms> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_Rooms.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.split_rooms, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Rooms.MyHolder holder, int position) {

        Item_Rooms item_rooms = arrayList.get(position);




        int id=item_rooms.getId();

        holder.name.setText(item_rooms.getName_room());
        holder.type.setText(item_rooms.getType_room());
        holder.price.setText("$"+item_rooms.getPrice_room());

        String sh=item_rooms.getShow();

        if (item_rooms.getImage() != null && !item_rooms.getImage().isEmpty()) {
            Picasso.get().load(item_rooms.getImage()).resize(200, 300).into(holder.imageView);
        }


        String e=item_rooms.getEnable();
        if (e.equals("no")){

                holder.layout.setBackgroundColor(Color.parseColor("#C6B4B4"));
                holder.lay.setVisibility(View.VISIBLE);


        }


        if (sh.equals("no")) {

//            for (int i = 0; i < holder.layout.getChildCount(); i++) {
//                View child = holder.imageView.getChildAt(i);
//
//                child.setEnabled(false);
//                holder.layout.setBackgroundColor(Color.parseColor("#C6B4B4"));
//            }
            holder.imageView.setEnabled(false);

            holder.ll.setVisibility(View.VISIBLE);
        }

        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, ShowActivity.class);
                i.putExtra("id",id);
                context.startActivity(i);


            }
        });








        holder.pop.setTag(id);



        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, Details_Rooms.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{
        TextView name,type,price,pop;
        ImageView imageView,imageView2;
        LinearLayout layout,lay,ll;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            ll=itemView.findViewById(R.id.layay);
            pop=itemView.findViewById(R.id.textpop);
            lay=itemView.findViewById(R.id.laylay);
            layout=itemView.findViewById(R.id.layout_main);
            name=itemView.findViewById(R.id.text_name_room);
            type=itemView.findViewById(R.id.text_type_rooms);
            price=itemView.findViewById(R.id.text_price_rooms);
            imageView=itemView.findViewById(R.id.image_rooms);
            imageView2=itemView.findViewById(R.id.image_show);

            pop.setOnClickListener(this);

        }



        @Override
        public void onClick(View v) {
            PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
            popupMenu.inflate(R.menu.edit_pop);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){

                case R.id.edit:

                    int ui= (int) pop.getTag();

                    upDate(ui);

                    break;
            }

            return false;
        }
    }













    private void upDate(int id) {

        final String token = SaveToken.getInstanse(context).getToken().getToken();

        JSONObject js = new JSONObject();
        try {


            js.put("enable","yes");


        } catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, LinkServer.UP_ENA+id, js, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {

                    Toast.makeText(context,"update",Toast.LENGTH_SHORT).show();
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
}
