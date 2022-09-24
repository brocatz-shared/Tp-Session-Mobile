package com.androidquebec.tpsessionmobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.fragments.HomeFragment;
import com.androidquebec.tpsessionmobile.fragments.OrderFragment;
import com.androidquebec.tpsessionmobile.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {


    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setWidgets();
        setListeners();
    }

    private void setListeners() {
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.home_page:
                        loadFragment(new HomeFragment(HomeActivity.this), 0 );
                        return true;
                    case R.id.search_page:
                        loadFragment(new SearchFragment(HomeActivity.this), 0 );
                        return true;

                    case R.id.order_page:
                        loadFragment(new OrderFragment(HomeActivity.this), 0 );
                        return true;
                }
                return false;
            }
        });

    }

    private void setWidgets() {
        loadFragment(new HomeFragment(this), 0 );

        navigationView = (BottomNavigationView)  findViewById(R.id.bottom_navigation);

    }



    private void loadFragment (Fragment fragment, int string) {
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

       transaction.replace(R.id.home_fragment,fragment);
       transaction.addToBackStack(null);
       transaction.commit();
    }
}