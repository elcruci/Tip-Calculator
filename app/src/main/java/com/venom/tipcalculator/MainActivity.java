package com.venom.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import com.venom.tipcalculator.R;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int SUGGEST_CODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
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
        */
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        findViewById(R.id.calcButton).setOnClickListener(this);
    }

    @Override
    public void onResume(){
        Log.i("Resume","successfully in the onResume method");
        super.onResume();

        if(Data.savePercentage()){
            ((EditText)findViewById(R.id.tipEdit)).setText((new Double(Data.getPercentage())).toString(),TextView.BufferType.EDITABLE);
            Data.setSavePercentage(false);
        }
        if(Data.saveCurrency()){
            ((TextView)findViewById(R.id.billField)).setText("Bill ("+(Data.getCurrency()+") : "));
            Data.setSaveCurrency(false);
        }
        Log.i("Share","data fetched into onResume");
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
                startActivityForResult(intent,SUGGEST_CODE);
                return true;
            }
            default:
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == SUGGEST_CODE) {
            if (data.hasExtra("suggestedTip")) {
                Toast.makeText(this, data.getExtras().getString("suggestedTip"),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.calcButton:{
                EditText billEdit = (EditText) findViewById(R.id.billEdit);
                EditText tipEdit = (EditText) findViewById(R.id.tipEdit);
                EditText pplEdit = (EditText) findViewById(R.id.numberOfPplEdit);

                String billString = billEdit.getText().toString();
                String tipString = tipEdit.getText().toString();
                String pplString = pplEdit.getText().toString();


                //non empty fields check
                if (billString.equals("")){
                    billEdit.requestFocus();
                    billEdit.setError("Please fill out.");
                    break;
                }
                if (tipString.equals("")){
                    tipEdit.requestFocus();
                    tipEdit.setError("Please fill out.");
                    break;
                }
                if (pplString.equals("")){
                    pplEdit.requestFocus();
                    pplEdit.setError("Please fill out.");
                    break;
                }
                // non-empty fields after this point :)


                //number check
                try{
                    Double.parseDouble(billString);
                } catch (NumberFormatException numEX) {
                    //Toast.makeText(getApplicationContext(), "Only numbers (no spaces) allowed in the fields above", Toast.LENGTH_LONG).show();
                    billEdit.requestFocus();
                    billEdit.setError("Only Numbers (No special characters or spaces) allowed in the Bill field.");
                    break;
                }

                try{
                    Double.parseDouble(tipString);
                } catch (NumberFormatException numEX) {
                    //Toast.makeText(getApplicationContext(), "Only numbers (no spaces) allowed in the fields above", Toast.LENGTH_LONG).show();
                    tipEdit.requestFocus();
                    tipEdit.setError("Only Numbers (No special characters or spaces) allowed in the Tip Percentage field.");
                    break;
                }

                try{
                    Integer.parseInt(pplString);
                } catch (NumberFormatException numEX) {
                    //Toast.makeText(getApplicationContext(), "Only Integers (no special characters) allowed in number of people field", Toast.LENGTH_LONG).show();
                    pplEdit.requestFocus();
                    pplEdit.setError("Only Integers (No decimals or special characters or spaces) allowed in Number of People field.");
                    break;
                }
                // only numbers after this point :)


                //positive number and boundary check
                try{
                    if (Double.parseDouble(billString) <= 0.0) throw new IllegalArgumentException("Bill field");
                    if (Double.parseDouble(tipString)< 0.0 || Double.parseDouble(tipString)>100.0) throw new IllegalArgumentException("Tip field");
                    if (Integer.parseInt(pplString)< 1 || Integer.parseInt(pplString)>99) throw new IllegalArgumentException("#ppl");
                } catch (IllegalArgumentException argEX) {
                    if(argEX.getMessage().equals("#ppl")){
                        //Toast.makeText(getApplicationContext(), "Invalid number of people (Shouldn't be more than 99)", Toast.LENGTH_LONG);
                        pplEdit.requestFocus();
                        pplEdit.setError("Invalid number of people (Should be between 1 and 99).");
                        break;
                    } else if (argEX.getMessage().equals("Tip field")) {
                        //Toast.makeText(getApplicationContext(), "Percentage should be between 1 and 100", Toast.LENGTH_LONG);
                        tipEdit.requestFocus();
                        tipEdit.setError("Percentage should be between 0.1 and 100");
                        break;
                    } else if (argEX.getMessage().equals("Bill field")){
                        //Toast.makeText(getApplicationContext(), "Amount Due is invalid", Toast.LENGTH_LONG);
                        billEdit.requestFocus();
                        billEdit.setError("Amount Due should be greater than 0");
                        break;
                    }
                }
                // only positive numbers and proper bounds after this point :)

                //safe to perform operations after this point :)

                double finalBill = Double.parseDouble(billString);
                double finalTip = Double.parseDouble(tipString);
                int finalNumPpl = Integer.parseInt(pplString);

                Intent intent = new Intent(this, SummaryActivity.class);
                intent.putExtra("bill",finalBill);
                intent.putExtra("tip",finalTip);
                intent.putExtra("number of people",finalNumPpl);
                //also have to add currency, when settings activity is complete

                //final screen :)
                startActivity(intent);
                break;
            }
        }
    }
}