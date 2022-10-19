package com.androidquebec.tpsessionmobile.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;
import com.androidquebec.tpsessionmobile.adapter.ItemDecorator;
import com.androidquebec.tpsessionmobile.adapter.OrderItemAdapter;
import com.androidquebec.tpsessionmobile.adapter.RecycleViewClickListener;
import com.androidquebec.tpsessionmobile.model.Article;
import com.androidquebec.tpsessionmobile.model.Order;
import com.androidquebec.tpsessionmobile.model.RegistreArticle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;


public class OrderFragment extends Fragment implements RecycleViewClickListener {

    private  HomeActivity context;

    OrderItemAdapter orderItemAdapter;

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

        LinkedHashMap<Order, LinkedHashMap<Article,Integer>> orderList = RegistreArticle.getRegistreArticleInstance().getOrderListElement();


        RecyclerView order_RecycleView = view.findViewById(R.id.order_item_recycle_view);
        orderItemAdapter = new OrderItemAdapter(R.layout.item_horizontal_orderlist2,context,orderList, this);
        order_RecycleView.setAdapter(orderItemAdapter);

        order_RecycleView.addItemDecoration(new ItemDecorator());

        //new ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(order_RecycleView);
        return view;
    }


    @Override
    public void recyclerViewListClicked(View v, int position) {

        // Get the current click element

        LinkedHashMap<Order,LinkedHashMap<Article,Integer>> orderList = RegistreArticle.getRegistreArticleInstance().getOrderListElement();

        int customCounter = 0;

        Set<Order> orderSet = orderList.keySet();
        LinkedHashMap<Article,Integer> selectedOorder = null;

        for (Order order : orderSet) {
            if (customCounter == position) {
                selectedOorder = orderList.get(order);
            }
            customCounter ++;
        }



        OrderDetailFragment orderDetailFragment = new OrderDetailFragment(context,selectedOorder);
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.home_fragment, orderDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}