package com.example.eattogether.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;
import com.example.eattogether.APIs.ResponseModle.PostMessageResponse;
import com.example.eattogether.Adapters.ChatAdapter;
import com.example.eattogether.AppConfig;
import com.example.eattogether.DateConverter;
import com.example.eattogether.Models.ChatDialogsModel;
import com.example.eattogether.Models.PostMessageModel;
import com.example.eattogether.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat extends AppCompatActivity {

    API api = ApiClint.getRetrofitInstance().create(API.class);

    ArrayList<ChatDialogsModel.ChatDialog> list = new ArrayList<>();

    ChatAdapter adapter;

    @BindView(R.id.send_message_chat)
    ImageButton sendMessageChat;
    @BindView(R.id.message_chat)
    EditText messageChat;
    @BindView(R.id.recyclerView_chat)
    RecyclerView recyclerViewChat;

    String chatID;
    int receiverID;
    PostMessageModel model = new PostMessageModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppConfig.getSharedPreferencesInstance(this);
        Intent intent = getIntent();
        chatID = intent.getStringExtra("chat_id");
        receiverID = intent.getIntExtra("receiver_id", -1);
        model.setReceiver(receiverID);
        model.setWho(AppConfig.get_USER_ID());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);


        adapter = new ChatAdapter(this, list);
        recyclerViewChat.setAdapter(adapter);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        Call<ChatDialogsModel> call = api.getDialogs(chatID);
        call.enqueue(new Callback<ChatDialogsModel>() {
            @Override
            public void onResponse(Call<ChatDialogsModel> call, Response<ChatDialogsModel> response) {
                if (response.code() == 200) {
                    adapter.refreshAdapter(response.body().getResult().getDialogs());
                }
            }

            @Override
            public void onFailure(Call<ChatDialogsModel> call, Throwable t) {

                Toast.makeText(Chat.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        sendMessageChat.setOnClickListener(v -> {
            model.setTime(DateConverter.now());
            if (!messageChat.getText().toString().equals("")){
                model.setMessage(messageChat.getText().toString());
                Call<PostMessageResponse> call1 = api.postMessage(model);
                call1.enqueue(new Callback<PostMessageResponse>() {
                    @Override
                    public void onResponse(Call<PostMessageResponse> call, Response<PostMessageResponse> response) {
                        messageChat.getText().clear();
                    }

                    @Override
                    public void onFailure(Call<PostMessageResponse> call, Throwable t) {
                        Toast.makeText(Chat.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }
}