package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChatListModel {

    @SerializedName("result")
    ArrayList<ChatModel> result;
    @SerializedName("success")
    boolean success;

    public ArrayList<ChatModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<ChatModel> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public class ChatModel {
        /*    "id": "sila1sila69",
              "accountId": 69,
              "fullName": "admin admin",
              "contactId": 1,
              "lastMessage": "hellow",
              "lastMessageTime": "2021-10-13T12:30:00.2145917+03:00",
              "dialogs": []*/
        @SerializedName("id")
        String chatID;
        @SerializedName("accountId")
        int accountId;
        @SerializedName("fullName")
        String fullName;
        @SerializedName("lastMessage")
        String lastMessage;
        @SerializedName("lastMessageTime")
        String lastMessageTime;
        @SerializedName("contactId")
        int contactId;

        public String getChatID() {
            return chatID;
        }

        public void setChatID(String chatID) {
            this.chatID = chatID;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getLastMessage() {
            return lastMessage;
        }

        public void setLastMessage(String lastMessage) {
            this.lastMessage = lastMessage;
        }

        public String getLastMessageTime() {
            return lastMessageTime;
        }

        public void setLastMessageTime(String lastMessageTime) {
            this.lastMessageTime = lastMessageTime;
        }

        public int getContactId() {
            return contactId;
        }

        public void setContactId(int contactId) {
            this.contactId = contactId;
        }

    }

}
