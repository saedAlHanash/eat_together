package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChatDialogsModel {


    @SerializedName("result")
    DialogResult result;

    public DialogResult getResult() {
        return result;
    }

    public void setResult(DialogResult result) {
        this.result = result;
    }

    public class ChatDialog {
        /*
               "who": 69,
              "message": "hellow",
              "chatid": "sila1sila69",
              "time": "2021-10-13T12:28:41.4008717+03:00",
              "": 6*/
        @SerializedName("who")
        int who;
        @SerializedName("message")
        String message;
        @SerializedName("chatid")
        String chatId;
        @SerializedName("time")
        String time;
        @SerializedName("id")
        int id;

        public int getWho() {
            return who;
        }

        public void setWho(int who) {
            this.who = who;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getChatId() {
            return chatId;
        }

        public void setChatId(String chatId) {
            this.chatId = chatId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public class DialogResult {
        @SerializedName("dialogs")
        ArrayList<ChatDialog> dialogs;

        public ArrayList<ChatDialog> getDialogs() {
            return dialogs;
        }

        public void setDialogs(ArrayList<ChatDialog> dialogs) {
            this.dialogs = dialogs;
        }
    }

}
