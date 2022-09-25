package com.androidquebec.tpsessionmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.androidquebec.tpsessionmobile.R;
import com.google.android.material.appbar.MaterialToolbar;

public class CartActivity extends AppCompatActivity {


    private MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        setWigets();
        setListerner();
    }

    private void setListerner() {

        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartActivity.this.finish();
            }
        });
    }

    private void setWigets() {

        materialToolbar = findViewById(R.id.topAppBarCart);
    }
}