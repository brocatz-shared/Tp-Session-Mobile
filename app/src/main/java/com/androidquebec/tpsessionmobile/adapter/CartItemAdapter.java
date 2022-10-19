package com.androidquebec.tpsessionmobile.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.CartActivity;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;
import com.androidquebec.tpsessionmobile.model.Article;
import com.bumptech.glide.Glide;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    int layoutId;
    CartActivity context;
    LinkedHashMap<Article,Integer> cartList;

    private static RecyclerViewButtonAddClickListener recyclerViewButtonAddClickListener;
    private static RecyclerViewButtonRemoveClickListener recyclerViewButtonRemoveClickListener;


    public CartItemAdapter(int layoutId, CartActivity context,  LinkedHashMap<Article,Integer> cartList , RecyclerViewButtonAddClickListener recyclerViewButtonAddClickListener, RecyclerViewButtonRemoveClickListener recyclerViewButtonRemoveClickListener ) {
        this.layoutId = layoutId;
        this.context = context;
        this.cartList = cartList;
        this.recyclerViewButtonAddClickListener = recyclerViewButtonAddClickListener;
        this.recyclerViewButtonRemoveClickListener = recyclerViewButtonRemoveClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView lblArticleTitle, lblArticlePrice, lblItemCount;
        public ImageView imgArticleImage;
        public Button btnAddOneToCart, btnRemoveOneFromCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lblArticleTitle = itemView.findViewById(R.id.cartItemTitle);
            lblItemCount = itemView.findViewById(R.id.cartSpecifiqueItemCOunt);
            lblArticlePrice = itemView.findViewById(R.id.cartItemPrice);
            imgArticleImage = itemView.findViewById(R.id.cartImageView);
            btnAddOneToCart = itemView.findViewById(R.id.btnAddOneToCart);
            btnRemoveOneFromCart = itemView.findViewById(R.id.btnRemoveOneFromCart);

            // btnAddToCart.setOnClickListener(this);
            btnAddOneToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewButtonAddClickListener.recyclerViewButtonAddClickListener(view,getAdapterPosition());
                }
            });
            btnRemoveOneFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewButtonRemoveClickListener.recyclerViewButtonRemoveClickListener(view,getAdapterPosition());
                }
            });
          // itemView.setOnClickListener(this);
        }

        public TextView getLblArticleTitle() {
            return lblArticleTitle;
        }

        public TextView getLblArticlePrice() {
            return lblArticlePrice;
        }

        public TextView getLblItemCount() {
            return lblItemCount;
        }

        public Button getBtnAddOneToCart() {
            return btnAddOneToCart;
        }

        public Button getBtnRemoveOneFromCart() {
            return btnRemoveOneFromCart;
        }

        public ImageView getImgArticleImage() {
            return imgArticleImage;
        }

        @Override
        public void onClick(View view) {
            //itemListener.recyclerViewListClicked(view,this.getLayoutPosition());
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

        //Article article = cartList.get(position);

        // Loop througth the LinkedHashMap

        Set<Article> listArticleInMap = cartList.keySet();
        Article articleToSetForView = null;

        int customCounter = 0;
        for (Article article : listArticleInMap) {
            if (customCounter == position) {
                articleToSetForView = article;
                break;
            }
            customCounter ++;
        }

        int numberOfSpecifiqueArticle = cartList.get(articleToSetForView);

        holder.getLblArticlePrice().setText("Price :" + articleToSetForView.getPrix());
        holder.getLblArticleTitle().setText(articleToSetForView.getTitre());
        holder.getLblItemCount().setText(String.valueOf(numberOfSpecifiqueArticle));

        Glide.with(context).load(Uri.parse(articleToSetForView.getImage())).into(holder.imgArticleImage);




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
        return cartList.size();
    }



}
