package com.androidquebec.tpsessionmobile.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.adapter.ButtonAddToCartClickListener;
import com.androidquebec.tpsessionmobile.fragments.HomeFragment;
import com.androidquebec.tpsessionmobile.fragments.OrderFragment;
import com.androidquebec.tpsessionmobile.fragments.SearchFragment;
import com.androidquebec.tpsessionmobile.model.Article;
import com.androidquebec.tpsessionmobile.model.RegistreArticle;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.LinkedHashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity  implements ButtonAddToCartClickListener {


    private BottomNavigationView navigationView;
    private MaterialToolbar materialToolbar;
    private TextView txtAppBarTitle, txtCartCounter;
    private ImageView cartImageView;

    private int CART_ACTIVITY_LAUNCH_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_home);

        setContentView(R.layout.activity_home2);

        setWidgets();
        setListeners();
    }

//    private void setStartActivityForResultCallback() {
//        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == Activity.RESULT_OK) {
//                            // There are no request codes
//                            Intent data = result.getData();
//                            doSomeOperations();
//                        }
//                    }
//                });
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar,menu);
        MenuItem item = menu.findItem(R.id.cart);
        MenuItemCompat.setActionView(item,R.layout.cartmenulayout);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);

        txtCartCounter = (TextView) notifCount.findViewById(R.id.actionbar_notifcation_textview);
        cartImageView = (ImageView) notifCount.findViewById(R.id.imageViewGoToCart) ;

        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });

        //tv.setText("12");
        txtCartCounter.setVisibility(View.INVISIBLE);
        return super.onCreateOptionsMenu(menu);
    }

    private void setListeners() {
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.home_page:
                        loadFragment(new HomeFragment(HomeActivity.this), 0 );
                        materialToolbar.setTitle("Home");
                        return true;
                    case R.id.search_page:
                        loadFragment(new SearchFragment(HomeActivity.this), 0 );
                        materialToolbar.setTitle("Search");
                        return true;

                    case R.id.order_page:
                        loadFragment(new OrderFragment(HomeActivity.this), 0 );
                        materialToolbar.setTitle("Order");
                        return true;
                }
                return false;
            }
        });

        // Set Cart Listener
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.cart) {
                    Intent intent = new Intent(HomeActivity.this,CartActivity.class);


                    startActivityForResult(intent, CART_ACTIVITY_LAUNCH_CODE);

                    return true;
                }
                return false;
            }
        });

    }

    private void setWidgets() {
        loadFragment(new HomeFragment(this), 0 );

        navigationView = (BottomNavigationView)  findViewById(R.id.bottom_navigation);
        materialToolbar = (MaterialToolbar) findViewById(R.id.topAppBar);
//        txtAppBarTitle = (TextView) findViewById(R.id.home_activity_title);

//
      setSupportActionBar(materialToolbar);
//        txtAppBarTitle.setText(materialToolbar.getTitle());
//
//        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }



    private void loadFragment (Fragment fragment, int string) {
       FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
       transaction.replace(R.id.home_fragment,fragment);
//       transaction.addToBackStack(null);
       transaction.commit();
    }


    @Override
    public void buttonClickOnSearchFragment() {
        // Update the cart Icon
        LinkedHashMap<Article,Integer> cartList = RegistreArticle.getRegistreArticleInstance().getCartListProducts();

        int totalNumberOfItem = 0;
        for (Map.Entry<Article,Integer> entrySet : cartList.entrySet()) {
            totalNumberOfItem += entrySet.getValue();

        }

        if (totalNumberOfItem != 0) {
            txtCartCounter.setVisibility(View.VISIBLE);
            txtCartCounter.setText(String.valueOf(totalNumberOfItem));
        } else {
            txtCartCounter.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (txtCartCounter != null) {
            this.buttonClickOnSearchFragment();
        }

    }

    //  it
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == CART_ACTIVITY_LAUNCH_CODE) {
//            this.buttonClickOnSearchFragment();
//        }
//    } //onActiv


//    public void openSomeActivityForResult() {
//        Intent intent = new Intent(this, CartActivity.class);
//        someActivityResultLauncher.launch(intent);
//    }
}