package com.app.deafkeyboard.callback;

import com.app.deafkeyboard.model.Chat;
import com.app.deafkeyboard.model.Posters;

import java.util.ArrayList;

public interface PostersCallback {
    void onSuccess(ArrayList<Posters> chats);
    void onFail(String msg);
}
