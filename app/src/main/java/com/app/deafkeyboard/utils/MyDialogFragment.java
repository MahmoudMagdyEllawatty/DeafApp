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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyDialogFragment extends DialogFragment implements SpellCheckerSession.SpellCheckerSessionListener {

    private RecyclerView mRecyclerView,suggestions;
    TextView writtenMessage;
    private SpellCheckerSession mScs;
    public Context context;
    public EditText message;



    public MyDialogFragment() {
    }



    public static MyDialogFragment newInstance(String title){
        MyDialogFragment frag = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_row, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mRecyclerView = view.findViewById(R.id.signs);
        suggestions = view.findViewById(R.id.suggestions);
        writtenMessage = view.findViewById(R.id.message_written);

        final TextServicesManager tsm = (TextServicesManager)
                context.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
        mScs = tsm.newSpellCheckerSession(null, null, this, true);

        writtenMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().contains(" ")){
                    String text =charSequence.toString();
                    text = text.substring(text.indexOf(" ")+1);
                    if(!text.equals(""))
                        mScs.getSuggestions(new TextInfo(text), 3);
                }else{
                    if(charSequence.length() > 0)
                        mScs.getSuggestions(new TextInfo(charSequence.toString()), 3);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        suggestions.setItemAnimator(new DefaultItemAnimator());
        suggestions.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));

        // you can use LayoutInflater.from(getContext()).inflate(...) if you have xml layout
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        mRecyclerView.setAdapter(new KeyboardAdapter());

    }

    @Override
    public void onGetSuggestions(SuggestionsInfo[] arg0) {
        ArrayList<String> suggestions1 = new ArrayList<>();

        for (int i = 0; i < arg0.length; ++i) {
            // Returned suggestions are contained in SuggestionsInfo
            final int len = arg0[i].getSuggestionsCount();

            for (int j = 0; j < len; ++j) {
                suggestions1.add(arg0[i].getSuggestionAt(j));
            }

        }

        suggestions.setAdapter(new SuggestionsAdapter(suggestions1));

    }

    @Override
    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfos) {

    }



    private class KeyboardAdapter extends RecyclerView.Adapter<KeyboardAdapter.ViewHolder> {

        ArrayList<LettersHelper.Letters> letters ;

        public KeyboardAdapter() {
            letters = new ArrayList<>();
            letters.add(new LettersHelper.Letters(R.mipmap.space,"Space"));
            letters.add(new LettersHelper.Letters(R.mipmap.remove,"Remove"));
            letters.addAll(new LettersHelper(SharedData.isEnglish).getLetters());
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
                    writtenMessage.setText(msg);
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

    private class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.ViewHolder> {

        ArrayList<String> letters ;

        public SuggestionsAdapter(ArrayList<String> suggestions) {
            this.letters = suggestions;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.suggest_row,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String letter = letters.get(position);
            holder.letter.setText(letter);

            holder.letter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String msg = writtenMessage.getText().toString();
                    if(msg.contains(" "))
                        msg  = msg.substring(0,msg.lastIndexOf(" "))+" ";
                    else
                        msg = "";
                    writtenMessage.setText(msg+letter+" ");
                    message.setText(writtenMessage.getText().toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return letters.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView letter;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                letter = itemView.findViewById(R.id.letter);
            }
        }
    }
}
