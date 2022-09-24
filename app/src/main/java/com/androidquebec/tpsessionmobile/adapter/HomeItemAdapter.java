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

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> {

        int layoutId;
        Activity context;
        List<Article> articleList;

   public HomeItemAdapter (int layoutId, Activity context, List<Article>  articleList) {
        this.layoutId = layoutId;
        this.context = context;
        this.articleList = articleList;
        }

    public static   class ViewHolder  extends RecyclerView.ViewHolder {


    public ImageView imgHomeImage;
    public TextView lblSubCategory;

    public  ViewHolder(@NonNull View itemView) {
        super(itemView);

        imgHomeImage = itemView.findViewById(R.id.img_home_cardView);
        lblSubCategory = itemView.findViewById(R.id.lblSubCategoryId);
    }



    public ImageView getImgArticleImage() {
        return imgHomeImage;
    }

    public TextView getLblSubCategory() {
        return lblSubCategory;
    }
}

    @NonNull
    @Override
    public HomeItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getLblSubCategory().setText("sdsdsd");
        holder.getImgArticleImage().setBackgroundResource(R.drawable.ic_order);


    }



    @Override
    public int getItemCount() {
        return 100;
    }
}
