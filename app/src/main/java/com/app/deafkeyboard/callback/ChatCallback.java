package com.app.deafkeyboard.callback;

import com.app.deafkeyboard.model.Chat;

import java.util.ArrayList;

public interface ChatCallback {
    void onSuccess(ArrayList<Chat> chats);
    void onFail(String msg);
}
