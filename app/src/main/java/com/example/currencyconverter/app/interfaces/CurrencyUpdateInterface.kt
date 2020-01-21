package com.example.currencyconverter.app.interfaces

import com.example.currencyconverter.app.models.CurrencyData

interface CurrencyUpdateInterface {
    fun onCurrencyClicked(currencyData: CurrencyData)
    fun onCurrencyRateEdited(newRate: Float)
}