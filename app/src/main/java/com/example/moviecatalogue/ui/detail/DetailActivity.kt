package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.utils.JsonHelper
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var moviesDetailResponse: MoviesDetailResponse? = null
    private var tvDetailResponse: TvDetailResponse? = null
    private val jsonHelper = JsonHelper()
    private var isFavorite = false

    companion object {
        const val ID = "id"
        const val IS_MOVIES = "true"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Item"

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val isMovies = extras.getString(IS_MOVIES)
            val id = extras.getInt(ID)

            if(isMovies.toBoolean()){
                viewModel.getFavMoviesById(id).observe(this, {favMovies->
                    if(favMovies !== null){
                        if(favMovies.id > 0){
                            isFavorite = true
                            setFavButtonColor(true)
                        }
                    }
                })
                viewModel.setDetailMovies(id)
                viewModel.getDetailMovies().observe(this, {moviesDetail ->
                    moviesDetailResponse = moviesDetail
                    putDataMoviesToView(moviesDetail)
                })
            } else {
                viewModel.getFavTvById(id).observe(this, {favTv->
                    if(favTv !== null) {
                        if (favTv.id > 0) {
                            isFavorite = true
                            setFavButtonColor(true)
                        }
                    }
                })
                viewModel.setDetailTv(id)
                viewModel.getDetailTv().observe(this, {tvDetail->
                    tvDetailResponse = tvDetail
                    putDataTvShowsToView(tvDetail)
                })
            }

            binding.fabAdd.setOnClickListener {
                onClickButton(isMovies.toBoolean())
            }
        }
    }

    private fun onClickButton(isMovies: Boolean){
        if(isMovies){
            insertDelFavMovies(moviesDetailResponse)
        } else {
            insertDelFavTv(tvDetailResponse)
        }
    }

    private fun putDataMoviesToView(data: MoviesDetailResponse?){
        if (data != null) {
            binding.tvDetailName.text = data.originalTitle
            binding.tvDetailDate.text = data.releaseDate
            binding.tvDetailMotto.text = data.originalLanguage
            binding.tvDetailDirector.text = data.popularity.toString()
            binding.tvDetailOverview.text = data.overview

            val imgUrl = data.posterPath?.let { jsonHelper.loadImage(it) }
            binding.imgDetail.tag = data.posterPath
            Glide.with(this)
                .load(imgUrl)
                .apply(RequestOptions().override(150, 170))
                .into(binding.imgDetail)
        }
    }

    private fun putDataTvShowsToView(data: TvDetailResponse?){
        if(data !== null){
            binding.tvDetailName.text = data.name
            binding.tvDetailDate.text = data.firstAirDate
            binding.tvDetailMotto.text = data.originalLanguage
            binding.tvDetailDirector.text = data.popularity.toString()
            binding.tvDetailOverview.text = data.overview

            val imgUrl = data.posterPath?.let { jsonHelper.loadImage(it) }
            binding.imgDetail.tag = data.posterPath
            Glide.with(this)
                    .load(imgUrl)
                    .apply(RequestOptions().override(150, 170))
                    .into(binding.imgDetail)
        }
    }

    private fun insertDelFavMovies(data: MoviesDetailResponse?){
        if (data != null) {
            val favMovies = MoviesEntity(
                data.id!!,
                data.title!!,
                data.releaseDate!!,
                data.originalLanguage!!,
                data.posterPath!!
            )
            if(!isFavorite){
                viewModel.insertFavMovies(favMovies)
                setFavButtonColor(true)
            } else {
                viewModel.delFavMovies(favMovies)
                setFavButtonColor(false)
            }
        }
    }

    private fun insertDelFavTv(data: TvDetailResponse?){
        if (data != null) {
            val favTv = TvShowsEntity(
                data.id!!,
                data.name!!,
                data.firstAirDate!!,
                data.originalLanguage!!,
                data.posterPath!!
            )
            if(!isFavorite) {
                viewModel.insertFavTv(favTv)
                setFavButtonColor(true)
            } else {
                viewModel.delFavTv(favTv)
                setFavButtonColor(false)
            }
        }
    }

    private fun setFavButtonColor(state: Boolean){
        if(state){
            binding.fabAdd.setImageResource(R.drawable.ic_favorite_pink_24dp)
        } else {
            binding.fabAdd.setImageResource(R.drawable.ic_favorite_black_24dp)
        }
    }
}