package com.antinolabs.projectgreedygame.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antinolabs.projectgreedygame.databinding.ItemCastBinding
import com.antinolabs.projectgreedygame.model.Cast

class CastXAdapter : ListAdapter<Cast.CastX, CastXAdapter.CastListRecyclerViewHolder>(CastCallBack()) {

    class CastListRecyclerViewHolder(
        private val binding: ItemCastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cast.CastX) {
            binding.apply {
                result=item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastListRecyclerViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context))
        return CastListRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastListRecyclerViewHolder, position: Int) {
        val currentArticle = getItem(position)
        holder.bind(currentArticle)
    }


}

class CastCallBack : DiffUtil.ItemCallback<Cast.CastX>() {
    override fun areItemsTheSame(oldItem: Cast.CastX, newItem: Cast.CastX): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Cast.CastX, newItem: Cast.CastX): Boolean =
        oldItem.name == newItem.name

}