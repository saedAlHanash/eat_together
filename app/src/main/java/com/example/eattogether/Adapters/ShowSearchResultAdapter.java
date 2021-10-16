package com.example.eattogether.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eattogether.Helper.GetImageForUserHelper;
import com.example.eattogether.Models.FindPartnersModel;
import com.example.eattogether.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import butterknife.BindView;

public class ShowSearchResultAdapter extends RecyclerView.Adapter<ShowSearchResultAdapter.Holder> {
    ArrayList<FindPartnersModel.ResultFindPartnersModel> list;
    onRecyclerViewClickListener RecyclerListener;
    Context context;

    public ShowSearchResultAdapter(Context context, ArrayList<FindPartnersModel.ResultFindPartnersModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_search_result, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.partnerName.setText(list.get(position).getUserName());
        holder.partnerAge.setText(list.get(position).getCityName());
        holder.resturantName.setText(list.get(position).getDate());
        GetImageForUserHelper.downloadImage(list.get(position).getUserId(), holder.searchResultAvatar);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        MaterialButton searchResultBtnSendMessage;
        ImageView searchResultAvatar;
        TextView partnerName;
        TextView partnerAge;
        TextView resturantName;

        public Holder(@NonNull View itemView) {
            super(itemView);
            searchResultAvatar = itemView.findViewById(R.id.search_result_avatar);
            partnerName = itemView.findViewById(R.id.partner_name);
            partnerAge = itemView.findViewById(R.id.partner_age);
            resturantName = itemView.findViewById(R.id.resturant_name);
            searchResultBtnSendMessage = itemView.findViewById(R.id.searsh_result_btn_send_message);
            listener();
        }
        void listener(){
            searchResultBtnSendMessage.setOnClickListener(v -> {
                RecyclerListener.setOnSendMessageListener(getAdapterPosition());
            });
        }

    }
   public void listenerBtnSend(onRecyclerViewClickListener listener){
        this.RecyclerListener=listener;
    }

  public   interface onRecyclerViewClickListener {
        void setOnSendMessageListener(int position);
    }
}
