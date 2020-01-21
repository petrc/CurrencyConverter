package com.example.currencyconverter.app.models

class CurrencyData(
    var code: String,
    var description: String,
    var icon: Int,
    var rate: Float = 0f,
    var rateCalculated: Float = 0f,
    var editable: Boolean = false
)


