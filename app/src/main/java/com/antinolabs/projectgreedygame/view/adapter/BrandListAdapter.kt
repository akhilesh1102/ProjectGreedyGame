package com.antinolabs.projectgreedygame.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antinolabs.projectgreedygame.databinding.ItemBrandBinding
import com.antinolabs.projectgreedygame.model.MovieDetail

class BrandListAdapter : ListAdapter<MovieDetail.ProductionCompany, BrandListAdapter.BrandListRecyclerViewHolder>(BrandCallBack()) {

    class BrandListRecyclerViewHolder(
        private val binding: ItemBrandBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieDetail.ProductionCompany) {
            binding.apply {
                result=item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandListRecyclerViewHolder {
        val binding = ItemBrandBinding.inflate(LayoutInflater.from(parent.context))
        return BrandListRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandListRecyclerViewHolder, position: Int) {
        val currentArticle = getItem(position)
        holder.bind(currentArticle)
    }


}

class BrandCallBack : DiffUtil.ItemCallback<MovieDetail.ProductionCompany>() {
    override fun areItemsTheSame(oldItem: MovieDetail.ProductionCompany, newItem: MovieDetail.ProductionCompany): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MovieDetail.ProductionCompany, newItem: MovieDetail.ProductionCompany): Boolean =
        oldItem.name == newItem.name

}