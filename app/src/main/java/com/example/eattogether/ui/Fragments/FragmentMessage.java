package com.example.eattogether.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;
import com.example.eattogether.Adapters.ChatListAdapter;
import com.example.eattogether.Models.ChatListModel;
import com.example.eattogether.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMessage extends Fragment {

    API api = ApiClint.getRetrofitInstance().create(API.class);
    @BindView(R.id.recyclerView_message)
    RecyclerView recyclerViewMessage;
    ChatListAdapter adapter;
    ArrayList<ChatListModel.ChatModel> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        Call<ChatListModel> call = api.getAllChatList();
        call.enqueue(new Callback<ChatListModel>() {
            @Override
            public void onResponse(Call<ChatListModel> call, Response<ChatListModel> response) {
                if (response.code() == 200) {
                    list = response.body().getResult();
                    adapter.refreshAdapter(list);
                }
            }

            @Override
            public void onFailure(Call<ChatListModel> call, Throwable t) {
            }
        });
        adapter = new ChatListAdapter(list, getContext());
        recyclerViewMessage.setAdapter(adapter);
        recyclerViewMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}