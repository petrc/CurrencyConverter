package com.example.currencyconverter.app.models

class CurrenciesResponse {
    lateinit var base: String
    lateinit var date: String
    lateinit var rates: Map<String, Float>
}