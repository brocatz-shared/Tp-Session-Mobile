package com.androidquebec.tpsessionmobile.fragments;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;
import com.androidquebec.tpsessionmobile.model.Article;
import com.bumptech.glide.Glide;

public class DetailFragement extends Fragment {

    // TODO: Rename parameter arguments, choose names that match


    private HomeActivity context;
    private Article article;

    public DetailFragement(HomeActivity homeActivity, Article article) {
        // Required empty public constructor
        context = homeActivity;
        this.article = article;
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
        ImageView imgView = view.findViewById(R.id.imageViewDetail);


        lblDescription.setText(article.getDescription());
        lblPrice.setText(String.valueOf(article.getPrix()));
        lblTitre.setText(article.getTitre());

        Glide.with(context).load(Uri.parse(article.getImage())).into(imgView);


        return view;
    }
}