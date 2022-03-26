package com.app.deafkeyboard.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.activities.user.UserChatMessagesActivity;
import com.app.deafkeyboard.model.Chat;
import com.app.deafkeyboard.utils.SharedData;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    ArrayList<Chat> chat;
    Context context;

    public ChatsAdapter(ArrayList<Chat> chat, Context context) {
        this.chat = chat;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.chat_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat1  = chat.get(position);

        String userName = "";
        String date = "";
        String msg = "";
        if(chat1.getToUser().equals(SharedData.loggedUser.getKey())){
            userName = chat1.getFromUserName();
        }else if(chat1.getFromUser().equals(SharedData.loggedUser.getKey())){
            userName = chat1.getToUserName();
        }

        if(chat1.getChatDetails() != null){
            if(chat1.getChatDetails().size() > 0){
                Chat.ChatDetails details = chat1.getChatDetails().get(chat1.getChatDetails().size()-1);
                msg = details.getMsg();
                date = details.getDate();
            }
        }


        holder.title.setText(userName);
        holder.msg.setText(msg);
        holder.date.setText(date);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedData.chat = chat1;
                Intent intent = new Intent(context, UserChatMessagesActivity.class);
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return chat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,msg,date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.user_name);
            msg = itemView.findViewById(R.id.msg);
            date = itemView.findViewById(R.id.date);

        }
    }
}
