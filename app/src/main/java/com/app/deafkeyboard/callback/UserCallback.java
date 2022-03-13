package com.app.deafkeyboard.callback;

import com.app.deafkeyboard.model.Chat;
import com.app.deafkeyboard.model.User;

import java.util.ArrayList;

public interface UserCallback {
    void onSuccess(ArrayList<User> chats);
    void onFail(String msg);
}
