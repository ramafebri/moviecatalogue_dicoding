package com.example.moviecatalogue.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.remote.ResultsItem
import com.example.moviecatalogue.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_list.view.*
import com.example.moviecatalogue.utils.JsonHelper

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    private var moviesEntity = ArrayList<ResultsItem>()

    fun setMovies(movies: List<ResultsItem>?) {
        if (movies == null) return
        this.moviesEntity.clear()
        this.moviesEntity.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(moviesEntity[position])
    }

    override fun getItemCount(): Int {
        return moviesEntity.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movies: ResultsItem) {
            with(itemView) {
                tv_item_title.text = movies.title
                tv_item_date.text = movies.releaseDate
                tv_item_motto.text = movies.originalLanguage

                val jsonHelper = JsonHelper(context)
                val imgUri = movies.posterPath?.let { jsonHelper.loadImage(it) }
                Glide.with(context)
                        .load(imgUri)
                        .apply(RequestOptions().override(120, 150))
                        .into(img_poster)
            }

//            itemView.setOnClickListener {
//                val moveToDetail = Intent(itemView.context, DetailActivity::class.java)
//                moveToDetail.putExtra(DetailActivity.ITEM_ID, movies.moviesId)
//                moveToDetail.putExtra(DetailActivity.IS_MOVIES, "true")
//                itemView.context.startActivity(moveToDetail)
//            }
        }
    }

}