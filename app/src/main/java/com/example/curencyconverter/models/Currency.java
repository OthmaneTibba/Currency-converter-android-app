package com.example.curencyconverter.models;

public class Currency {
     String currencyName;
     int currencyCountryFlag;

    public Currency(String currencyName, int currencyCountryFlag) {
        this.currencyName = currencyName;
        this.currencyCountryFlag = currencyCountryFlag;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public int getCurrencyCountryFlag() {
        return currencyCountryFlag;
    }
}
