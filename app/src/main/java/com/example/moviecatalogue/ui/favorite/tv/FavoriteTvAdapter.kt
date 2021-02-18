package com.example.moviecatalogue.ui.favorite.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.databinding.ItemListBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.JsonHelper

class FavoriteTvAdapter : PagedListAdapter<TvShowsEntity, FavoriteTvAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowsEntity>() {
            override fun areItemsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favTv = getItem(position)
        if (favTv != null) {
            holder.bind(favTv)
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowsEntity? = getItem(swipedPosition)

    inner class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvShowsEntity) {
            with(binding) {
                tvItemTitle.text = tv.title
                tvItemDate.text = tv.date
                tvItemMotto.text = tv.language

                val jsonHelper = JsonHelper()
                val imgUri = tv.image.let { jsonHelper.loadImage(it) }
                Glide.with(root.context)
                        .load(imgUri)
                        .apply(RequestOptions().override(120, 150))
                        .into(imgPoster)
            }

            binding.root.setOnClickListener {
                val moveToDetail = Intent(binding.root.context, DetailActivity::class.java)
                moveToDetail.putExtra(DetailActivity.IS_MOVIES, "false")
                moveToDetail.putExtra(DetailActivity.ID, tv.id)
                binding.root.context.startActivity(moveToDetail)
            }
        }
    }
}