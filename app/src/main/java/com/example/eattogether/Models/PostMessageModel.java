package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

public class PostMessageModel {

    /*{
  "recipientID": 0,
  "who": 0,
  "message": "string",
  "time": "2021-10-16T22:27:57.630Z"
}*/
    @SerializedName("recipientID")
    int receiver;
    @SerializedName("who")
    int who;
    @SerializedName("message")
    String message;
    @SerializedName("time")
    String time;

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
