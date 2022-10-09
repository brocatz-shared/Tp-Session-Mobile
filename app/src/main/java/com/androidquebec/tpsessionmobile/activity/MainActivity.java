package com.androidquebec.tpsessionmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.model.Article;
import com.androidquebec.tpsessionmobile.model.RegistreArticle;
import com.androidquebec.tpsessionmobile.utile.JsonReaderHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class MainActivity extends Activity {

    Button btnLogin;
    EditText txtEmail,txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        setWidgets();
        setListeners();
        setSingleton();
    }

    private void setSingleton() {
        RegistreArticle registreArticle = RegistreArticle.getRegistreArticleInstance();

        String data = JsonReaderHelper.obtenirReponseHttp("https://raw.githubusercontent.com/brocatz-shared/Tp-Session-Mobile/master/app/article.json");
        JsonReaderHelper.handleJson(data);

        // Make on http request and get the element

        }

    private void setListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                intent.putExtra("email",txtEmail.getText().toString());

                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void setWidgets() {

        btnLogin = findViewById(R.id.btnLogin);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
    }



}