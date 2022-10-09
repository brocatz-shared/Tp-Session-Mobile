package com.androidquebec.tpsessionmobile.model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

// Singleton
public class RegistreArticle {

    private HashSet<Article> setListProduct; // la liste au debut
    private ArrayList<Article> clientlistProduct; // Une liste ordonne
    private ArrayList<Article> companyListProduct; // Une Ordoneee;;
    private LinkedHashMap<Article,Integer> cartListProducts;
    private LinkedHashMap<Order,LinkedHashMap<Article,Integer>> orderListElement;

    private Map.Entry<Order, LinkedHashMap<Article,Integer>> selectedEntryOrder;
    private Article editArticleRef;

    private int cartItemNumber = 0;


    private static RegistreArticle registreArticle;
    public static RegistreArticle getRegistreArticleInstance() {
        if (registreArticle == null) {
            registreArticle = new RegistreArticle();
        }

        return registreArticle;
    }

    private  RegistreArticle () {
        setListProduct = new HashSet<Article>();
        clientlistProduct = new ArrayList<Article>();
        cartListProducts = new LinkedHashMap<>();
        orderListElement = new LinkedHashMap<>();
        companyListProduct = new ArrayList<Article>();
    }

    public HashSet<Article> getSetListProduct() {
        return setListProduct;
    }
    public ArrayList<Article> getClientlistProduct() {
        return clientlistProduct;
    }
    public ArrayList<Article> getCompanyListProduct() { return companyListProduct; }
    public LinkedHashMap<Article,Integer> getCartListProducts() { return cartListProducts;}
    public LinkedHashMap<Order, LinkedHashMap<Article, Integer>> getOrderListElement() {
        return orderListElement;
    }

    public Map.Entry<Order, LinkedHashMap<Article, Integer>> getSelectedEntryOrder() {
        return selectedEntryOrder;
    }

    public void setSelectedEntryOrder(Map.Entry<Order, LinkedHashMap<Article, Integer>> selectedEntryOrder) {
        this.selectedEntryOrder = selectedEntryOrder;
    }

    public Article getEditArticleRef() {
        return editArticleRef;
    }

    public void setEditArticleRef(Article editArticleRef) {
        this.editArticleRef = editArticleRef;
    }

    public void ajouterArticle (Article article) {
        setListProduct.add(article);
    }

    public void clearCartList () {
        cartListProducts.clear();
    }

    public double  getCartTotal () {
        Iterator iterator = cartListProducts.entrySet().iterator();

        double total = 0;

        while (iterator.hasNext()) {
            Map.Entry<Article,Integer> entry = (Map.Entry<Article, Integer>) iterator.next();

            Article article = entry.getKey();
            Integer nbArticle = entry.getValue();
            total += article.getPrix() * nbArticle.intValue();
        }


        BigDecimal bd = new BigDecimal(total);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        total = bd.doubleValue();

        return total;
    }

    public void createOrder() {
        orderListElement.putIfAbsent(new Order(Order.generateOrderNumber(),new Date()),cartListProducts);
        cartListProducts = new LinkedHashMap<Article,Integer>();
    }

    // currentElementTrigger =
    // public static final String TXT_SEARCH , , ...etc


    public int getCartItemNumber() {
        return cartItemNumber;
    }

    public void incrementCartItemNumber() {
        this.cartItemNumber ++;
    }

    public  void clearCartCounter() {
        cartItemNumber = 0;
    }
}
