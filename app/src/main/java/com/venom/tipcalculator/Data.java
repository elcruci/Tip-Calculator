package com.venom.tipcalculator;

import android.app.Application;

public class Data {

    //getters:
    //((Data) this.getApplication()).get%VariableName%();

    //setters:
    //((Data) this.getApplication()).set%VariableName%(%VariableName%);

    private static double percentage=10.0;
    private static String currency="$"; //  $  or  €  or  £
    private static boolean savePercentage=true;
    private static boolean saveCurrency=true;


    public static void setPercentage(double percentage){
        Data.percentage=percentage;
    }

    public static void setCurrency(String currency){
        Data.currency=currency;
    }

    public static void setSavePercentage(boolean choice){ savePercentage=choice;}

    public static void setSaveCurrency(boolean choice){saveCurrency=choice;}



    public static double getPercentage(){
        return percentage;
    }

    public static String getCurrency(){
        return currency;
    }

    public static boolean savePercentage(){return savePercentage;}

    public static boolean saveCurrency(){return saveCurrency;}


}
