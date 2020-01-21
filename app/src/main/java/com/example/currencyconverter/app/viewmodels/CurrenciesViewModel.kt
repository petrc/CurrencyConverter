package com.example.currencyconverter.app.viewmodels

import androidx.lifecycle.ViewModel
import com.example.currencyconverter.app.repositories.CurrenciesRepository

class CurrenciesViewModel(private val currenciesRepository: CurrenciesRepository) : ViewModel() {

    var currencies = currenciesRepository.currencies
    var newRates = currenciesRepository.rates

    fun refreshRates() = currenciesRepository.getRates(currencies[0].code)
}