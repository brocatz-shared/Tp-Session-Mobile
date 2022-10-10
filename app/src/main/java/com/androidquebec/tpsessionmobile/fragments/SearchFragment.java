package com.androidquebec.tpsessionmobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;
import com.androidquebec.tpsessionmobile.adapter.RecycleViewClickListener;
import com.androidquebec.tpsessionmobile.adapter.SearhItemAdapter;
import com.androidquebec.tpsessionmobile.model.Article;
import com.androidquebec.tpsessionmobile.model.RegistreArticle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SearchFragment extends Fragment implements RecycleViewClickListener {


    HomeActivity context;

    public SearchFragment(HomeActivity homeActivity) {
        // Required empty public constructor
        this.context = homeActivity;
    }

    public SearchFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView rv_searchPage = view.findViewById(R.id.rv_searchPage);

        Set<Article> setArticle = RegistreArticle.getRegistreArticleInstance().getSetListProduct();
        List<Article> listArticle = new ArrayList<>(setArticle);

        rv_searchPage.setAdapter(new SearhItemAdapter(R.layout.item_search_page_cardview,context,listArticle,this));

        return view;
    }


    @Override
    public void recyclerViewListClicked(View v, int position) {


        ArrayList<Article> companyListProduct = RegistreArticle.getRegistreArticleInstance().getCompanyListProduct();

        Article article = companyListProduct.get(position);
        DetailFragement detailFragement = new DetailFragement(context,article);
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();

         transaction.replace(R.id.home_fragment,detailFragement);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}