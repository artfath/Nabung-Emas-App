package com.apps.nabungemas.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apps.nabungemas.R
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.databinding.ListSavingBinding

class SavingListAdapter:
ListAdapter<SavingTable,SavingListAdapter.SavingViewHolder>(DiffCallback){
    class SavingViewHolder(private var binding: ListSavingBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(saving: SavingTable){
            binding.apply {
                tvSavingCategory.text = saving.savingCategory
                tvTarget.text = itemView.context.getString(R.string.target,saving.target.toString())
                tvSaving.text =itemView.context.getString(R.string.total_saving,saving.totalSaving.toString())
                tvPercentage.text = itemView.context.getString(R.string.percentage,saving.percentage)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavingViewHolder {
        return SavingViewHolder(
            ListSavingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: SavingViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object{
        private val DiffCallback = object : DiffUtil.ItemCallback<SavingTable>(){
            override fun areItemsTheSame(oldItem: SavingTable, newItem: SavingTable): Boolean {
                return  oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: SavingTable, newItem: SavingTable): Boolean {
                return oldItem.savingCategory == newItem.savingCategory
            }


        }
    }
}