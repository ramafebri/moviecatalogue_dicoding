package com.example.moviecatalogue.ui.favorite.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.databinding.ItemListBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.JsonHelper

class FavoriteMoviesAdapter: PagedListAdapter<MoviesEntity, FavoriteMoviesAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favMovies = getItem(position)
        if (favMovies != null) {
            holder.bind(favMovies)
        }
    }

    fun getSwipedData(swipedPosition: Int): MoviesEntity? = getItem(swipedPosition)

    inner class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MoviesEntity) {
            with(binding) {
                tvItemTitle.text = movies.title
                tvItemDate.text = movies.date
                tvItemMotto.text = movies.language

                val jsonHelper = JsonHelper()
                val imgUri = movies.image.let { jsonHelper.loadImage(it) }
                Glide.with(root.context)
                    .load(imgUri)
                    .apply(RequestOptions().override(120, 150))
                    .into(imgPoster)
            }

            binding.root.setOnClickListener {
                val moveToDetail = Intent(binding.root.context, DetailActivity::class.java)
                moveToDetail.putExtra(DetailActivity.IS_MOVIES, "true")
                moveToDetail.putExtra(DetailActivity.ID, movies.id)
                binding.root.context.startActivity(moveToDetail)
            }
        }
    }
}