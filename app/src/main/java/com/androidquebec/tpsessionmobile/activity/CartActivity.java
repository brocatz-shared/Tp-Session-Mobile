package com.androidquebec.tpsessionmobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.adapter.CartItemAdapter;
import com.androidquebec.tpsessionmobile.adapter.ItemDecorator;
import com.androidquebec.tpsessionmobile.adapter.RecycleViewClickListener;
import com.androidquebec.tpsessionmobile.adapter.RecyclerViewButtonAddClickListener;
import com.androidquebec.tpsessionmobile.adapter.RecyclerViewButtonRemoveClickListener;
import com.androidquebec.tpsessionmobile.model.Article;
import com.androidquebec.tpsessionmobile.model.Order;
import com.androidquebec.tpsessionmobile.model.RegistreArticle;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.LinkedHashMap;
import java.util.Set;

public class CartActivity extends AppCompatActivity implements RecyclerViewButtonAddClickListener, RecyclerViewButtonRemoveClickListener {


    private MaterialToolbar materialToolbar;
    private RecyclerView cartRecyclerView;
    private CartItemAdapter cartItemAdapter;
    private TextView lblCartTotalItemPrice;
    private Button btnBuy;

    private AlertDialog.Builder builder;
    private AlertDialog.Builder sucessAlertDiagBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setWigets();
        setListerner();

        calculateCartTotal();
        setPurchaseConfirmationDialog();
    }

    private void calculateCartTotal() {
       double cartTotal = RegistreArticle.getRegistreArticleInstance().getCartTotal();
       String cartTotalFormat = String.format("%.2f", cartTotal);
       lblCartTotalItemPrice.setText("Total : " + cartTotalFormat+ "$");

    }

    private void setListerner() {

        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED,returnIntent);
                CartActivity.this.finish();
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get cartList
                LinkedHashMap<Article,Integer> cartListProducts  = RegistreArticle.getRegistreArticleInstance().getCartListProducts();

                if (cartListProducts.size()  == 0) {
                    builder.setMessage("There is nothing in the cart to buy");
                    builder.setTitle("Cart Empty");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                } else {

                    double cartTotal = RegistreArticle.getRegistreArticleInstance().getCartTotal();
                    String cartTptalString = String.format("%.2f", cartTotal);
                    builder.setMessage("Are you sure you want to buy for " + cartTptalString + "$ CAD" );
                    builder.setTitle("Confirm Purchase");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Create the order
                            RegistreArticle.getRegistreArticleInstance().createOrder();
                            sucessAlertDiagBuilder.show();
                        }
                    });

                    builder.setNegativeButton("No", null);
                    builder.show();
                }

            }
        });
    }

    private void setWigets() {

        materialToolbar = findViewById(R.id.topAppBarCart);
        cartRecyclerView = findViewById(R.id.cartRecycleView);
        lblCartTotalItemPrice = findViewById(R.id.cartTotalItemPrice);
        btnBuy = findViewById(R.id.btnBuy);
        builder = new AlertDialog.Builder(CartActivity.this);

        LinkedHashMap<Article,Integer> cartList = RegistreArticle.getRegistreArticleInstance().getCartListProducts();

        cartItemAdapter = new CartItemAdapter(R.layout.item_vertical_cart_item2,this,cartList,this,this);

        //cartRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        cartRecyclerView.addItemDecoration(new ItemDecorator());
        cartRecyclerView.setAdapter(cartItemAdapter);

        new ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(cartRecyclerView);
    }

    private void setPurchaseConfirmationDialog () {
        sucessAlertDiagBuilder = new AlertDialog.Builder(CartActivity.this);
        sucessAlertDiagBuilder.setTitle("Sucess");
        sucessAlertDiagBuilder.setMessage("You have purchase new items check order page");
        sucessAlertDiagBuilder.setCancelable(false);
        sucessAlertDiagBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

    }


    ItemTouchHelper.SimpleCallback simpleItemTouchCallback= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int postion = viewHolder.getAdapterPosition();

            LinkedHashMap<Article,Integer> cartList = RegistreArticle.getRegistreArticleInstance().getCartListProducts();

            Set<Article> setArticle = cartList.keySet();

            Article selectedArticleToDelete = null;
            int index = 0;
            for (Article article: setArticle) {
                if (index == postion) {
                    selectedArticleToDelete = article;
                }
                index ++;
            }

            cartList.remove(selectedArticleToDelete);
            cartItemAdapter.notifyDataSetChanged();
            calculateCartTotal();
        }
    };


    @Override
    public void recyclerViewButtonAddClickListener(View v, int position) {
        incrementOrDecrementCartItem(v,position,1);
    }

    @Override
    public void recyclerViewButtonRemoveClickListener(View v, int position) {
        incrementOrDecrementCartItem(v,position,-1);
    }


    public void incrementOrDecrementCartItem(View v, int position, int valueToIncrement) {
        LinkedHashMap<Article,Integer> cartList = RegistreArticle.getRegistreArticleInstance().getCartListProducts();

        Set<Article> articlesKeySet = cartList.keySet();

        int customCounter = 0;
        Article selectedArticle = null;
        Integer itemQuantity = null;
        for(Article  article : articlesKeySet) {
            if (customCounter == position) {
                selectedArticle = article;
                itemQuantity = cartList.get(article);
                break;
            }

            customCounter ++;
        }

        // Update the cart
        Integer quantityPlus = new Integer(itemQuantity.intValue() + valueToIncrement);

        if (quantityPlus > 0) {
            cartList.put(selectedArticle,quantityPlus);
            cartItemAdapter.notifyDataSetChanged();
            calculateCartTotal();
        }
    }
}