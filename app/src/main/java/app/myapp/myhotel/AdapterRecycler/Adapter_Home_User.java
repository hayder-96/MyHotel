package app.myapp.myhotel.AdapterRecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.myapp.myhotel.MapsActivity;
import app.myapp.myhotel.Model.InfoHotel;
import app.myapp.myhotel.Model.InfoHotelUser;
import app.myapp.myhotel.R;
import app.myapp.myhotel.User.MapsUser;
import app.myapp.myhotel.User.ViewRoomUser;
import app.myapp.myhotel.ViewPhoto;
import app.myapp.myhotel.ViewRooms;

public class Adapter_Home_User extends RecyclerView.Adapter<Adapter_Home_User.MyHolder> {


    ArrayList<InfoHotelUser> arrayList;

    Context context;
    public Adapter_Home_User(ArrayList<InfoHotelUser> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter_Home_User.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.split_home_user,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Home_User.MyHolder holder, int position) {

        InfoHotelUser infoHotel=arrayList.get(position);

        int id=infoHotel.getId();

        if (infoHotel.getImage1()!=null && !infoHotel.getImage1().isEmpty()) {
            Picasso.get().load(infoHotel.getImage1()).resize(200, 300).into(holder.imageView1);
            Picasso.get().load(infoHotel.getImage2()).resize(200, 300).into(holder.imageView2);
            Picasso.get().load(infoHotel.getImage3()).resize(200, 300).into(holder.imageView3);
        }

        holder.name_hotel.setText(infoHotel.getNamehotel());
        holder.ratingBar.setRating(Float.parseFloat(infoHotel.getEvaluation()));
        holder.country.setText(infoHotel.getCountry());
        holder.city.setText(infoHotel.getCity());
        if (!infoHotel.getEv().equals("null")) {
            double d = Double.parseDouble(infoHotel.getEv());
            if (d >= 5.0) {
                holder.eve.setText("التقييم" + "\t" + "5/5");
            } else {
                holder.eve.setText("التقييم" + "\t" + infoHotel.getEv() + "/5");
            }
        }


        int admin_id=infoHotel.getAdmin_id();
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MapsUser.class);
                 intent.putExtra("name",infoHotel.getNamehotel());
                intent.putExtra("lat",infoHotel.getLatitude());
                intent.putExtra("log",infoHotel.getLongtude());
                context.startActivity(intent);
            }
        });


        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context,ViewRoomUser.class);
                intent.putExtra("id",id);
                intent.putExtra("admin_id",admin_id);
                intent.putExtra("img",infoHotel.getImage1());
                context.startActivity(intent);



            }
        });

        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,ViewRoomUser.class);
                intent.putExtra("id",id);
                intent.putExtra("admin_id",admin_id);
                intent.putExtra("img",infoHotel.getImage1());
                context.startActivity(intent);



            }
        });


        holder.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,ViewRoomUser.class);
                intent.putExtra("id",id);
                intent.putExtra("admin_id",admin_id);
                intent.putExtra("img",infoHotel.getImage1());
                context.startActivity(intent);


            }
        });
        holder.morePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewPhoto.class);
                intent.putExtra("1",1);
                intent.putExtra("i1",infoHotel.getImage1());
                intent.putExtra("i2",infoHotel.getImage2());
                intent.putExtra("i3",infoHotel.getImage3());
               context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name_hotel,country,city,eve,morePhoto;

        RatingBar ratingBar;
        ImageView imageView1,imageView2,imageView3;
        ImageView button;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            imageView1=itemView.findViewById(R.id.imageView1_user);
            imageView2=itemView.findViewById(R.id.imageView2_user);
            imageView3=itemView.findViewById(R.id.imageView3_user);
            name_hotel=itemView.findViewById(R.id.name_hotel_user);
            ratingBar=itemView.findViewById(R.id.ratingBar_user);
            country=itemView.findViewById(R.id.country_user);
            city=itemView.findViewById(R.id.city_user);
            eve=itemView.findViewById(R.id.text_eve);
            morePhoto=itemView.findViewById(R.id.text_more_photo);
            button=itemView.findViewById(R.id.but_user);





        }
    }
}
