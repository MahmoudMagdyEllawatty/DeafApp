package com.app.deafkeyboard.model;

import java.util.ArrayList;

public class Chat {
    private String key;
    private String fromUser;
    private String toUser;
    private ArrayList<ChatDetails> chatDetails;
    private String fromUserName;
    private String toUserName;

    public Chat() {
    }


    public Chat(String key, String fromUser, String toUser, ArrayList<ChatDetails> chatDetails, String fromUserName, String toUserName) {
        this.key = key;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.chatDetails = chatDetails;
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public ArrayList<ChatDetails> getChatDetails() {
        return chatDetails;
    }

    public void setChatDetails(ArrayList<ChatDetails> chatDetails) {
        this.chatDetails = chatDetails;
    }

    public static class ChatDetails{
        private String msg;
        private String date;
        private int side;


        public ChatDetails() {
        }


        public ChatDetails(String msg, String date, int side) {
            this.msg = msg;
            this.date = date;
            this.side = side;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getSide() {
            return side;
        }

        public void setSide(int side) {
            this.side = side;
        }
    }
}
