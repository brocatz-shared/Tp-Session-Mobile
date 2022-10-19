package com.androidquebec.tpsessionmobile.adapter;

import android.app.Activity;
import android.net.Uri;
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
import com.bumptech.glide.Glide;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Set;

public class OrderDetailItemAdapter extends RecyclerView.Adapter<OrderDetailItemAdapter.ViewHolder> {

    int layoutId;
    Activity context;
    LinkedHashMap<Article,Integer> orderList;

    public OrderDetailItemAdapter (int layoutId, Activity context, LinkedHashMap<Article,Integer>  orderList) {
        this.layoutId = layoutId;
        this.context = context;
        this.orderList = orderList;
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder {

        public TextView lblArticleTitle, lblArticlePrice, lblQuantity;
        public ImageView imgArticleImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblArticleTitle = itemView.findViewById(R.id.lblOrderDetailItemName);
            lblArticlePrice = itemView.findViewById(R.id.lblOrderDetailPrie);
            lblQuantity = itemView.findViewById(R.id.lblOrderDetailQuantity);
            imgArticleImage = itemView.findViewById(R.id.img_orderDetailImage);
        }

        public TextView getLblArticleTitle() {
            return lblArticleTitle;
        }

        public TextView getLblArticlePrice() {
            return lblArticlePrice;
        }

        public TextView getLblQuantity() {
            return lblQuantity;
        }

        public ImageView getImgArticleImage() {
            return imgArticleImage;
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

        Set<Article> orderSet =  orderList.keySet();

        int customCounter = 0;
        Article visibleArticle = null;
       Integer itemQuantity = null;
        for (Article  article : orderSet) {
            if (customCounter == position) {
                itemQuantity = orderList.get(article);
                visibleArticle = article;
                break;
            }
            customCounter ++;
        }

       double specifiqueCartListTotal = Calculate.calculHashMapTotal(orderList);


       holder.lblArticlePrice.setText( "UP :" + String.format("%.2f",visibleArticle.getPrix()) + " $CAD");
       holder.lblQuantity.setText("Quantity: " + itemQuantity.toString());
       holder.lblArticleTitle.setText(visibleArticle.getTitre());

       Glide.with(context).load(Uri.parse(visibleArticle.getImage())).into(holder.imgArticleImage);


    }



    @Override
    public int getItemCount() {
        return orderList.size();
    }
}