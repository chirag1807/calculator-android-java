package com.example.icalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText display;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strtoAdd){
        String oldstr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftstr = oldstr.substring(0, cursorPos);
        String rightstr = oldstr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strtoAdd);
            display.setSelection(cursorPos + 1);
        }
        else{
        display.setText(String.format("%s%s%s",leftstr,strtoAdd,rightstr));
        display.setSelection(cursorPos + 1);
        }
    }

    public void zeroBTN(View view){
        updateText("0");
    }

    public void oneBTN(View view){
        updateText("1");
    }

    public void twoBTN(View view){
        updateText("2");
    }

    public void threeBTN(View view){
        updateText("3");
    }

    public void fourBTN(View view){
        updateText("4");
    }

    public void fiveBTN(View view){
        updateText("5");
    }

    public void sixBTN(View view){
        updateText("6");
    }

    public void sevenBTN(View view){
        updateText("7");
    }

    public void eightBTN(View view){
        updateText("8");
    }

    public void nineBTN(View view){
        updateText("9");
    }

    public void clearBTN(View view){
        display.setText("");
    }

    public void parBTN(View view){
        int cursorpos = display.getSelectionStart();
        int openpar = 0;
        int closepar = 0;
        int textlen = display.getText().length();
        for(int i = 0; i < closepar; i++){
            if(display.getText().toString().charAt(i) == '('){
                openpar += 1;
            }

            if(display.getText().toString().charAt(i) == ')'){
                closepar += 1;
            }
        }
        if(openpar == closepar || display.getText().toString().charAt(textlen - 1) == '('){
            updateText("(");
            display.setSelection(cursorpos + 1);
        }
        else if(closepar < openpar && display.getText().toString().charAt(textlen - 1) != ')'){
            updateText(")");
            display.setSelection(cursorpos + 1);
        }
    }

    public void expBTN(View view){
        updateText("^");
    }

    public void plusMinusBTN(View view){
        updateText("-");
    }

    public void equalBTN(View view){
        String userExpression = display.getText().toString();

        userExpression = userExpression.replaceAll("÷","/");
        userExpression = userExpression.replaceAll("×", "*");

        Expression exp = new Expression(userExpression);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void backspaceBTN(View view){
        int cursorpos = display.getSelectionStart();
        int textlen = display.getText().length();

        if(cursorpos != 0 && textlen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorpos-1, cursorpos, "");
            display.setText(selection);
            display.setSelection(cursorpos - 1);
        }
    }

    public void addBTN(View view){
        updateText("+");
    }

    public void substractBTN(View view){
        updateText("-");
    }

    public void mulBTN(View view){
        updateText("×");
    }

    public void divisionBTN(View view){
        updateText("÷");
    }

    public void pointBTN(View view){
        updateText(".");
    }
}