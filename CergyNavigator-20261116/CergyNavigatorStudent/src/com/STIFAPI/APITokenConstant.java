package com.STIFAPI;

public class APITokenConstant {
    private static final String token = "KsKvzAYN6gubRk9enrs2WXy6yPjbAIL9";
    private static final String url = "https://prim.iledefrance-mobilites.fr/marketplace/stop-monitoring";
    //private static final String cergyCode = "411352";
    private static final String cergyCode = "44559";
    static String getToken(){
        return token;
    }
    static String getUrl(){return url;}

    public static String getCergyCode() {
        return cergyCode;
    }
}
