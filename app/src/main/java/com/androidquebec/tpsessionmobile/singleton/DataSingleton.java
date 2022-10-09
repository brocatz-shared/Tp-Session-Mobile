package com.androidquebec.tpsessionmobile.singleton;

public class DataSingleton {

    private  DataSingleton dataSingleton;

    public DataSingleton getInstance  () {
        if (dataSingleton == null) {
            dataSingleton = new DataSingleton();
        }
        return dataSingleton;

    }

}
