package com.androidquebec.tpsessionmobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;
import com.androidquebec.tpsessionmobile.adapter.ItemDecorator;
import com.androidquebec.tpsessionmobile.adapter.OrderItemAdapter;
import com.androidquebec.tpsessionmobile.model.Article;

import java.util.ArrayList;


public class OrderFragment extends Fragment {

    private  HomeActivity context;

    public OrderFragment(HomeActivity homeActivity) {
        // Required empty public constructor
        this.context = homeActivity;
    }

    public OrderFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_order, container, false);

        ArrayList<Article> list = new ArrayList<Article>();

        Article article = new Article();

        article.setTitre("Title");
        article.setPrix(203);
        article.setDescription("sdsdsds");
        for (int i =0; i < 100 ; i ++) {
            list.add(new Article());
        }


        RecyclerView order_RecycleView = view.findViewById(R.id.order_item_recycle_view);
        order_RecycleView.setAdapter(new OrderItemAdapter(R.layout.item_horizontal_orderlist,context,list));

        order_RecycleView.addItemDecoration(new ItemDecorator());

        return view;
    }
}