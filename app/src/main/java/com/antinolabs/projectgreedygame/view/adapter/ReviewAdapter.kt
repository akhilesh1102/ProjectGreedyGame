package com.antinolabs.projectgreedygame.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antinolabs.projectgreedygame.databinding.ItemReviewBinding
import com.antinolabs.projectgreedygame.databinding.ItemSimilarBinding
import com.antinolabs.projectgreedygame.model.Review
import com.antinolabs.projectgreedygame.model.SimilarMovie

class ReviewAdapter : ListAdapter<Review.Result, ReviewAdapter.ReviewRecyclerViewHolder>(ReviewCallBack()) {

    class ReviewRecyclerViewHolder(
        private val binding: ItemReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Review.Result) {
            binding.apply {
                result=item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewRecyclerViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context))
        return ReviewRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewRecyclerViewHolder, position: Int) {
        val currentArticle = getItem(position)
        holder.bind(currentArticle)
    }


}

class ReviewCallBack : DiffUtil.ItemCallback<Review.Result>() {
    override fun areItemsTheSame(oldItem: Review.Result, newItem: Review.Result): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Review.Result, newItem: Review.Result): Boolean =
        oldItem.id == newItem.id

}