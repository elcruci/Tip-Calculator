package com.venom.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        ((TextView)findViewById(R.id.bill)).setText("Bill : "+currency+bill);
        ((TextView)findViewById(R.id.tip)).setText("Tip : "+currency+tipValue);
        ((TextView)findViewById(R.id.total)).setText("Total : "+currency+total);
        ((TextView)findViewById(R.id.tipPerPerson)).setText("Tip/Person : "+currency+tipPerPerson);
        ((TextView)findViewById(R.id.totalPerPerson)).setText("Total/Person : "+currency+totalPerPerson);
    }



}
