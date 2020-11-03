package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.data.TvShowsEntity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var tvDetailName: TextView
    private lateinit var tvDetailYear: TextView
    private lateinit var tvDetailMotto: TextView
    private lateinit var tvDetailDirector: TextView
    private lateinit var tvDetailOverview: TextView
    private lateinit var imgDetail: ImageView

    companion object {
        const val ITEM_ID = "0"
        const val IS_MOVIES = "true"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        tvDetailName = findViewById(R.id.tv_detail_name)
        tvDetailYear = findViewById(R.id.tv_detail_date)
        tvDetailMotto = findViewById(R.id.tv_detail_motto)
        tvDetailDirector = findViewById(R.id.tv_detail_director)
        tvDetailOverview = findViewById(R.id.tv_detail_overview)
        imgDetail = findViewById(R.id.img_detail)

        val extras = intent.extras
        if (extras != null) {
            val itemId = extras.getString(ITEM_ID)
            val isMovies = extras.getString(IS_MOVIES)
            if (itemId != null) {
                viewModel.setSelectedDetail(itemId, isMovies.toBoolean())
                if(isMovies.toBoolean()){
                    val data = viewModel.getDetailMovies()
                    if (data != null) {
                        putDataMoviesToView(data)
                    }
                } else {
                    val data = viewModel.getDetailTvShows()
                    if (data != null) {
                        putDataTvShowsToView(data)
                    }
                }
            }
        }
    }

    private fun putDataMoviesToView(data: MoviesEntity){
        tvDetailName.text = data.title
        tvDetailYear.text = data.year
        tvDetailMotto.text = data.motto
        tvDetailDirector.text = data.director
        tvDetailOverview.text = data.description

        imgDetail.tag = data.imagePath
        Glide.with(this)
            .load(data.imagePath)
            .apply(RequestOptions().override(150, 170))
            .into(img_detail)
    }

    private fun putDataTvShowsToView(data: TvShowsEntity){
        tvDetailName.text = data.title
        tvDetailYear.text = data.year
        tvDetailMotto.text = data.motto
        tvDetailDirector.text = data.director
        tvDetailOverview.text = data.description

        imgDetail.tag = data.imagePath
        Glide.with(this)
            .load(data.imagePath)
            .apply(RequestOptions().override(150, 170))
            .into(img_detail)
    }
}