package com.androidquebec.tpsessionmobile.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.model.Article;
import com.androidquebec.tpsessionmobile.model.Order;
import com.androidquebec.tpsessionmobile.utile.Calculate;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    int layoutId;
    Activity context;
    LinkedHashMap<Order,LinkedHashMap<Article,Integer>> orderList;
    private static RecycleViewClickListener recycleViewClickListener;

    public OrderItemAdapter (int layoutId, Activity context, LinkedHashMap<Order,LinkedHashMap<Article,Integer>>  orderList, RecycleViewClickListener recycleViewClickListener) {
        this.layoutId = layoutId;
        this.context = context;
        this.orderList = orderList;
        this.recycleViewClickListener =recycleViewClickListener;
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener {

        public TextView txtArticleTitle, txtArticlePrice, lblDate;
        public ImageView imgArticleImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtArticleTitle = itemView.findViewById(R.id.lblOrderId);
            txtArticlePrice = itemView.findViewById(R.id.lblOrderTotal);
            lblDate = itemView.findViewById(R.id.lblOrderDate);
            imgArticleImage = itemView.findViewById(R.id.img_orderImage);

            itemView.setOnClickListener(this);
        }

        public TextView getTxtArticleTitle() {
            return txtArticleTitle;
        }

        public TextView getTxtArticlePrice() {
            return txtArticlePrice;
        }

        public ImageView getImgArticleImage() {
            return imgArticleImage;
        }

        public TextView getLblDate() { return lblDate; }

        @Override
        public void onClick(View view) {
            recycleViewClickListener.recyclerViewListClicked(view,this.getLayoutPosition());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       Set<Order> orderSet =  orderList.keySet();

       int customCounter = 0;
       Order visibleOrder = null;
       LinkedHashMap<Article,Integer> visibleLinkedHashMap = null;
       for (Order  order : orderSet) {
           if (customCounter == position) {
               visibleLinkedHashMap = orderList.get(order);
               visibleOrder = order;
               break;
           }
           customCounter ++;
       }

       double specifiqueCartListTotal = Calculate.calculHashMapTotal(visibleLinkedHashMap);

        holder.getTxtArticleTitle().setText("OrderID: " + visibleOrder.getOrderRef());
        holder.getTxtArticlePrice().setText( "Price :" + String.format("%.2f", specifiqueCartListTotal) + "$ CAD");
        holder.getLblDate().setText(visibleOrder.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
       // holder.getImgArticleImage().setBackgroundResource(R.drawable.ic_order);

    }



    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
