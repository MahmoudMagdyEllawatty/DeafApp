package com.app.deafkeyboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.model.Posters;

import java.util.ArrayList;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.ViewHolder> {

    ArrayList<Posters> posters;
    Context context;

    public PostersAdapter(ArrayList<Posters> posters, Context context) {
        this.posters = posters;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.poster_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Posters posters1  = posters.get(position);

        holder.title.setText(posters1.getTitle());
        holder.msg.setText(posters1.getDescription());
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,msg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            msg = itemView.findViewById(R.id.msg);

        }
    }
}
