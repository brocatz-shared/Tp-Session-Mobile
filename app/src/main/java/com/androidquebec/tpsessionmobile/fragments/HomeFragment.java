package com.androidquebec.tpsessionmobile.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;
import com.androidquebec.tpsessionmobile.activity.MainActivity;
import com.androidquebec.tpsessionmobile.adapter.HomeItemAdapter;
import com.androidquebec.tpsessionmobile.adapter.ItemDecorator;
import com.androidquebec.tpsessionmobile.model.Article;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeActivity context;

    public HomeFragment() {}

    public HomeFragment(HomeActivity context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view =  inflater.inflate(R.layout.fragment_home,container,false);

      RecyclerView rv_home_page = view.findViewById(R.id.home_page_recycleView);
      ArrayList<Article> list = new ArrayList<Article>();

      Article article = new Article();

      article.setTitre("Title");
      article.setPrix(203);
      article.setDescription("sdsdsds");
      for (int i =0; i < 100 ; i ++) {
          list.add(new Article());
      }

      rv_home_page.setAdapter(new HomeItemAdapter(R.layout.item_vertical_home_page,this.context,list));
      rv_home_page.addItemDecoration(new ItemDecorator());
      return view;
    }


}
