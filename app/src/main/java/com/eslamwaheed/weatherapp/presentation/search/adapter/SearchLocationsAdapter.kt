package com.eslamwaheed.weatherapp.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eslamwaheed.domain.entity.search.SearchResponseItem
import com.eslamwaheed.weatherapp.databinding.ItemLocationBinding

class SearchLocationsAdapter :
    ListAdapter<SearchResponseItem, SearchLocationsAdapter.SearchLocationsViewHolder>(
        SearchLocationsDiffUtil
    ) {

    private var onItemClick: ((SearchResponseItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchLocationsViewHolder {
        return SearchLocationsViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchLocationsViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class SearchLocationsViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchResponseItem) {
            with(binding) {
                mtvLocationName.text = item.name
                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }
            }
        }
    }

    object SearchLocationsDiffUtil : DiffUtil.ItemCallback<SearchResponseItem>() {
        override fun areItemsTheSame(
            oldItem: SearchResponseItem, newItem: SearchResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchResponseItem, newItem: SearchResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    fun setItemClick(onItemClick: ((SearchResponseItem) -> Unit)) {
        this.onItemClick = onItemClick
    }
}