package com.apps.nabungemas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apps.nabungemas.R
import com.apps.nabungemas.data.TransactionTable
import com.apps.nabungemas.databinding.ListTransactionBinding

class TransactionListAdapter:
ListAdapter<TransactionTable,TransactionListAdapter.TransactionViewHolder>(DiffCallBack){
    class TransactionViewHolder(private var binding:ListTransactionBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(transaction: TransactionTable){
            binding.apply {
                tvSavingCategory.text = transaction.savingCategory
                tvTime.text = transaction.time
//                tvPrice.text = transaction.goldPrice.toString()
//                tvQuantity.text = transaction.goldQuantity.toString()
                tvPrice.text = itemView.context.getString(R.string.price,transaction.goldPrice.toString())
                tvQuantity.text = itemView.context.getString(R.string.gold_weight,transaction.goldQuantity.toString())
                tvProduct.text = transaction.product
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            ListTransactionBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object{
        private val DiffCallBack = object :DiffUtil.ItemCallback<TransactionTable>(){
            override fun areItemsTheSame(
                oldItem: TransactionTable,
                newItem: TransactionTable
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: TransactionTable,
                newItem: TransactionTable
            ): Boolean {
                return oldItem.time == newItem.time
            }

        }
    }
}