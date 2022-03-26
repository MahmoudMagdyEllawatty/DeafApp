package com.app.deafkeyboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.deafkeyboard.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SignLettersAdapter extends RecyclerView.Adapter<SignLettersAdapter.ViewHolder>{

    ArrayList<Integer> letters;
    Context context;

    public SignLettersAdapter(ArrayList<Integer> letters, Context context) {
        this.letters = letters;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.sign_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get()
                .load(letters.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return letters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
