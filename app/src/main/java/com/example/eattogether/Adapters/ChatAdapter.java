package com.example.eattogether.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eattogether.AppConfig;
import com.example.eattogether.Models.ChatDialogsModel;
import com.example.eattogether.Models.ChatListModel;
import com.example.eattogether.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<ChatDialogsModel.ChatDialog> list;

    public ChatAdapter(Context context, ArrayList<ChatDialogsModel.ChatDialog> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getWho() == AppConfig.get_USER_ID()) return 0;
        else return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: {
                return new Holder0(LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_send_message, parent, false));
            }
            case 1: {
                return new ChatAdapter.Holder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.dialod_get_message, parent, false));
            }
        }
        return new Holder0(LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_send_message, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 0: {
                Holder0 holder0 = (Holder0) holder;
                holder0.sendMessage.setText(list.get(position).getMessage());
                break;
            }
            case 1: {
                Holder1 holder1 = (Holder1) holder;
                holder1.getMessage.setText(list.get(position).getMessage());
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void refreshAdapter(ArrayList<ChatDialogsModel.ChatDialog> list){
        this.list =list;
        notifyDataSetChanged();
    }


    class Holder1 extends RecyclerView.ViewHolder {

        TextView getMessage;
        public Holder1(@NonNull View itemView) {
            super(itemView);
            getMessage = itemView.findViewById(R.id.dialod_get_message);
        }
    }

    class Holder0 extends RecyclerView.ViewHolder {

        TextView sendMessage;
        public Holder0(@NonNull View itemView) {
            super(itemView);
            sendMessage = itemView.findViewById(R.id.dialod_send_message);
        }
    }
}
