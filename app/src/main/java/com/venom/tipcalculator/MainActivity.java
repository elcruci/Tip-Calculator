package com.venom.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
//import com.venom.tipcalculator.R;

import java.lang.reflect.Method;
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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar, menu);
        if(menu.getClass().getSimpleName().equals("MenuBuilder")){
            try{
                Method m = menu.getClass().getDeclaredMethod(
                        "setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            }
            catch(NoSuchMethodException e){
                //
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:{
               // start settings activity
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.suggest:{
                // start settings activity
                Intent intent = new Intent(this, SuggestionActivity.class);
                startActivity(intent);
                return true;
            }
            default:
        }
        return false;
    }
}
