package app.myapp.myhotel.AdapterRecycler;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import app.myapp.myhotel.Model.InfoHotel;
import app.myapp.myhotel.Model.ItemShow;
import app.myapp.myhotel.R;
import app.myapp.myhotel.ViewPhoto;
import app.myapp.myhotel.ViewRooms;

public class Adapter_Show extends RecyclerView.Adapter<Adapter_Show.MyHolder> {


    ArrayList<ItemShow> arrayList;

    Context context;
    public Adapter_Show(ArrayList<ItemShow> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter_Show.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.split_notify_user,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Show.MyHolder holder, int position) {

        ItemShow infoHotel=arrayList.get(position);

        int id=infoHotel.getId();

        holder.name.setText(infoHotel.getName());
        holder.content.setText(infoHotel.getContent());
        holder.time.setText(formatdate(infoHotel.getTime()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name, content, time;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_name_noty);
            content = itemView.findViewById(R.id.text_content_noty);
            time = itemView.findViewById(R.id.textView_time);

        }


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

}
