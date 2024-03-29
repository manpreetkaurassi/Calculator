package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    //Variables to hold operand and type of calculation
    private Double operand1 = null;
    private String pendingOperation = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.displayOperation);

        //View.OnClickListener listener = null;
        //displayOperation.setText("operation");
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);

        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMult);
        Button buttonAdd = (Button) findViewById(R.id.buttonPlus);
        Button buttonSubtract = (Button) findViewById(R.id.buttonMinus);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }

        };


        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                try{
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                }catch(NumberFormatException e){
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonAdd.setOnClickListener(opListener);
        buttonSubtract.setOnClickListener(opListener);


    }

    private void performOperation(Double value, String operation) {
        if(null == operand1){
            operand1 = value;
        }
        else {
            if (pendingOperation.equals("=")){
                pendingOperation = operation;
            }

            switch(pendingOperation) {
                case "=":
                    operand1 = value;
                    break;

                case "+":
                    operand1 = operand1 + value;
                    break;

                case "-":
                    operand1 = operand1 - value;
                    break;

                case "/":
                    if (value == 0 ) {
                        operand1 = 0.0;
                    }
                    else {
                        operand1 = operand1 / value;
                    }
                    break;

                case "*":
                    operand1 = operand1 * value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }

}
