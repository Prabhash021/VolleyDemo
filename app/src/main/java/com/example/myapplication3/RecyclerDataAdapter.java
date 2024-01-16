package com.example.myapplication3;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.ViewHolder>{
    Context context;
    ArrayList<dataModel> arrData;

    RecyclerDataAdapter(Context context, ArrayList<dataModel> arrData){
        this.context = context;
        this.arrData = arrData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index = holder.getAdapterPosition();

        holder.txtTitle.setText(String.format("Title: %s", arrData.get(position).title));
        holder.txtId.setText(String.format("Id: %s", arrData.get(position).id));
        holder.txtUrl.setText(String.format("Url: %s",arrData.get(position).url));
        Glide.with(context)
                .load(arrData.get(position).url)
                .into(holder.imageView);
        Log.e("tag4",arrData.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle(arrData.get(index).title);
                alertDialog.setMessage(String.format("ID: "+arrData.get(index).id +"%nURL: "+arrData.get(index).url+"%nThumbnail URL: "+arrData.get(index).thumbnailUrl));
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle, txtUrl, txtId;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.title);
            txtId = itemView.findViewById(R.id.id);
            txtUrl = itemView.findViewById(R.id.url);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
