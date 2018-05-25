package com.venom.tipcalculator;

import android.app.Application;

public class Data extends Application {

    //getters:
    //((Data) this.getApplication()).get%VariableName%();

    //setters:
    //((Data) this.getApplication()).set%VariableName%(%VariableName%);

    public static double percentage;
    public static String currency=""; //  $  or  €  or  £
    public static int numberOfPpl;

    public static void setPercentage(double percentage){
        Data.percentage=percentage;
    }

    public static void setCurreny(String currency){
        Data.currency=currency;
    }

    public static double getPercentage(){
        return percentage;
    }

    public static String getCurreny(){
        return currency;
    }

    public static void setNumberOfPpl(int numberOfPpl){ Data.numberOfPpl=numberOfPpl;}

    public static int getNumberOfPpl(){return Data.numberOfPpl;}

}
