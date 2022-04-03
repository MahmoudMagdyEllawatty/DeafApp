package com.app.deafkeyboard.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.deafkeyboard.R;
import com.app.deafkeyboard.activities.user.UserChatMessagesActivity;
import com.app.deafkeyboard.callback.ChatCallback;
import com.app.deafkeyboard.controller.ChatController;
import com.app.deafkeyboard.model.Chat;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class StickerFragment extends DialogFragment{

    private RecyclerView mRecyclerView;
    public Context context;



    public StickerFragment() {
    }



    public static StickerFragment newInstance(String title){
        StickerFragment frag = new StickerFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sticker_row, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mRecyclerView = view.findViewById(R.id.signs);

        // you can use LayoutInflater.from(getContext()).inflate(...) if you have xml layout
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mRecyclerView.setAdapter(new KeyboardAdapter());

    }



    private class KeyboardAdapter extends RecyclerView.Adapter<KeyboardAdapter.ViewHolder> {

        ArrayList<Integer> letters ;

        public KeyboardAdapter() {
            letters = new ArrayList<>();
            letters.add(R.mipmap.bye_bye);
            letters.add(R.mipmap.whats_your_name);
            letters.add(R.mipmap.go_home);
            letters.add(R.mipmap.happy);
            letters.add(R.mipmap.anniversary);
            letters.add(R.mipmap.done);
            letters.add(R.mipmap.thanks);
            letters.add(R.mipmap.mosque);
            letters.add(R.mipmap.hospital);
            letters.add(R.mipmap.hardly);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.gif_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Integer letter = letters.get(position);

//            Picasso.get()
//                    .load(letter)
//                    .into(holder.letter);

            holder.letter.setBackgroundResource(letter);

            holder.letter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Chat.ChatDetails newMsg = new Chat.ChatDetails();
                    newMsg.setDate(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ENGLISH).format(Calendar.getInstance().getTime()));
                    newMsg.setMsg("GIF:"+letter);
                    newMsg.setSide(SharedData.side);

                    ArrayList<Chat.ChatDetails> chatDetailsArrayList = SharedData.chat.getChatDetails();
                    chatDetailsArrayList.add(newMsg);

                    SharedData.chat.setChatDetails(chatDetailsArrayList);

                    new ChatController().Save(SharedData.chat, new ChatCallback() {
                        @Override
                        public void onSuccess(ArrayList<Chat> children) {

                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    });
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
