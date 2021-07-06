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

import app.myapp.myhotel.Model.InfoHotel;
import app.myapp.myhotel.Model.ItemComment;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ViewRooms;

public class Adapter_Comment extends RecyclerView.Adapter<Adapter_Comment.MyHolder> {


    ArrayList<ItemComment> arrayList;

    Context context;
    public Adapter_Comment(ArrayList<ItemComment> arrayList,Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter_Comment.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.split_comment,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Comment.MyHolder holder, int position) {

        ItemComment itemComment=arrayList.get(position);

        int id=itemComment.getId();
        holder.ratingBar.setRating(Float.parseFloat(itemComment.getRating()));
        holder.name.setText(itemComment.getName());
        holder.comment.setText(itemComment.getContent());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name,comment;

        RatingBar ratingBar;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.com_name);
            comment=itemView.findViewById(R.id.com_comment);
            ratingBar=itemView.findViewById(R.id.com_rating);


        }
    }
}
