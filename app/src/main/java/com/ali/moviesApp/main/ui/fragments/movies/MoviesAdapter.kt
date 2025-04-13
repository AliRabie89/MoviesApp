package com.ali.moviesApp.main.ui.fragments.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ali.moviesApp.databinding.ItemMovieBinding
import com.ali.moviesApp.main.base.CustomBaseAdapter
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel

class MoviesAdapter(private var isGrid: Boolean,
                    val onItemClicked:(MovieUiModel,ImageView)->Unit,
                    val onAddToFavoriteButtonClicked:(MovieUiModel,Boolean)->Unit) : CustomBaseAdapter<MovieUiModel, MoviesAdapter.AdsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return AdsViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLayoutStyle(grid: Boolean) {
        isGrid = grid
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class AdsViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieUiModel) {
            binding.btnFavorite.isChecked = item.isInFavorites
            binding.movie = item
            binding.imagePoster.setOnClickListener { onItemClicked(item,binding.imagePoster) }
            binding.btnFavorite.setOnCheckedChangeListener { toggle, isChecked ->
                if(toggle.isPressed){
                    item.isInFavorites = isChecked
                    onAddToFavoriteButtonClicked(item,isChecked)
                }
            }
            binding.executePendingBindings() // To immediately update the UI
        }
    }
}