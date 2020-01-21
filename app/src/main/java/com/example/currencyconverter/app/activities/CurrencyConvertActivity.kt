package com.example.currencyconverter.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.currencyconverter.R
import com.example.currencyconverter.app.adaptors.CurrenciesAdapter
import com.example.currencyconverter.app.interfaces.CurrencyUpdateInterface
import com.example.currencyconverter.app.models.CurrencyData
import com.example.currencyconverter.app.repositories.CurrenciesRepository
import com.example.currencyconverter.app.viewmodels.CurrenciesViewModel
import kotlinx.android.synthetic.main.activity_currency_convert.*
import java.util.*
import kotlin.concurrent.fixedRateTimer

class CurrencyConvertActivity : AppCompatActivity(), CurrencyUpdateInterface {

    private val currenciesViewModel = CurrenciesViewModel(CurrenciesRepository())

    private var refreshTimer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_currency_convert)

        val currenciesAdapter = CurrenciesAdapter(currenciesViewModel, this)

        rvCurrenciesList.adapter = currenciesAdapter
        rvCurrenciesList.itemAnimator?.changeDuration = 10
        rvCurrenciesList.setItemViewCacheSize(30)

        currenciesViewModel.newRates.observe(this, Observer {
            for (currency in currenciesViewModel.currencies.drop(1)) {
                currency.rate = (it[currency.code] ?: currency.rate)
            }
            currenciesAdapter.notifyItemRangeChanged(1, currenciesViewModel.currencies.size - 1)
        })

    }

    override fun onCurrencyClicked(currencyData: CurrencyData) {
        currenciesViewModel.currencies[0].editable = false
        currencyData.rate = currencyData.rateCalculated
        currencyData.editable = true

        currenciesViewModel.currencies.remove(currencyData)
        currenciesViewModel.currencies.add(0, currencyData)

        rvCurrenciesList.adapter?.notifyDataSetChanged()
        rvCurrenciesList.smoothScrollToPosition(0)
    }

    override fun onCurrencyRateEdited(newRate: Float) {
        currenciesViewModel.currencies[0].rate = newRate
        rvCurrenciesList.adapter?.notifyItemRangeChanged(1, currenciesViewModel.currencies.size)
    }

    override fun onPause() {
        super.onPause()

        refreshTimer.cancel()
    }

    override fun onResume() {
        super.onResume()

        refreshCurrencies(1)
    }

    private fun refreshCurrencies(interval: Int = 0) {
        if (interval > 0) {
            refreshTimer = fixedRateTimer(
                name = "refresh-timer",
                initialDelay = 0, period = interval * 1000L
            ) {
                currenciesViewModel.refreshRates()
            }
        }
    }
}
