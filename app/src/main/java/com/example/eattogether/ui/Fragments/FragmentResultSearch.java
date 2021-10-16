package com.example.eattogether.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eattogether.Adapters.ShowSearchResultAdapter;
import com.example.eattogether.Models.FindPartnersModel;
import com.example.eattogether.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentResultSearch extends Fragment {



    @BindView(R.id.search_result_recyclerView)
    RecyclerView searchResultRecyclerView;
    ShowSearchResultAdapter adapter;
    ArrayList<FindPartnersModel.ResultFindPartnersModel> list = new ArrayList<>();
    ArrayList<FindPartnersModel.ResultFindPartnersModel> list1 = new ArrayList<>();

    public FragmentResultSearch() {
    }
    public FragmentResultSearch(ArrayList<FindPartnersModel.ResultFindPartnersModel > list) {
        this.list=list;
        list1.add(new FindPartnersModel().new ResultFindPartnersModel(0,69,1,20,100,1,"saed",
                "saedAlHansh","damas"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_search, container, false);
        ButterKnife.bind(this, view);
        adapter= new ShowSearchResultAdapter(getContext(),list1);
 adapter.listenerBtnSend(new ShowSearchResultAdapter.onRecyclerViewClickListener() {
     @Override
     public void setOnSendMessageListener(int position) {
         Toast.makeText(getContext(), ""+list1.get(position).getUserName(), Toast.LENGTH_SHORT).show();
     }
 });
        searchResultRecyclerView.setAdapter(adapter);
        searchResultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}