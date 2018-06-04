package com.venom.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;

public class SuggestionActivity extends AppCompatActivity implements View.OnClickListener{

    //just to hide overflow menu from view
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        RatingBar mBar = (RatingBar) findViewById(R.id.ratingBar);
        mBar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RatingBar bar = (RatingBar) v;
                //bar.getRating()
            }
        });
        //getSupportActionBar().setTitle("Tip Calculator-Summary");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.calcButton:{
                break;
            }
        }
    }
}
