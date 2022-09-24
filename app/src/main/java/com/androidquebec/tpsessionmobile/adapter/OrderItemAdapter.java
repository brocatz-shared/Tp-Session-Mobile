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

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    int layoutId;
    Activity context;
    List<Article>  articleList;

    public OrderItemAdapter (int layoutId, Activity context, List<Article>  articleList) {
        this.layoutId = layoutId;
        this.context = context;
        this.articleList = articleList;
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder {

        public TextView txtArticleTitle, txtArticlePrice;
        public ImageView imgArticleImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtArticleTitle = itemView.findViewById(R.id.lblOrderItemTitle);
            txtArticlePrice = itemView.findViewById(R.id.lblOrderItemPrice);
            imgArticleImage = itemView.findViewById(R.id.img_orderPage_item);
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

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getTxtArticleTitle().setText("sdsdsd");
        holder.getImgArticleImage().setBackgroundResource(R.drawable.ic_order);

    }



    @Override
    public int getItemCount() {
        return 100;
    }
}
