package com.example.currencyconverter.app.network

import com.example.currencyconverter.app.models.CurrenciesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RevolutInterface {

    @GET("latest")
    fun getCurrenyData(@Query("base") base: String): Call<CurrenciesResponse>

    companion object {
        fun create(): RevolutInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://revolut.duckdns.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RevolutInterface::class.java)
        }
    }
}