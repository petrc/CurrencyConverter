package com.example.currencyconverter.app.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.app.interfaces.CurrencyUpdateInterface
import com.example.currencyconverter.app.models.CurrencyData
import com.example.currencyconverter.app.viewmodels.CurrenciesViewModel
import com.example.currencyconverter.databinding.ListItemCurrencyBinding
import kotlinx.android.synthetic.main.list_item_currency.view.*
import java.text.DecimalFormat

class CurrenciesAdapter(
    private val currenciesViewModel: CurrenciesViewModel,
    private val currencyUpdateInterface: CurrencyUpdateInterface
) :
    RecyclerView.Adapter<CurrenciesAdapter.CurrencyViewHolder>() {

    var formatter: DecimalFormat = DecimalFormat("#.##")
    lateinit var rv: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        rv = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val listItemBinding = ListItemCurrencyBinding.inflate(inflater, parent, false)

        return CurrencyViewHolder(listItemBinding)
    }

    override fun getItemCount(): Int {
        return currenciesViewModel.currencies.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currencyData = currenciesViewModel.currencies[position]
        currencyData.rateCalculated =
            formatter.format(currencyData.rate * currenciesViewModel.currencies[0].rate).toFloat()

        holder.bind(currencyData)

        if (currencyData.editable) {
            holder.itemView.etCurrencyValue.setText("%.2f".format(currencyData.rate))
            holder.itemView.etCurrencyValue.setSelection(holder.itemView.etCurrencyValue.text.length)
            holder.itemView.etCurrencyValue.requestFocus()

            holder.itemView.etCurrencyValue.doAfterTextChanged { text ->
                if (text.isNullOrEmpty()) {
                    currencyUpdateInterface.onCurrencyRateEdited(0f)
                } else {
                    currencyUpdateInterface.onCurrencyRateEdited(text.toString().toFloat())
                }
            }
        } else {
            holder.itemView.setOnClickListener {
                currencyUpdateInterface.onCurrencyClicked(currencyData)
            }
        }
    }

    inner class CurrencyViewHolder(private val binding: ListItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currencyData: CurrencyData) {
            binding.currencyData = currencyData
            binding.executePendingBindings()
        }
    }

}

