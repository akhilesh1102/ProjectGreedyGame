package com.antinolabs.projectgreedygame.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antinolabs.projectgreedygame.databinding.ItemSimilarBinding
import com.antinolabs.projectgreedygame.model.SimilarMovie

class SimilarMovieAdapter : ListAdapter<SimilarMovie.Result, SimilarMovieAdapter.SimilarRecyclerViewHolder>(SimilarCallBack()) {

    class SimilarRecyclerViewHolder(
        private val binding: ItemSimilarBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SimilarMovie.Result) {
            binding.apply {
                result=item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarRecyclerViewHolder {
        val binding = ItemSimilarBinding.inflate(LayoutInflater.from(parent.context))
        return SimilarRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarRecyclerViewHolder, position: Int) {
        val currentArticle = getItem(position)
        holder.bind(currentArticle)
    }


}

class SimilarCallBack : DiffUtil.ItemCallback<SimilarMovie.Result>() {
    override fun areItemsTheSame(oldItem: SimilarMovie.Result, newItem: SimilarMovie.Result): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: SimilarMovie.Result, newItem: SimilarMovie.Result): Boolean =
        oldItem.id == newItem.id

}