package com.example.moviecatalogue.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.data.source.remote.response.ResultsItemTv
import com.example.moviecatalogue.databinding.ItemListBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.JsonHelper

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    private var tvEntity = ArrayList<ResultsItemTv?>()

    fun setMovies(tv: ArrayList<ResultsItemTv?>) {
        this.tvEntity.clear()
        this.tvEntity.addAll(tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsAdapter.ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsAdapter.ViewHolder, position: Int) {
        tvEntity[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return tvEntity.size
    }

    inner class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: ResultsItemTv) {
            with(binding) {
                tvItemTitle.text = tvShows.name
                tvItemDate.text = tvShows.firstAirDate
                tvItemMotto.text = tvShows.originalLanguage

                val jsonHelper = JsonHelper()
                val imgUri = tvShows.posterPath?.let { jsonHelper.loadImage(it) }
                Glide.with(root.context)
                    .load(imgUri)
                    .apply(RequestOptions().override(120, 150))
                    .into(imgPoster)
            }
            binding.root.setOnClickListener {
                val moveToDetail = Intent(binding.root.context, DetailActivity::class.java)
                moveToDetail.putExtra(DetailActivity.IS_MOVIES, "false")
                moveToDetail.putExtra(DetailActivity.ID, tvShows.id)
                binding.root.context.startActivity(moveToDetail)
            }
        }
    }
}