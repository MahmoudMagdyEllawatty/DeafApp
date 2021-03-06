package com.app.deafkeyboard.controller;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.app.deafkeyboard.callback.ChatCallback;
import com.app.deafkeyboard.model.Chat;
import com.app.deafkeyboard.utils.Config;
import com.app.deafkeyboard.utils.FirebaseHelper;
import com.app.deafkeyboard.utils.SharedData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class ChatController {
    private final String node = Config.CHAT_NODE;
    ArrayList<Chat> users = new ArrayList<>();
    FirebaseHelper<Chat> helper = new FirebaseHelper<Chat>();

    public void Save(final Chat user, final ChatCallback callback){
        if(user.getKey().equals("")){
            user.setKey(helper.getKey(node));
        }

        helper.save(node,user.getKey(),user)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            users.add(user);
                            callback.onSuccess(users);
                        }else{
                            callback.onFail(task.getException().toString());
                        }
                    }
                });
    }

    public void delete(Chat trainingType, ChatCallback callback){
        helper.delete(node, trainingType.getKey())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            users = new ArrayList<>();
                            callback.onSuccess(users);
                        }else{
                            callback.onFail(task.getException().toString());
                        }
                    }
                });
    }

    public void getChats(final ChatCallback callback){
        helper.getAll(node, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(queryDocumentSnapshots == null && e != null){
                    callback.onFail(e.getLocalizedMessage());
                }else {
                    ArrayList<Chat> users = new ArrayList<>();
                    assert queryDocumentSnapshots != null;
                    for (QueryDocumentSnapshot snap : queryDocumentSnapshots) {
                        Chat user = snap.toObject(Chat.class);

                        if(user.getFromUser().equals(SharedData.loggedUser.getKey()) ||
                            user.getToUser().equals(SharedData.loggedUser.getKey()))
                            users.add(user);

                        if(SharedData.chat != null){
                            if(SharedData.chat.getKey().equals(user.getKey())){
                                SharedData.chat = user;
                                SharedData.mCurrentIndex.setValue(user);
                            }
                        }


                    }
                    callback.onSuccess(users);
                }
            }
        });
    }
}
