package com.venom.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    String initialPercentage= "";
    String initialCurrency="";
    String currentCurrency="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Data.setSavePercentage(false);
        Data.setSaveCurrency(false);

        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        getSupportActionBar().setTitle("Tip Calculator - Settings");

        //checking the saved currency selection
        RadioButton rb ;
        switch (currentCurrency=initialCurrency=Data.getCurrency()){
            case "£":{
                rb = findViewById(R.id.poundRButton);
                rb.setChecked(true);
            }
            break;

            case "$":{
                rb = findViewById(R.id.dollarRButton);
                rb.setChecked(true);
            }
            break;

            case "€":{
                rb = findViewById(R.id.euroRButton);
                rb.setChecked(true);
            }
            break;
        }

        //filling out the saved percentage
        ((EditText)findViewById(R.id.percentageEdit)).setText(initialPercentage=(new Double(Data.getPercentage())).toString(), TextView.BufferType.EDITABLE);
        findViewById(R.id.saveButton).setOnClickListener(this);
        findViewById(R.id.cancelButton).setOnClickListener(this);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.euroRButton:
                if (checked){
                    currentCurrency="€";
                    //Data.setCurrency("€");
                    Log.i("Share","data fetched into onResume");
                }
                break;
            case R.id.dollarRButton:
                if (checked){
                    currentCurrency="$";
                    //Data.setCurrency("$");
                    Log.i("Share","data fetched into onResume");
                }
                break;
            case R.id.poundRButton:
                if (checked){
                    currentCurrency="£";
                    //Data.setCurrency("£");
                    Log.i("Share","data fetched into onResume");
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:{

                //check to see if percentage should be changed
                String laterPercentage = ((EditText)findViewById(R.id.percentageEdit)).getText().toString();
                if(laterPercentage.equals(initialPercentage)){
                    Data.setSavePercentage(false);
                } else {
                    Data.setPercentage(Double.parseDouble(((EditText)findViewById(R.id.percentageEdit)).getText().toString()));
                    Data.setSavePercentage(true);
                }

                // check to see if currency should be changed
                if(initialCurrency.equals(currentCurrency)){
                    Data.setSaveCurrency(false);
                } else {
                    Data.setCurrency(currentCurrency);
                    Data.setSaveCurrency(true);
                }
                finish();
            }
            break;

            case R.id.cancelButton:{
                Data.setSavePercentage(false);
                Data.setSaveCurrency(false);
                finish();
            }
            break;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }
}