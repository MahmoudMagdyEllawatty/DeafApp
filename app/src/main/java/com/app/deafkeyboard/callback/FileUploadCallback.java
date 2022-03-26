package com.app.deafkeyboard.callback;

public interface FileUploadCallback {
    void onSuccess(String url);
    void onFail(String msg);
}
