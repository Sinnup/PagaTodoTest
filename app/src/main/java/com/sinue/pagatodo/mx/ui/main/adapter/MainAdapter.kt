package com.sinue.pagatodo.mx.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinue.pagatodo.mx.R
import com.sinue.pagatodo.mx.data.model.UserTransaction
import com.sinue.pagatodo.mx.databinding.TransactionLayoutBinding
import com.sinue.pagatodo.mx.utils.TimeUtils
import java.util.*

class MainAdapter(private val transactions: ArrayList<UserTransaction>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = TransactionLayoutBinding.bind(itemView)
        fun bind(userTransaction: UserTransaction) {
            binding.apply {
                merchantName.text = userTransaction.merchantName
                amount.text = String.format("%,d", userTransaction.amount)
                currencyCode.text = userTransaction.currencyCode

                TimeUtils.convertTimeStampMillisToLocal(userTransaction.timestamp)?.let {
                    time.text = it
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.transaction_layout, parent, false)
        )

    override fun getItemCount(): Int = transactions.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    fun addTransactions(transactions: List<UserTransaction>) {
        this.transactions.apply {
            clear()
            addAll(transactions)
        }
    }
}