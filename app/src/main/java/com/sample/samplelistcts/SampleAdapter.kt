package com.sample.samplelistcts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.samplelistcts.databinding.ItemSampleBinding

class SampleAdapter(private val sampleList: List<SampleModel>) :
    RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {

    lateinit var itemSampleBinding: ItemSampleBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        itemSampleBinding =
            ItemSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SampleViewHolder(itemSampleBinding)
    }

    override fun onBindViewHolder(holder: SampleAdapter.SampleViewHolder, position: Int) {
        val user = sampleList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return sampleList.size
    }

    class SampleViewHolder(private val sampleBinding: ItemSampleBinding) :
        RecyclerView.ViewHolder(sampleBinding.root) {
        fun bind(sample: SampleModel) {
            sampleBinding.sampleData = sample
        }

    }
}
