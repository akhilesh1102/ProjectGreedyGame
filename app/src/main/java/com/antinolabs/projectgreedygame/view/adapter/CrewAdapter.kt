package com.antinolabs.projectgreedygame.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antinolabs.projectgreedygame.databinding.ItemCrewBinding
import com.antinolabs.projectgreedygame.model.Cast

class CrewAdapter : ListAdapter<Cast.Crew, CrewAdapter.CrewListRecyclerViewHolder>(CrewCallBack()) {

    class CrewListRecyclerViewHolder(
        private val binding: ItemCrewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cast.Crew) {
            binding.apply {
                result=item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewListRecyclerViewHolder {
        val binding = ItemCrewBinding.inflate(LayoutInflater.from(parent.context))
        return CrewListRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CrewListRecyclerViewHolder, position: Int) {
        val currentArticle = getItem(position)
        holder.bind(currentArticle)
    }


}

class CrewCallBack : DiffUtil.ItemCallback<Cast.Crew>() {
    override fun areItemsTheSame(oldItem: Cast.Crew, newItem: Cast.Crew): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Cast.Crew, newItem: Cast.Crew): Boolean =
        oldItem.name == newItem.name

}