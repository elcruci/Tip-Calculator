package com.venom.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
//import com.venom.tipcalculator.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner numberOfPPL1 = (Spinner) findViewById(R.id.numberOfPplSpinner);
        ArrayList<String> list  = new ArrayList<String>();
            list.add("1 Person");
        for (int i = 2; i<11; i++){
            list.add(Integer.toString(i)+" People");
        }
        ArrayAdapter<CharSequence> numberOfPPL = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                list);
        numberOfPPL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberOfPPL1.setAdapter(numberOfPPL);
    }
}
