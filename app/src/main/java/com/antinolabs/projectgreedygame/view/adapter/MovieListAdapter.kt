package com.antinolabs.projectgreedygame.view.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antinolabs.projectgreedygame.R
import com.antinolabs.projectgreedygame.databinding.ItemMoviesBinding
import com.antinolabs.projectgreedygame.model.PopularMovie

class MovieListAdapter : ListAdapter<PopularMovie.Result, MovieListAdapter.MovieListRecyclerViewHolder>(OrderCallBack()) {

    class MovieListRecyclerViewHolder(
        private val binding: ItemMoviesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PopularMovie.Result) {
            binding.apply {
                result=item
                ratMovie.rating=item.vote_average ?:0.0f
                root.setOnClickListener {
                    val bundle =Bundle()
                    bundle.putInt("id",item.id)
                    Navigation.findNavController(root)
                        .navigate(R.id.action_home_to_detail,bundle)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListRecyclerViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context))
        return MovieListRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListRecyclerViewHolder, position: Int) {
        val currentArticle = getItem(position)
        holder.bind(currentArticle)
    }


}

class OrderCallBack : DiffUtil.ItemCallback<PopularMovie.Result>() {
    override fun areItemsTheSame(oldItem: PopularMovie.Result, newItem: PopularMovie.Result): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: PopularMovie.Result, newItem: PopularMovie.Result): Boolean =
        oldItem.original_title == newItem.original_title

}
