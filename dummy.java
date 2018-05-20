public class MainActivity extends AppCompatActivity {

    public enum Operator {none , add , minus , multiply , divide}
    private double a=0 , b=0;
    private Operator optr = Operator.none;
    private boolean reachedEquality=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btn00Click(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        if(reachedEquality){
            eText.setText("");
        }
        reachedEquality=false;
        eText.setText(eText.getText()+"0");
    }

    public void btn01Click(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        if(reachedEquality){
            eText.setText("");
        }
        reachedEquality=false;
        eText.setText(eText.getText()+"1");
    }

    public void btn02Click(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        if(reachedEquality){
            eText.setText("");
        }
        reachedEquality=false;
        eText.setText(eText.getText()+"2");
    }

    public void btn03Click(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        if(reachedEquality){
            eText.setText("");
        }
        reachedEquality=false;
        eText.setText(eText.getText()+"3");
    }

    public void btn04Click(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        if(reachedEquality){
            eText.setText("");
        }
        reachedEquality=false;
        eText.setText(eText.getText()+"4");
    }

    public void btn05Click(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        if(reachedEquality){
            eText.setText("");
        }
        reachedEquality=false;
        eText.setText(eText.getText()+"5");
    }

    public void btn06Click(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        if(reachedEquality){
            eText.setText("");
        }
        reachedEquality=false;
        eText.setText(eText.getText()+"6");
    }

    public void btn07Click(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        if(reachedEquality){
            eText.setText("");
        }
        reachedEquality=false;
        eText.setText(eText.getText()+"7");
    }

    public void btn08Click(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        if(reachedEquality){
            eText.setText("");
        }
        reachedEquality=false;
        eText.setText(eText.getText()+"8");
    }

    public void btn09Click(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        if(reachedEquality){
            eText.setText("");
        }
        reachedEquality=false;
        eText.setText(eText.getText()+"9");
    }

    public void btnAddClick(View view){
        if(optr == Operator.none) {
            optr = Operator.add;
            EditText eText = (EditText) findViewById(R.id.resultEdit);
            a = Double.parseDouble("0"+eText.getText().toString());
            eText.setText("");
        }
    }

    public void btnMinusClick(View view){
        if(optr == Operator.none) {
            optr = Operator.minus;
            EditText eText = (EditText) findViewById(R.id.resultEdit);
            a = Double.parseDouble("0"+eText.getText().toString());
            eText.setText("");
        }
    }

    public void btnMultiplyClick(View view){
        if(optr == Operator.none) {
            optr = Operator.multiply ;
            EditText eText = (EditText) findViewById(R.id.resultEdit);
            a = Double.parseDouble("0"+eText.getText().toString());
            eText.setText("");
        }
    }

    public void btnDivideClick(View view){
        if(optr == Operator.none) {
            optr = Operator.divide;
            EditText eText = (EditText) findViewById(R.id.resultEdit);
            a = Double.parseDouble("0"+eText.getText().toString());
            eText.setText("");
        }
    }

    public void btnClearClick(View view){
        optr = Operator.none ;
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        a = 0;
        b = 0;
        eText.setText("");
    }

    public void btnDotClick(View view){
        EditText eText = (EditText) findViewById(R.id.resultEdit);
        String text = eText.getText().toString();
        boolean invalid = false;
        for (int i=0; !invalid && i<text.length(); i++){
            if (text.charAt(i)=='.'){
                invalid = true;
            }
        }
        if(!invalid){
            if(text.equals("")){
                text+="0";
            }
            eText.setText(text+".");
        }
    }

    public void btnResultClick(View view){
        if(optr!=Operator.none) {
            EditText eText = (EditText) findViewById(R.id.resultEdit);
            b = Double.parseDouble("0"+eText.getText().toString());
            reachedEquality = true;
            boolean divider = true;
            double result = 0;
            switch (optr) {
                case add:
                    result = a + b;
                    break;
                case minus:
                    result = a - b;
                    break;
                case multiply:
                    result = a * b;
                    break;
                case divide:
                    if (b != 0) {
                        result = a / b;
                    } else {
                        btnClearClick(view);
                        divider = false;
                    }
                    break;
            }
            if ( divider) {
                optr = Operator.none;
                a = result;
                b = 0;
                if ((result - (int) result) == 0.0) {
                    eText.setText(Integer.toString((int) result));
                } else {
                    eText.setText(Double.toString(result));
                }

            }
        }
    }

}
