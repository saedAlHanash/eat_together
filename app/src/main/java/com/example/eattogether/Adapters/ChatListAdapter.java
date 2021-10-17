package com.example.eattogether.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eattogether.DateConverter;
import com.example.eattogether.Helper.GetImageForUserHelper;
import com.example.eattogether.Models.ChatListModel;
import com.example.eattogether.R;
import com.example.eattogether.ui.Chat;
import com.example.eattogether.ui.Home;

import java.util.ArrayList;


public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.Holder> {
    ArrayList<ChatListModel.ChatModel> list;
    Context context;

    public ChatListAdapter(ArrayList<ChatListModel.ChatModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_messages, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        GetImageForUserHelper.downloadImage(list.get(position).getContactId(), holder.avatar);
        holder.last_message.setText(list.get(position).getLastMessage());
        holder.dateMessage.setText(DateConverter.formatDate(list.get(position).getLastMessageTime()));
        holder.userName.setText(list.get(position).getFullName());
    }

    public void refreshAdapter(ArrayList<ChatListModel.ChatModel> refreshList){
        this.list = refreshList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView last_message;
        TextView dateMessage;
        TextView userName;
        ImageView avatar;

        public Holder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.message_avatar);
            dateMessage = itemView.findViewById(R.id.message_date);
            last_message = itemView.findViewById(R.id.message_last_message);
            userName = itemView.findViewById(R.id.message_user_name);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,Chat.class);
            intent.putExtra("chat_id",list.get(getAdapterPosition()).getChatID());
            intent.putExtra("receiver_id",list.get(getAdapterPosition()).getContactId());
            context.startActivity(intent);
        }
    }
}
