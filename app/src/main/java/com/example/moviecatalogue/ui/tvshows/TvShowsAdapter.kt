package com.example.moviecatalogue.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.TvShowsEntity
import com.example.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_list.view.*

class TvShowsAdapter(private var tvShowsEntity: ArrayList<TvShowsEntity>) : RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsAdapter.ViewHolder, position: Int) {
        holder.bind(tvShowsEntity[position])
    }

    override fun getItemCount(): Int {
        return tvShowsEntity.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(tvShowsEntity: TvShowsEntity) {
            with(itemView) {
                tv_item_title.text = tvShowsEntity.title
                tv_item_date.text = tvShowsEntity.year
                tv_item_motto.text = tvShowsEntity.motto

                Glide.with(context)
                    .load(tvShowsEntity.imagePath)
                    .apply(RequestOptions().override(120, 150))
                    .into(img_poster)
            }
            itemView.setOnClickListener {
                val moveToDetail = Intent(itemView.context, DetailActivity::class.java)
                moveToDetail.putExtra(DetailActivity.ITEM_ID, tvShowsEntity.tvShowsId)
                moveToDetail.putExtra(DetailActivity.IS_MOVIES, "false")
                itemView.context.startActivity(moveToDetail)
            }
        }
    }
}