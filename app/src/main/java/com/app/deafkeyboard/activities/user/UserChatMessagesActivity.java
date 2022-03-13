package com.app.deafkeyboard.activities.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.callback.ChatCallback;
import com.app.deafkeyboard.controller.ChatController;
import com.app.deafkeyboard.model.Chat;
import com.app.deafkeyboard.utils.LettersHelper;
import com.app.deafkeyboard.utils.LoadingHelper;
import com.app.deafkeyboard.utils.SharedData;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class UserChatMessagesActivity extends AppCompatActivity {

    RecyclerView messagesList;

    static EditText message;
    ImageView send,deafKeyboard;

    ArrayList<Chat.ChatDetails> chatDetails = new ArrayList<>();
    int side = 0;

    LoadingHelper loadingHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chat_messages);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_gradient));
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button

        loadingHelper = new LoadingHelper(this);
        if( SharedData.chat.getToUser().equals(SharedData.loggedUser.getKey())){
            side = 2;
            getSupportActionBar().setTitle(SharedData.chat.getFromUserName());
        }else if( SharedData.chat.getFromUser().equals(SharedData.loggedUser.getKey())){
            side = 1;
            getSupportActionBar().setTitle(SharedData.chat.getToUserName());
        }


        messagesList = findViewById(R.id.messages_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        messagesList.setLayoutManager(linearLayoutManager);
        messagesList.setItemAnimator(new DefaultItemAnimator());


        message = findViewById(R.id.message);
        send = findViewById(R.id.send);
        deafKeyboard = findViewById(R.id.deaf_keyboard);


        chatDetails = SharedData.chat.getChatDetails();

        messagesList.setAdapter(new ChatMessageAdapter());

        SharedData.mCurrentIndex.observe(this, new Observer<Chat>() {
            @Override
            public void onChanged(Chat chat) {
                chatDetails = SharedData.chat.getChatDetails();
                messagesList.setAdapter(new ChatMessageAdapter());
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(message.getText() == null){
                    return;
                }else if(message.getText().toString().equals("")){
                    return;
                }else{
                    Chat.ChatDetails newMsg = new Chat.ChatDetails();
                    newMsg.setDate(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()));
                    newMsg.setMsg(message.getText().toString());
                    newMsg.setSide(side);

                    ArrayList<Chat.ChatDetails> chatDetailsArrayList = SharedData.chat.getChatDetails();
                    chatDetailsArrayList.add(newMsg);

                    SharedData.chat.setChatDetails(chatDetailsArrayList);

                    messagesList.setAdapter(new ChatMessageAdapter());
                    new ChatController().Save(SharedData.chat, new ChatCallback() {
                        @Override
                        public void onSuccess(ArrayList<Chat> children) {
                            message.setText("");
                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
                }
            }
        });


        deafKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignDialog();
            }
        });

    }

    private void showSignDialog(){
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(getSupportFragmentManager(),"Tag");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(UserChatMessagesActivity.this)
                    .inflate(R.layout.chat_message_row,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Chat.ChatDetails detail = chatDetails.get(position);

            if(side == detail.getSide()){
                holder.relativeLayout.setVisibility(View.VISIBLE);
                holder.relativeLayout1.setVisibility(View.GONE);
            }else{
                holder.relativeLayout1.setVisibility(View.VISIBLE);
                holder.relativeLayout.setVisibility(View.GONE);
            }

            holder.message.setText(detail.getMsg());
            holder.date.setText(detail.getDate());

            holder.message1.setText(detail.getMsg());
            holder.date1.setText(detail.getDate());


        }

        @Override
        public int getItemCount() {
            return chatDetails.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            LinearLayout relativeLayout;
            TextView message,date;

            LinearLayout relativeLayout1;
            TextView message1,date1;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                relativeLayout = itemView.findViewById(R.id.my_side);
                message = itemView.findViewById(R.id.message);
                date = itemView.findViewById(R.id.date);


                relativeLayout1 = itemView.findViewById(R.id.his_side);
                message1 = itemView.findViewById(R.id.message1);
                date1 = itemView.findViewById(R.id.date1);
            }
        }
    }



    public static  class MyDialogFragment extends DialogFragment {

        private RecyclerView mRecyclerView;

        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            mRecyclerView = new RecyclerView(getContext());
            // you can use LayoutInflater.from(getContext()).inflate(...) if you have xml layout
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
            mRecyclerView.setAdapter(new KeyboardAdapter());

            return new AlertDialog.Builder(getActivity())
                    .setTitle("Sign Keyboard")
                    .setView(mRecyclerView)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // do something
                                }
                            }
                    ).create();
        }


        private class KeyboardAdapter extends RecyclerView.Adapter<KeyboardAdapter.ViewHolder> {

            ArrayList<LettersHelper.Letters> letters ;

            public KeyboardAdapter() {
                letters = new ArrayList<>();
                letters.add(new LettersHelper.Letters(R.mipmap.space,"Space"));
                letters.add(new LettersHelper.Letters(R.mipmap.remove,"Remove"));
                letters.addAll(new LettersHelper().getLetters());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getActivity())
                        .inflate(R.layout.letter_item,parent,false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                LettersHelper.Letters letter = letters.get(position);

                Picasso.get()
                        .load(letter.getImage())
                        .into(holder.letter);


                holder.letter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String currentLetter = letter.getName();
                        String msg = message.getText().toString();
                        if(currentLetter.equals("Space")){
                            msg +=" ";
                        }else if(currentLetter.equals("Remove")){
                            if(msg.length() > 0)
                                msg = msg.substring(0,msg.length()-1);
                        }else{
                            msg += currentLetter.toLowerCase();
                        }
                        message.setText(msg);
                    }
                });

            }

            @Override
            public int getItemCount() {
                return letters.size();
            }

            class ViewHolder extends RecyclerView.ViewHolder{

                ImageView letter;
                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    letter = itemView.findViewById(R.id.letter);
                }
            }
        }

    }



}