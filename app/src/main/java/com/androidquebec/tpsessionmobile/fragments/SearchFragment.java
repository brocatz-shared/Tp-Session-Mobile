package com.androidquebec.tpsessionmobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;
import com.androidquebec.tpsessionmobile.adapter.SearhItemAdapter;
import com.androidquebec.tpsessionmobile.model.Article;

import java.util.ArrayList;


public class SearchFragment extends Fragment {


    HomeActivity context;

    public SearchFragment(HomeActivity homeActivity) {
        // Required empty public constructor
        this.context = homeActivity;
    }

    public SearchFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView rv_searchPage = view.findViewById(R.id.rv_searchPage);

        rv_searchPage.setAdapter(new SearhItemAdapter(R.layout.item_search_page_cardview,context,new ArrayList<Article>()));

        return view;
    }
}