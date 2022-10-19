package com.androidquebec.tpsessionmobile.fragments;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;
import com.androidquebec.tpsessionmobile.adapter.ButtonAddToCartClickListener;
import com.androidquebec.tpsessionmobile.model.Article;
import com.androidquebec.tpsessionmobile.model.RegistreArticle;
import com.bumptech.glide.Glide;

import java.util.LinkedHashMap;

public class DetailFragement extends Fragment {

    // TODO: Rename parameter arguments, choose names that match


    private HomeActivity context;
    private Article article;

    public static ButtonAddToCartClickListener buttonAddToCartClickListener;


    public DetailFragement(HomeActivity homeActivity, Article article) {
        // Required empty public constructor
        context = homeActivity;
        this.article = article;
        buttonAddToCartClickListener = homeActivity;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);

        TextView lblTitre = view.findViewById(R.id.lblDetailTitle);
        TextView lblDescription = view.findViewById(R.id.lblDetailDescription);
        TextView lblPrice = view.findViewById(R.id.lblDetailPrice);
        EditText txtDetailQuantity = view.findViewById(R.id.txtDetailQunatity);
        ImageView imgView = view.findViewById(R.id.imageViewDetail);
        Button  btnDetailAddTo = view.findViewById(R.id.btnDetailAddTo);


        lblDescription.setText(article.getDescription());
        lblPrice.setText(String.valueOf(article.getPrix()));
        lblTitre.setText(article.getTitre());

        Glide.with(context).load(Uri.parse(article.getImage())).into(imgView);

        btnDetailAddTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int quantite = Integer.parseInt(txtDetailQuantity.getText().toString());

                    if (quantite <= 0)
                        return;

                    // Increment the cart
                    LinkedHashMap<Article,Integer> cart = RegistreArticle.getRegistreArticleInstance().getCartListProducts();
                    cart.merge(article,quantite,Integer::sum);

                    // CallBack to update the icon
                    buttonAddToCartClickListener.buttonClickOnSearchFragment();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}