package com.example.moviecatalogue.ui.favorite.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.databinding.ItemListBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.JsonHelper

class FavoriteMoviesAdapter: RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder>() {
    private val listFavMovies = ArrayList<MoviesEntity>()

    fun setFavMovies(favMovies: List<MoviesEntity>?) {
        if (favMovies == null) return
        this.listFavMovies.clear()
        this.listFavMovies.addAll(favMovies)

        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listFavMovies[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return listFavMovies.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemListBinding.bind(view)

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