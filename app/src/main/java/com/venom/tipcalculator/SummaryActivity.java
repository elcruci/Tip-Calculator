package com.venom.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SummaryActivity extends AppCompatActivity{

    //just to hide overflow menu from view
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        getSupportActionBar().setTitle("Tip Calculator - Summary");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        //First field:
        double bill = bundle.getDouble("bill");


        double tipPercentage = bundle.getDouble("tip");
        int numPpl = bundle.getInt("number of people");
        String currency = bundle.getString("currency");

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        //Second field:
        double tipValue = new Double(df.format(bill*(tipPercentage/100))).doubleValue();

        //Third field:
        double total = new Double(df.format(bill+tipValue)).doubleValue();

        //Fourth field:
        double tipPerPerson=new Double(df.format(tipValue/numPpl)).doubleValue();

        //Fifth field:
        double totalPerPerson=new Double(df.format(total/numPpl)).doubleValue();

        SpannableString ss1=  new SpannableString("Bill : "+currency+bill);
        ss1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0,6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString ss2=  new SpannableString("Tip : "+currency+tipValue);
        ss2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString ss3=  new SpannableString("Total : "+currency+total);
        ss3.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0,7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString ss4=  new SpannableString("Tip/Person : "+currency+tipPerPerson);
        ss4.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0,12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString ss5=  new SpannableString("Total/Person : "+currency+totalPerPerson);
        ss5.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0,14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        ((TextView)findViewById(R.id.bill)).setText(ss1);
        ((TextView)findViewById(R.id.tip)).setText(ss2);
        ((TextView)findViewById(R.id.total)).setText(ss3);
        ((TextView)findViewById(R.id.tipPerPerson)).setText(ss4);
        ((TextView)findViewById(R.id.totalPerPerson)).setText(ss5);
    }



}
