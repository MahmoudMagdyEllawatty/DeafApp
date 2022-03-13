package com.app.deafkeyboard.controller;

import androidx.annotation.NonNull;

import com.app.deafkeyboard.callback.PostersCallback;
import com.app.deafkeyboard.model.Posters;
import com.app.deafkeyboard.utils.Config;
import com.app.deafkeyboard.utils.FirebaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class PostersController {
    private final String node = Config.POSTERS_NODE;
    ArrayList<Posters> users = new ArrayList<>();
    FirebaseHelper<Posters> helper = new FirebaseHelper<Posters>();

    public void Save(final Posters user, final PostersCallback callback){
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

    public void delete(Posters trainingType, PostersCallback callback){
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

    public void getPosterss(final PostersCallback callback){
        helper.getAll(node, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(queryDocumentSnapshots == null && e != null){
                    callback.onFail(e.getLocalizedMessage());
                }else {
                    ArrayList<Posters> users = new ArrayList<>();
                    assert queryDocumentSnapshots != null;
                    for (QueryDocumentSnapshot snap : queryDocumentSnapshots) {
                        Posters user = snap.toObject(Posters.class);
                        users.add(user);
                    }
                    callback.onSuccess(users);
                }
            }
        });
    }
}
