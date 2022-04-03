package com.app.deafkeyboard.activities.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.adapters.SignLettersAdapter;
import com.app.deafkeyboard.callback.ChatCallback;
import com.app.deafkeyboard.controller.ChatController;
import com.app.deafkeyboard.model.Chat;
import com.app.deafkeyboard.utils.ArabicSignHelper;
import com.app.deafkeyboard.utils.EnglishSignHelper;
import com.app.deafkeyboard.utils.LettersHelper;
import com.app.deafkeyboard.utils.LoadingHelper;
import com.app.deafkeyboard.utils.MyDialogFragment;
import com.app.deafkeyboard.utils.SharedData;
import com.app.deafkeyboard.utils.StickerFragment;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class UserChatMessagesActivity extends AppCompatActivity {

    RecyclerView messagesList;

    static EditText message;
    ImageView send,deafKeyboard,sticker;


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


        SharedData.side = side;
        messagesList = findViewById(R.id.messages_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        messagesList.setLayoutManager(linearLayoutManager);
        messagesList.setItemAnimator(new DefaultItemAnimator());


        message = findViewById(R.id.message);
        send = findViewById(R.id.send);
        deafKeyboard = findViewById(R.id.deaf_keyboard);
        sticker = findViewById(R.id.sticker_keyboard);

        sticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStickerDialog();
            }
        });

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

    private void showStickerDialog(){
        FragmentManager fm = getSupportFragmentManager();
        StickerFragment editNameDialogFragment = StickerFragment.newInstance("Some Title");
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

    private void showSignDialog(){
        new LoadingHelper(UserChatMessagesActivity.this)
                .showDialog("Select Language", "Please,Select Ar or En", "Ar", "En", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedData.isEnglish = false;
                        FragmentManager fm = getSupportFragmentManager();
                        MyDialogFragment editNameDialogFragment = MyDialogFragment.newInstance("Some Title");
                        editNameDialogFragment.message = message;
                        editNameDialogFragment.context = UserChatMessagesActivity.this;
                        editNameDialogFragment.show(fm, "fragment_edit_name");
//                        MyDialogFragment myDialogFragment = new MyDialogFragment();
//                        myDialogFragment.show(getSupportFragmentManager(),"Tag");
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedData.isEnglish = true;
                        FragmentManager fm = getSupportFragmentManager();
                        MyDialogFragment editNameDialogFragment = MyDialogFragment.newInstance("Some Title");
                        editNameDialogFragment.message = message;
                        editNameDialogFragment.context = UserChatMessagesActivity.this;
                        editNameDialogFragment.show(fm, "fragment_edit_name");
                    }
                });

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

            if(detail.getMsg().toLowerCase().contains("gif:")){
                holder.gif1.setVisibility(View.VISIBLE);
                holder.gif2.setVisibility(View.VISIBLE);

                holder.message.setVisibility(View.GONE);
                holder.message1.setVisibility(View.GONE);

                holder.translate1.setVisibility(View.GONE);
                holder.translate.setVisibility(View.GONE);

                Integer image = Integer.parseInt(detail.getMsg().substring(4));
                holder.gif1.setBackgroundResource(image);
                holder.gif2.setBackgroundResource(image);

            }else{
                holder.gif1.setVisibility(View.GONE);
                holder.gif2.setVisibility(View.GONE);

                holder.message.setVisibility(View.VISIBLE);
                holder.message1.setVisibility(View.VISIBLE);

                holder.translate1.setVisibility(View.VISIBLE);
                holder.translate.setVisibility(View.VISIBLE);
            }

            holder.translate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showTranslateDialog(detail.getMsg());
                }
            });


            holder.translate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showTranslateDialog(detail.getMsg());
                }
            });

        }

        private void showTranslateDialog(String msg){
            if(isEnglish(msg)){
                showEnglishTranslator(msg);
            }else{
                showArabicTranslator(msg);
            }
        }

        private void showEnglishTranslator(String msg){
                ArrayList<Integer> signs = new EnglishSignHelper(msg).images;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UserChatMessagesActivity.this);
                View view = LayoutInflater.from(UserChatMessagesActivity.this)
                        .inflate(R.layout.translate_layout,null);

                TextView mainMessage = view.findViewById(R.id.main_text);
                RecyclerView list = view.findViewById(R.id.list);

                mainMessage.setText(msg);
                list.setItemAnimator(new DefaultItemAnimator());
                list.setLayoutManager(new GridLayoutManager(UserChatMessagesActivity.this,3));
                list.setAdapter(new SignLettersAdapter(signs,UserChatMessagesActivity.this));

                alertDialog.setView(view);
                alertDialog.show();
        }

        private void showArabicTranslator(String msg){
            ArrayList<Integer> signs = new ArabicSignHelper(msg).images;
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UserChatMessagesActivity.this);
            View view = LayoutInflater.from(UserChatMessagesActivity.this)
                    .inflate(R.layout.translate_layout,null);

            TextView mainMessage = view.findViewById(R.id.main_text);
            RecyclerView list = view.findViewById(R.id.list);

            mainMessage.setText(msg);
            list.setItemAnimator(new DefaultItemAnimator());
            list.setLayoutManager(new RtlGridLayoutManager(UserChatMessagesActivity.this,3));
            list.setAdapter(new SignLettersAdapter(signs,UserChatMessagesActivity.this));

            alertDialog.setView(view);
            alertDialog.show();
        }



        private boolean isEnglish(String msg){
            String[] englishLetters = new String[]{ "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","y","z"};
            for (char letter: msg.toLowerCase().toCharArray()) {
                if(Arrays.asList(englishLetters).contains(String.valueOf(letter))){
                    return true;
                }
            }
            return false;
        }

        @Override
        public int getItemCount() {
            return chatDetails.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            RelativeLayout relativeLayout;
            TextView message,date;

            RelativeLayout relativeLayout1;
            TextView message1,date1;

            ImageButton translate,translate1;

            GifImageView gif1,gif2;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                translate = itemView.findViewById(R.id.translate);
                translate1 = itemView.findViewById(R.id.translate1);

                relativeLayout = itemView.findViewById(R.id.my_side);
                message = itemView.findViewById(R.id.message);
                date = itemView.findViewById(R.id.date);

                gif1 = itemView.findViewById(R.id.gif1);
                gif2 = itemView.findViewById(R.id.gif2);


                relativeLayout1 = itemView.findViewById(R.id.his_side);
                message1 = itemView.findViewById(R.id.message1);
                date1 = itemView.findViewById(R.id.date1);
            }
        }
    }



    public class RtlGridLayoutManager extends GridLayoutManager {

        public RtlGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        public RtlGridLayoutManager(Context context, int spanCount) {
            super(context, spanCount);
        }

        public RtlGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
            super(context, spanCount, orientation, reverseLayout);
        }

        @Override
        protected boolean isLayoutRTL(){
            return true;
        }
    }

}