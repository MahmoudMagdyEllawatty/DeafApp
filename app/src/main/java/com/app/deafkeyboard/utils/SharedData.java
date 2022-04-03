package com.app.deafkeyboard.utils;

import androidx.lifecycle.MutableLiveData;


import com.app.deafkeyboard.model.Chat;
import com.app.deafkeyboard.model.User;

import java.io.File;

public class SharedData {
    public static int userType = 0;



    public static File last_file;
    public static User loggedUser;
    public static Chat chat;
    public static MutableLiveData<Chat> mCurrentIndex = new MutableLiveData<>();
    public static int learn_type;
    public static boolean isEnglish;
    public static int side;
}
