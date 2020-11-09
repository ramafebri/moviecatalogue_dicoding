package com.example.moviecatalogue.utils

import android.content.Context
import android.util.Log
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.remote.NowPlayingResponse
import com.example.moviecatalogue.data.source.remote.ResultsItem
import com.example.moviecatalogue.service.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONException

class JsonHelper(private val context: Context) {
    fun loadAllMovies(): ArrayList<ResultsItem> {
        val list = ArrayList<ResultsItem>()
        try {
            val apiKey = R.string.api_key
            val client = ApiConfig.getApiService().getAllMovies()
            client.enqueue(object : Callback<NowPlayingResponse>{
                override fun onResponse(
                    call: Call<NowPlayingResponse>,
                    response: Response<NowPlayingResponse>
                ) {
                    if (response.body() != null){
                        if(response.body()!!.results != null){
                            val data = response.body()!!.results as ArrayList<ResultsItem>
                            Log.d("response", data.toString())
                            list.addAll(data)
                        }
                    }
                }
                override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                    Log.e("Exception", "onFailure: ${t.message.toString()}")
                }
            })
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        Log.d("response", list.toString())
        return list
    }

    fun loadImage(posterPath: String): String{
        val baseUri = "https://image.tmdb.org/t/p/"
        val size = "w500"
        return "${baseUri}${size}${posterPath}"
    }
}