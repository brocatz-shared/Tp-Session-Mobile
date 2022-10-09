package com.androidquebec.tpsessionmobile.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;
import com.androidquebec.tpsessionmobile.fragments.DetailFragement;
import com.androidquebec.tpsessionmobile.model.Article;

import java.util.List;


public class SearhItemAdapter extends RecyclerView.Adapter<SearhItemAdapter.ViewHolder> {

    int layoutId;
    HomeActivity context;
    List<Article> articleList;

    public SearhItemAdapter(int layoutId, HomeActivity context, List<Article> articleList) {
        this.layoutId = layoutId;
        this.context = context;
        this.articleList = articleList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtArticleTitle, txtArticlePrice;
        public ImageView imgArticleImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtArticleTitle = itemView.findViewById(R.id.lblItemSearchItemTitle);
           // txtArticlePrice = itemView.findViewById(R.id.img_searchImageArticle);
            imgArticleImage = itemView.findViewById(R.id.img_searchImageArticle);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getTxtArticleTitle().setText("sdsdsd");
        holder.getImgArticleImage().setBackgroundResource(R.drawable.ic_search);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
                DetailFragement detailFragement = new DetailFragement(context);
                FragmentTransaction transaction = appCompatActivity.getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.home_fragment,detailFragement);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



    }


    @Override
    public int getItemCount() {
        return 100;
    }



}
