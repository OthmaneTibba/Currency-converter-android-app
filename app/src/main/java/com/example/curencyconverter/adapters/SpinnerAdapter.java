package com.example.curencyconverter.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.curencyconverter.R;
import com.example.curencyconverter.models.Currency;

import java.util.ArrayList;


public class SpinnerAdapter extends BaseAdapter {
     Context context;
     ArrayList<Currency> currencies;

    public SpinnerAdapter(Context context, ArrayList<Currency> currencies){
        this.context = context;
        this.currencies = currencies;
    }

    @Override
    public int getCount() {
        return currencies.size();
    }

    @Override
    public Object getItem(int i) {
        return currencies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view =  LayoutInflater.from(context).inflate(R.layout.spinner_row,viewGroup, false);
        ImageView currencyFlagImage = view.findViewById(R.id.currencyFlagImage);
        TextView currencyName = view.findViewById(R.id.currencyName);
        currencyFlagImage.setImageResource(currencies.get(i).getCurrencyCountryFlag());
        currencyName.setText(currencies.get(i).getCurrencyName());
        return view;
    }
}
