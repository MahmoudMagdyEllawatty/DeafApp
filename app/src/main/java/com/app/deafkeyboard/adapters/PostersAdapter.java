package com.app.deafkeyboard.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.activities.admin.PostersActivity;
import com.app.deafkeyboard.callback.FileUploadCallback;
import com.app.deafkeyboard.callback.PostersCallback;
import com.app.deafkeyboard.controller.ImageController;
import com.app.deafkeyboard.controller.PostersController;
import com.app.deafkeyboard.model.Posters;
import com.app.deafkeyboard.utils.ImagePicker;
import com.app.deafkeyboard.utils.LoadingHelper;
import com.app.deafkeyboard.utils.SharedData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.ViewHolder> {

    ArrayList<Posters> posters;
    Context context;

    String imageURL = "";
    private static final int RESULT_LOAD_IMAGES = 25;

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

        if(posters1.getImage() != null){
            if(!posters1.getImage().equals("")){
                Picasso.get()
                        .load(posters1.getImage())
                        .into(holder.image);
            }

        }


        holder.title.setText(posters1.getTitle());
        holder.msg.setText(posters1.getDescription());

        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog(posters1);
            }
        });


        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadingHelper(context).showDialog("Delete Poster", "Are you sure?", "Delete", "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new PostersController().delete(posters1, new PostersCallback() {
                                    @Override
                                    public void onSuccess(ArrayList<Posters> chats) {
                                        Toast.makeText(context, "Deleted!!", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFail(String msg) {

                                    }
                                });
                            }
                        }, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                
                            }
                        });
            }
        });
    }


    private void showEditDialog(Posters posters){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_station, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);

        final Button dialogBtnSubmit = dialogView.findViewById(R.id.btn_submit);
        final ImageButton dialogBtnClose = dialogView.findViewById(R.id.btn_close);
        final EditText etxtTitle = dialogView.findViewById(R.id.etxt_title);
        final EditText etxt_description = dialogView.findViewById(R.id.etxt_description);
        final TextView title = dialogView.findViewById(R.id.title);
        final ImageButton selectImage = dialogView.findViewById(R.id.select_image);

        imageURL = posters.getImage();
        etxt_description.setText(posters.getDescription());
        etxtTitle.setText(posters.getTitle());

        title.setText("Edit Poster");

        (dialogView.findViewById(R.id.view_1)).setVisibility(View.GONE);

        final AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        dialogBtnSubmit.setOnClickListener(v -> {

            if(etxtTitle.getText() == null){
                etxtTitle.setError(context.getString(R.string.required));
                return;
            }else if(etxtTitle.getText().toString().equals("")){
                etxtTitle.setError(context.getString(R.string.required));
                return;
            }
            if(etxt_description.getText() == null){
                etxt_description.setError(context.getString(R.string.required));
                return;
            }else if(etxt_description.getText().toString().equals("")){
                etxt_description.setError(context.getString(R.string.required));
                return;
            }

            posters.setDescription(etxt_description.getText().toString());
            posters.setTitle(etxtTitle.getText().toString());


            new PostersController()
                    .Save(posters, new PostersCallback() {
                        @Override
                        public void onSuccess(ArrayList<Posters> policies) {
                            alertDialog.dismiss();
                            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(String msg) {
                            alertDialog.dismiss();
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                    });


        });


        dialogBtnClose.setOnClickListener(v -> alertDialog.dismiss());
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,msg;
        ImageView img_edit,img_delete,image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            msg = itemView.findViewById(R.id.msg);
            img_delete = itemView.findViewById(R.id.img_delete);
            img_edit = itemView.findViewById(R.id.img_edit);

            if(SharedData.loggedUser != null){
                img_delete.setVisibility(View.GONE);
                img_edit.setVisibility(View.GONE);
            }

        }
    }
}
