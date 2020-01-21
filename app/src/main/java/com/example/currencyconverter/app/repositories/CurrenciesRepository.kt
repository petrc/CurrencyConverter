package com.example.currencyconverter.app.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.currencyconverter.R
import com.example.currencyconverter.app.models.CurrenciesResponse
import com.example.currencyconverter.app.models.CurrencyData
import com.example.currencyconverter.app.network.RevolutInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrenciesRepository {

    private var revolutApiClient = RevolutInterface.create()

    var rates = MutableLiveData<Map<String, Float>>()

    var currencies = arrayListOf(
        CurrencyData(
            code = "EUR",
            description = "Euro",
            icon = R.drawable.ic_flag_european_union,
            rate = 1f,
            editable = true
        ),
        CurrencyData(
            code = "AUD",
            description = "Australian Dollar",
            icon = R.drawable.ic_flag_australia
        ),
        CurrencyData(
            code = "BGN",
            description = "Bulgarian Lev",
            icon = R.drawable.ic_flag_bulgaria
        ),
        CurrencyData(
            code = "BRL",
            description = "Brazilian Real",
            icon = R.drawable.ic_flag_brazil
        ),
        CurrencyData(
            code = "CAD",
            description = "Canadian Dollar",
            icon = R.drawable.ic_flag_canada
        ),
        CurrencyData(
            code = "CHF",
            description = "Swiss franc",
            icon = R.drawable.ic_flag_switzerland
        ),
        CurrencyData(
            code = "CNY",
            description = "Chinese Yuan Renminbi ",
            icon = R.drawable.ic_flag_china
        ),
        CurrencyData(
            code = "CZK",
            description = "Czech Koruna",
            icon = R.drawable.ic_flag_czech_republic
        ),
        CurrencyData(
            code = "DKK",
            description = "Danish Krone",
            icon = R.drawable.ic_flag_denmark
        ),
        CurrencyData(
            code = "GBP",
            description = "Pound Sterling",
            icon = R.drawable.ic_flag_united_kingdom
        ),
        CurrencyData(
            code = "HKD",
            description = "Hong Kong Dollar",
            icon = R.drawable.ic_flag_hong_kong
        ),
        CurrencyData(
            code = "HRK",
            description = "Croatian Kuna",
            icon = R.drawable.ic_flag_croatia
        ),
        CurrencyData(
            code = "HUF",
            description = "Hungarian Forint",
            icon = R.drawable.ic_flag_hungary
        ),
        CurrencyData(
            code = "IDR",
            description = "Indonesian Rupiah",
            icon = R.drawable.ic_flag_indonesia
        ),
        CurrencyData(
            code = "ILS",
            description = "Israeli New Shekel",
            icon = R.drawable.ic_flag_israel
        ),
        CurrencyData(
            code = "INR",
            description = "Indian Rupee",
            icon = R.drawable.ic_flag_india
        ),
        CurrencyData(
            code = "ISK",
            description = "Icelandic Króna",
            icon = R.drawable.ic_flag_iceland
        ),
        CurrencyData(
            code = "JPY",
            description = "Japanese Yen",
            icon = R.drawable.ic_flag_japan
        ),
        CurrencyData(
            code = "KRW",
            description = "South Korean Won",
            icon = R.drawable.ic_flag_south_korea
        ),
        CurrencyData(
            code = "MXN",
            description = "Mexican Peso",
            icon = R.drawable.ic_flag_mexico
        ),
        CurrencyData(
            code = "MYR",
            description = "Malaysian Ringgit",
            icon = R.drawable.ic_flag_malaysia
        ),
        CurrencyData(
            code = "NOK",
            description = "Norwegian Krone",
            icon = R.drawable.ic_flag_norway
        ),
        CurrencyData(
            code = "NZD",
            description = "New Zealand Dollar",
            icon = R.drawable.ic_flag_new_zealand
        ),
        CurrencyData(
            code = "PHP",
            description = "Philippine Peso",
            icon = R.drawable.ic_flag_philippines
        ),
        CurrencyData(
            code = "PLN",
            description = "Polish Zloty",
            icon = R.drawable.ic_flag_poland
        ),
        CurrencyData(
            code = "RON",
            description = "Romanian New Leu",
            icon = R.drawable.ic_flag_romania
        ),
        CurrencyData(
            code = "RUB",
            description = "Russian Ruble",
            icon = R.drawable.ic_flag_russia
        ),
        CurrencyData(
            code = "SEK",
            description = "Swedish Krona",
            icon = R.drawable.ic_flag_sweden
        ),
        CurrencyData(
            code = "SGD",
            description = "Singapore Dollar",
            icon = R.drawable.ic_flag_singapore
        ),
        CurrencyData(
            code = "THB",
            description = "Thai Baht",
            icon = R.drawable.ic_flag_thailand
        ),
        CurrencyData(
            code = "TRY",
            description = "Turkish Lira",
            icon = R.drawable.ic_flag_turkey
        ),
        CurrencyData(
            code = "USD",
            description = "US Dollar",
            icon = R.drawable.ic_flag_united_states_of_america
        ),
        CurrencyData(
            code = "ZAR",
            description = "South African Rand",
            icon = R.drawable.ic_flag_south_africa
        )
    )

    fun getRates(base: String) {
        revolutApiClient.getCurrenyData(base)
            .enqueue(object : Callback<CurrenciesResponse> {
                override fun onResponse(
                    call: Call<CurrenciesResponse>,
                    response: Response<CurrenciesResponse>
                ) {
                    if (response.isSuccessful) {
                        rates.value = response.body()?.rates
                    } else {
                        Log.e("getCurrencies", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<CurrenciesResponse>, t: Throwable) {
                    Log.e("getCurrencies", t.localizedMessage)
                }
            })
    }
}