package com.androidquebec.tpsessionmobile.adapter;

import android.app.Activity;
import android.net.Uri;
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
import com.androidquebec.tpsessionmobile.model.RegistreArticle;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class SearhItemAdapter extends RecyclerView.Adapter<SearhItemAdapter.ViewHolder> {

    int layoutId;
    HomeActivity context;
    List<Article> articleList;

    private static RecycleViewClickListener itemListener;


    public SearhItemAdapter(int layoutId, HomeActivity context, List<Article> articleList, RecycleViewClickListener itemListener) {
        this.layoutId = layoutId;
        this.context = context;
        this.articleList = articleList;
        this.itemListener = itemListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtArticleTitle, txtArticlePrice;
        public ImageView imgArticleImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtArticleTitle = itemView.findViewById(R.id.lblItemSearchItemTitle);
            txtArticlePrice = itemView.findViewById(R.id.lblItemSearchPrice);
            imgArticleImage = itemView.findViewById(R.id.img_searchImageArticle);

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

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view,this.getLayoutPosition());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);

        final  ViewHolder holder = new ViewHolder(view);

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                int position = getAd
//
//                ArrayList<Article> companyListProduct = RegistreArticle.getRegistreArticleInstance().getCompanyListProduct();
//
//               // DetailFragement detailFragement = new DetailFragement(context,companyListProduct.get(position));
//                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
//
//               // transaction.replace(R.id.home_fragment,detailFragement);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Article article = articleList.get(position);
        holder.getTxtArticleTitle().setText(article.getTitre());
        holder.getTxtArticlePrice().setText(String.valueOf(article.getPrix()));
        holder.getImgArticleImage().setBackgroundResource(R.drawable.ic_search);


        Glide.with(context).load(Uri.parse(article.getImage())).into(holder.imgArticleImage);




//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//
//            private  Article refArticle = article;
//            @Override
//            public void onClick(View view) {
//
//                DetailFragement detailFragement = new DetailFragement(context,refArticle);
//                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
//
//                transaction.replace(R.id.home_fragment,detailFragement);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });



    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }



}
