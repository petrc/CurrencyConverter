package com.example.currencyconverter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyconverter.app.repositories.CurrenciesRepository
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class CurrenciesRepositoryUnitTest {

    private val repository = CurrenciesRepository()

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun repositoryHasData() {
        assertNotNull(repository.currencies)
        assert(repository.currencies.size > 1)
    }


    @Test
    fun repositoryHasRates() {

        repository.getRates("EUR")

        repository.rates.observeForever {
            assertNotNull(repository.rates)
            assert(it.size > 1)
        }
    }
}
