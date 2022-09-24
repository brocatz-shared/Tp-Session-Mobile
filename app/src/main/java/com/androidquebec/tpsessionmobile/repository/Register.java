package com.androidquebec.tpsessionmobile.repository;

public class Register {



    private static Register register;
    private Register () {
        // Make http Request And Get the Data from the server;
    }

    public static Register getInstance() {
        if (register == null) {
            return new Register();
        }
        return register;
    }


}
