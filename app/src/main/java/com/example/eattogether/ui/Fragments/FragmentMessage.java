package com.example.eattogether.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eattogether.Adapters.ShowMessagesAdapter;
import com.example.eattogether.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentMessage extends Fragment {


    @BindView(R.id.recyclerView_message)
    RecyclerView recyclerViewMessage;
    ShowMessagesAdapter adapter;
    ArrayList<String> list = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);

        list.add("saed com back to home");
        list.add("hi i am hear");
        list.add("are you in collage ? ");
        adapter = new ShowMessagesAdapter(list, getContext());

        recyclerViewMessage.setAdapter(adapter);

        recyclerViewMessage.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}