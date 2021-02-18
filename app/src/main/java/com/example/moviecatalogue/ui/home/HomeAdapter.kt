package com.example.moviecatalogue.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.data.source.remote.response.ResultsItemMovies
import com.example.moviecatalogue.databinding.ItemListBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.JsonHelper

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    private var moviesList = ArrayList<ResultsItemMovies?>()

    fun setMovies(movies: ArrayList<ResultsItemMovies?>) {
        this.moviesList.clear()
        this.moviesList.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        moviesList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: ResultsItemMovies) {
            with(binding) {
                tvItemTitle.text = movies.title
                tvItemDate.text = movies.releaseDate
                tvItemMotto.text = movies.originalLanguage

                val jsonHelper = JsonHelper()
                val imgUri = movies.posterPath?.let { jsonHelper.loadImage(it) }
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