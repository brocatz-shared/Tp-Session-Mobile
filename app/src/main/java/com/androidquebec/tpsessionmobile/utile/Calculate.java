package com.androidquebec.tpsessionmobile.utile;

import com.androidquebec.tpsessionmobile.model.Article;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Calculate {
    public static double calculHashMapTotal (LinkedHashMap<Article,Integer> set) {
        Iterator iterator = set.entrySet().iterator();

        double total = 0;

        while (iterator.hasNext()) {
            Map.Entry<Article,Integer> entry = (Map.Entry<Article, Integer>) iterator.next();

            Article article = entry.getKey();
            Integer nbArticle = entry.getValue();
            total += article.getPrix() * nbArticle.intValue();
        }


//        BigDecimal bd = new BigDecimal(total);
//        bd = bd.setScale(2, RoundingMode.HALF_UP);
//        total = bd.doubleValue();

        return total;
    }
}
