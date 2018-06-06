package com.venom.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;

public class SuggestionActivity extends AppCompatActivity implements View.OnClickListener{

    //just to hide overflow menu from view
    double sRating;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        getSupportActionBar().setTitle("Tip Calculator - Suggest Tip");
        //getSupportActionBar().setTitle("Tip Calculator-Summary");

        RatingBar mBar = (RatingBar) findViewById(R.id.ratingBar);
        mBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                sRating=rating;
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.suggestButton:{

                Intent data = new Intent();
                data.putExtra("suggestedTip", "Based on your rating, we suggest a tip percentage of : "+new Integer(10+(((int)sRating)*2)).toString()+"%");
                setResult(RESULT_OK, data);
                finish();
                break;
            }
            case R.id.cancelButton:{
                setResult(RESULT_CANCELED,null);
                finish();
                break;
            }
        }
    }
}
