package com.androidquebec.tpsessionmobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;
import com.androidquebec.tpsessionmobile.adapter.OrderDetailItemAdapter;
import com.androidquebec.tpsessionmobile.model.Article;
import com.androidquebec.tpsessionmobile.model.Order;
import com.androidquebec.tpsessionmobile.utile.Calculate;

import java.util.LinkedHashMap;


public class OrderDetailFragment extends Fragment {

    private HomeActivity context;
    private LinkedHashMap<Article,Integer> orderList;

    public OrderDetailFragment() {

    }

    public OrderDetailFragment(HomeActivity context, LinkedHashMap<Article,Integer> orderList) {
        this.context = context;
        this.orderList = orderList;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);

        double cartTotal = Calculate.calculHashMapTotal(orderList);
        TextView lblTotalCartPrice = view.findViewById(R.id.lblOrderDetailTotal);
        ;lblTotalCartPrice.setText(String.format("%.2f", cartTotal) + "$ CAD");


        RecyclerView orderDetailRecyclerView = view.findViewById(R.id.orderDetailRecyclerView);
        orderDetailRecyclerView.setAdapter(new OrderDetailItemAdapter(R.layout.item_horizontal_cart_order_detail_item,context,orderList));

        return view;
    }
}