package com.example.curencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.curencyconverter.adapters.SpinnerAdapter;
import com.example.curencyconverter.models.Currency;

import java.util.ArrayList;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ArrayList<Currency> currencies;
    Spinner fromSpinner;
    Spinner toSpinner;
    SpinnerAdapter spinnerAdapter;
    Button convertButton;
    EditText amountTextView;
    TextView convertedAmountTextView;
    String selectedFromCurrency;
    String selectedToCurrency;
    float convertedAmount;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // to fix the orientationt to portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        convertedAmount = 0f;
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(getDrawable(R.color.white));
        selectedFromCurrency = "MAD";
        selectedToCurrency = "MAD";
        initCurrenciesData();
        initViews();
        initSpinnerAdapter();

        handleEvents();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.convertButton){
            convertCurrency(selectedFromCurrency, selectedToCurrency);
        }
    }


    private void handleEvents(){
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               selectedFromCurrency = currencies.get(i).getCurrencyName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedToCurrency = currencies.get(i).getCurrencyName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        convertButton.setOnClickListener(this);
    }

    private void initSpinnerAdapter(){
        spinnerAdapter = new SpinnerAdapter(this,currencies);
        fromSpinner.setAdapter(spinnerAdapter);
        toSpinner.setAdapter(spinnerAdapter);
    }




    private void initViews(){
        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        convertButton = findViewById(R.id.convertButton);
        amountTextView = findViewById(R.id.amountTextView);
        convertedAmountTextView = findViewById(R.id.convertedAmountTextView);
    }

    @SuppressLint("SetTextI18n")
    private void convertCurrency(String fromCurrency, String toCurrency){

        if(amountTextView.getText().toString().isEmpty()){
            amountTextView.setError("Please enter the amount");
        }
        else if(selectedFromCurrency.equals(selectedToCurrency)){
            Toast.makeText(this, "Please selected different currency ",Toast.LENGTH_LONG).show();
        }
        else{
            // from mad to usd and eur
            if(fromCurrency.equals("MAD") && toCurrency.equals("EUR")){
                convertedAmount = Float.parseFloat(amountTextView.getText().toString()) / 11;
            }
            else if(fromCurrency.equals("MAD") && toCurrency.equals("USD")){
                convertedAmount = Float.parseFloat(amountTextView.getText().toString()) / 10;
            }
            else if(fromCurrency.equals("EUR") && toCurrency.equals("MAD")){
                convertedAmount = Float.parseFloat(amountTextView.getText().toString()) * 10;
            }
            else if(fromCurrency.equals("EUR") && toCurrency.equals("USD")){
                convertedAmount = Float.parseFloat(amountTextView.getText().toString()) * 1.07f;
            }
            else if(fromCurrency.equals("USD") && toCurrency.equals("MAD")){
                convertedAmount = Float.parseFloat(amountTextView.getText().toString()) * 10;
            }
            else if(fromCurrency.equals("USD") && toCurrency.equals("EUR")){
                convertedAmount = Float.parseFloat(amountTextView.getText().toString()) / 1.07f;
            }
            convertedAmountTextView.setText(convertedAmount+ " " + toCurrency);
        }


    }

    private void initCurrenciesData(){
        currencies = new ArrayList<>();
        currencies.add(new Currency("MAD" ,R.drawable.maroc));
        currencies.add(new Currency("EUR",R.drawable.europe));
        currencies.add(new Currency("USD",R.drawable.usa));
    }


}