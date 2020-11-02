package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.data.TvShowsEntity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val ITEM_ID = "0"
        const val IS_MOVIES = "true"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val itemId = extras.getString(ITEM_ID)
            val isMovies = extras.getString(IS_MOVIES)
            if (itemId != null) {
                viewModel.setSelectedDetail(itemId, isMovies.toBoolean())
                if(isMovies.toBoolean()){
                    val data = viewModel.getDetailMovies()
                    putDataMoviesToView(data)
                } else {
                    val data = viewModel.getDetailTvShows()
                    putDataTvShowsToView(data)
                }
            }
        }
    }

    private fun putDataMoviesToView(data: MoviesEntity){
        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailYear: TextView = findViewById(R.id.tv_detail_date)
        val tvDetailMotto: TextView = findViewById(R.id.tv_detail_motto)
        val tvDetailDirector: TextView = findViewById(R.id.tv_detail_director)
        val tvDetailOverview: TextView = findViewById(R.id.tv_detail_overview)

        tvDetailName.text = data.title
        tvDetailYear.text = data.year
        tvDetailMotto.text = data.motto
        tvDetailDirector.text = data.director
        tvDetailOverview.text = data.description

        Glide.with(this)
            .load(data.imagePath)
            .apply(RequestOptions().override(150, 170))
            .into(img_detail)
    }

    private fun putDataTvShowsToView(data: TvShowsEntity){
        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailYear: TextView = findViewById(R.id.tv_detail_date)
        val tvDetailMotto: TextView = findViewById(R.id.tv_detail_motto)
        val tvDetailDirector: TextView = findViewById(R.id.tv_detail_director)
        val tvDetailOverview: TextView = findViewById(R.id.tv_detail_overview)

        tvDetailName.text = data.title
        tvDetailYear.text = data.year
        tvDetailMotto.text = data.motto
        tvDetailDirector.text = data.director
        tvDetailOverview.text = data.description

        Glide.with(this)
            .load(data.imagePath)
            .apply(RequestOptions().override(150, 170))
            .into(img_detail)
    }
}