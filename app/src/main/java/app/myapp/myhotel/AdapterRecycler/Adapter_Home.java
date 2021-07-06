package app.myapp.myhotel.AdapterRecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.myapp.myhotel.AddNextHotel;
import app.myapp.myhotel.Model.InfoHotel;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ViewPhoto;
import app.myapp.myhotel.ViewRooms;

public class Adapter_Home extends RecyclerView.Adapter<Adapter_Home.MyHolder> {


    ArrayList<InfoHotel> arrayList;

    Context context;
    public Adapter_Home(ArrayList<InfoHotel> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter_Home.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.split_home,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Home.MyHolder holder, int position) {

        InfoHotel infoHotel=arrayList.get(position);

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



        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,ViewRooms.class);
                intent.putExtra("id",id);
                context.startActivity(intent);


            }
        });


        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,ViewRooms.class);
                intent.putExtra("id",id);
                context.startActivity(intent);


            }
        });


        holder.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,ViewRooms.class);
                intent.putExtra("id",id);
                context.startActivity(intent);


            }
        });



        holder.more_admin.setOnClickListener(new View.OnClickListener() {
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

        TextView name_hotel,country,city,more_admin;

        RatingBar ratingBar;
        ImageView imageView1,imageView2,imageView3;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            imageView1=itemView.findViewById(R.id.imageView1);
            imageView2=itemView.findViewById(R.id.imageView2);
            imageView3=itemView.findViewById(R.id.imageView3);
            name_hotel=itemView.findViewById(R.id.name_hotel);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            country=itemView.findViewById(R.id.country);
            city=itemView.findViewById(R.id.city);
            more_admin=itemView.findViewById(R.id.text_photo_admin);




        }
    }
}
