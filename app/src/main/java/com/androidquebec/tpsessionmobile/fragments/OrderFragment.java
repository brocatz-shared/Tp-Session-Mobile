package com.androidquebec.tpsessionmobile.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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

    OrderItemAdapter orderItemAdapter;
    ArrayList<Article> list;

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

        list = new ArrayList<Article>();

        Article article = new Article();

        article.setTitre("Title");
        article.setPrix(203);
        article.setDescription("sdsdsds");
        for (int i =0; i < 5 ; i ++) {
            list.add(new Article());
        }


        RecyclerView order_RecycleView = view.findViewById(R.id.order_item_recycle_view);
        orderItemAdapter = new OrderItemAdapter(R.layout.item_horizontal_orderlist,context,list);
        order_RecycleView.setAdapter(orderItemAdapter);

        order_RecycleView.addItemDecoration(new ItemDecorator());

        new ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(order_RecycleView);
        return view;
    }


    ItemTouchHelper.SimpleCallback simpleItemTouchCallback= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int postion = viewHolder.getAdapterPosition();
            list.remove(postion);
            orderItemAdapter.notifyDataSetChanged();
        }
    };
}