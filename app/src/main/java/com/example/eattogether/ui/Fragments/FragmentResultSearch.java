package com.example.eattogether.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eattogether.Adapters.ShowSearchResultAdapter;
import com.example.eattogether.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentResultSearch extends Fragment {


    @BindView(R.id.search_result_recyclerView)
    RecyclerView searchResultRecyclerView;
    ShowSearchResultAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_search, container, false);
        ButterKnife.bind(this, view);
        ArrayList<String > list = new ArrayList<>();
        list.add("saed");
        list.add("saed");
        list.add("saed");
        list.add("saed");
        adapter= new ShowSearchResultAdapter(getContext(),list);
        searchResultRecyclerView.setAdapter(adapter);
        searchResultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}