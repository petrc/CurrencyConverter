package com.example.currencyconverter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconverter.app.repositories.CurrenciesRepository
import com.example.currencyconverter.app.viewmodels.CurrenciesViewModel
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

class CurrenciesViewModelUnitTest {

    private val viewModel = CurrenciesViewModel(CurrenciesRepository())

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun currenciesHasData() {
        assertNotNull(viewModel.currencies)
        assert(viewModel.currencies.size > 1)
    }


    @Test
    fun currenciesHasRates() {

        viewModel.refreshRates()

        viewModel.newRates.observeForever {
            assertNotNull(viewModel.newRates)
            assert(it.size > 1)
        }
    }
}
