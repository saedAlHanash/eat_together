package com.example.eattogether.Helper;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;
import com.example.eattogether.Models.ChatListModel;
import java.util.ArrayList;

public class GetAllChatListHelper {
    static API api = ApiClint.getRetrofitInstance().create(API.class);
    public static ArrayList<ChatListModel.ChatModel> chatList;

    public static void getChatList() {


    }
}
