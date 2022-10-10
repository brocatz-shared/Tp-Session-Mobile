package com.androidquebec.tpsessionmobile.utile;

import android.util.Log;

import com.androidquebec.tpsessionmobile.model.Article;
import com.androidquebec.tpsessionmobile.model.RegistreArticle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JsonReaderHelper {

    public static String obtenirReponseHttp(String url) {

        //Obtenir une connection avec un thread
        URL location = null;
        final String[] result = new String[1];

        try {
            location = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.v("hafed", "erreur url");
        }
        //faire une connexion en utilisant un thread
        if (location != null) {
            Log.v("hafed", "Debut thread");
            final URL finalLocation = location;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    //Lire le contenu distant
                    BufferedReader in = null;
                    HttpURLConnection urlConn = null;

                    try {
                        urlConn = (HttpURLConnection) finalLocation.openConnection();
                        in = new BufferedReader(new InputStreamReader(
                                urlConn.getInputStream()));
                        //utiliser le in
                        result[0] = lireReponse(in);
                    } catch (IOException e) {
                        Log.v("hafed","Erreur connexion");
                    }finally{
                        if (in !=null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                    Log.v("hafed", "fin thread");
                }
            };
            thread.start();
            //attente avec un join
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result[0];
    }

    public static String lireReponse(BufferedReader in) {
        String line=null;
        StringBuilder sb= new StringBuilder();

        try {
            while (((line = in.readLine()) !=null))  {
                // Log.v("hafed", line);
                sb.append(line);
            };
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void handleJson(String string) {
        JSONObject jObject;
        JSONArray jsonArray = null;

        try {
            jsonArray =  new JSONArray(string);
          //  DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-ddThh:mm:ssZ");
           for (int i = 0; i < jsonArray.length() ; i++ ) {
               JSONObject object = jsonArray.getJSONObject(i);
               Article article = new Article();

               article.setId(object.getLong("id"));
               article.setPrix(object.getDouble("prix"));
               article.setDescription(object.getString("description"));
               article.setTitre(object.getString("titre"));
               //article.setImage(object.getString("image"));
               String uri ="https://raw.githubusercontent.com/brocatz-shared/Tp-Session-Mobile/master/app/productImages/";
               uri += object.getString("image");
               article.setImage(uri);

//               article.setDate(LocalDateTime.parse(object.getString("date"), df));

               RegistreArticle articleList = RegistreArticle.getRegistreArticleInstance();
               articleList.getSetListProduct().add(article);
           }
        } catch ( Exception e) {
            e.printStackTrace();
        }

        ArrayList<Article> companyList = RegistreArticle.getRegistreArticleInstance().getCompanyListProduct();
        companyList.addAll(RegistreArticle.getRegistreArticleInstance().getSetListProduct());
        //companyList = new ArrayList<>(RegistreArticle.getRegistreArticleInstance().getSetListProduct());

        Collections.sort(companyList, new Comparator<Article>() {
                public int compare(Article a1, Article a2) {
                    return a1.getTitre().compareToIgnoreCase(a2.getTitre());
                }

        });
    }
}
