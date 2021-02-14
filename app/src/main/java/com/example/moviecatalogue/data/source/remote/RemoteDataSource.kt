package com.example.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.response.*
import com.example.moviecatalogue.service.ApiConfig
import com.example.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    val listPlayingNowMovies = MutableLiveData<ArrayList<ResultsItemMovies?>>()
    val detailMovies = MutableLiveData<MoviesDetailResponse?>()
    val detailTv = MutableLiveData<TvDetailResponse?>()
    val listPopularTv = MutableLiveData<ArrayList<ResultsItemTv?>>()

    fun setNowPlayingMovies() {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getAllMovies().enqueue(object : Callback<NowPlayingResponse> {
            override fun onResponse(call: Call<NowPlayingResponse>, response: Response<NowPlayingResponse>) {
                Log.d("response", response.body().toString())
                if (response.code() == 200) {
                    if(response.body() !== null){
                        if(response.body()!!.results != null){
                            listPlayingNowMovies.postValue(response.body()!!.results)
                        }
                    }
                }
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                Log.d("error", t.toString())
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun setDetailMovies(id: Int) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getDetailMovies(id.toString()).enqueue(object : Callback<MoviesDetailResponse> {
            override fun onResponse(call: Call<MoviesDetailResponse>, response: Response<MoviesDetailResponse>) {
                Log.d("responseDetailMovies", response.body().toString())
                if (response.code() == 200) {
                    if(response.body() !== null){
                        detailMovies.postValue(response.body())
                    }
                }
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<MoviesDetailResponse>, t: Throwable) {
                Log.d("error", t.toString())
                Log.d("responseDetailMovies", t.toString())
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun setDetailTv(id: Int) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getDetailTv(id.toString()).enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(call: Call<TvDetailResponse>, response: Response<TvDetailResponse>) {
                Log.d("responseDetailTv", response.body().toString())
                if (response.code() == 200) {
                    if(response.body() !== null){
                        detailTv.postValue(response.body())
                    }
                }
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                Log.d("error", t.toString())
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun setPopularTv() {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getAllTv().enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                Log.d("response", response.body().toString())
                if (response.code() == 200) {
                    if(response.body() !== null){
                        if(response.body()!!.results != null){
                            listPopularTv.postValue(response.body()!!.results)
                        }
                    }
                }
                EspressoIdlingResource.decrement()
            }
            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.d("error", t.toString())
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getNowPlayingMovies(): LiveData<ArrayList<ResultsItemMovies?>> {
        return listPlayingNowMovies
    }

    fun getDetailMovies(): LiveData<MoviesDetailResponse?> {
        return detailMovies
    }

    fun getDetailTv(): LiveData<TvDetailResponse?> {
        return detailTv
    }

    fun getPopularTv(): LiveData<ArrayList<ResultsItemTv?>> {
        return listPopularTv
    }
}