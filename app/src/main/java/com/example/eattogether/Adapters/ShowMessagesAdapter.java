package com.example.eattogether.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eattogether.R;

import java.util.ArrayList;


public class ShowMessagesAdapter extends RecyclerView.Adapter<ShowMessagesAdapter.Holder> {
    ArrayList<String> list = new ArrayList<>();
    Context context;
    ShowSearchResultAdapter.onRecyclerViewClickListener RecyclerListener;

    public ShowMessagesAdapter(ArrayList<String> list, Context context) {
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

        holder.messageMessages.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView messageMessages;
        TextView messageDate;
        TextView messageUserName;
        ImageView messageAvatar;

        public Holder(@NonNull View itemView) {
            super(itemView);

            messageAvatar = itemView.findViewById(R.id.message_avatar);
            messageDate = itemView.findViewById(R.id.message_date);
            messageMessages = itemView.findViewById(R.id.message_messages);
            messageUserName = itemView.findViewById(R.id.message_user_name);

        }
    }
}
