package com.venom.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    String initialPercentage= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Data.setSavePercentage(false);

        //checking the saved currency selection
        RadioButton rb ;
        switch (Data.getCurrency()){
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

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.euroRButton:
                if (checked){
                    Data.setCurrency("€");
                    Log.i("Share","data fetched into onResume");
                }
                break;
            case R.id.dollarRButton:
                if (checked){
                    Data.setCurrency("$");
                    Log.i("Share","data fetched into onResume");
                }
                break;
            case R.id.poundRButton:
                if (checked){
                    Data.setCurrency("£");
                    Log.i("Share","data fetched into onResume");
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:{
                String laterPercentage = ((EditText)findViewById(R.id.percentageEdit)).getText().toString();
                if(laterPercentage.equals(initialPercentage)){
                    Data.setSavePercentage(false);
                } else {
                    Data.setPercentage(Double.parseDouble(((EditText)findViewById(R.id.percentageEdit)).getText().toString()));
                    Data.setSavePercentage(true);
                }
                finish();
            }
            break;

            case R.id.cancelButton:{
                Data.setSavePercentage(false);
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
