package com.example.elemestest.feature.moreMovie

import android.util.Log
import com.example.elemestest.MVPBase.BasePresenter
import com.example.elemestest.data.ApiClient
import com.example.elemestest.data.model.MovieMappingModel
import com.example.elemestest.data.remote.ApiResource
import com.example.elemestest.data.remote.respons.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoreMoviePresenter: BasePresenter<MoreMovieView> {
    var views : MoreMovieView ?= null
    var apiservice : ApiResource?= null
    var startpersonpopular = 1
    var startupcomming = 1
    var starttoprate = 1
    var startnowplaying = 1


    constructor(views: MoreMovieView?) {
        this.views = views
    }

    fun getPersonPopular(){
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<PersonPopularResponse>? = apiservice?.getPopularPerson(ApiClient.API_KEY, 1)
        getData?.enqueue(object : Callback<PersonPopularResponse> {
            override fun onResponse(
                call: Call<PersonPopularResponse>,
                response: Response<PersonPopularResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    data.known_for.forEach { itemData ->
                        var dataMap = MovieMappingModel(itemData.genre_ids, itemData.id, itemData.original_name, ApiClient.BASE_IMG_URL+itemData.poster_path, itemData.first_air_date, itemData.name, itemData.vote_average)
                        dataMapping.add(dataMap)
                    }
                }
                views!!.onSetMovieAdapter(dataMapping)
                Log.d("onDataPopularPerson", "onResponse: ${response.body()?.results}")
            }

            override fun onFailure(call: Call<PersonPopularResponse>, t: Throwable) {
                Log.d("onDataPopularPerson", "onFailure: $t")
            }

        })
    }

    fun getMorePersonPopular(){
        startpersonpopular += 1
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<PersonPopularResponse>? = apiservice?.getPopularPerson(ApiClient.API_KEY, startpersonpopular)
        getData?.enqueue(object : Callback<PersonPopularResponse> {
            override fun onResponse(
                call: Call<PersonPopularResponse>,
                response: Response<PersonPopularResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    data.known_for.forEach { itemData ->
                        var dataMap = MovieMappingModel(itemData.genre_ids, itemData.id, itemData.original_name, ApiClient.BASE_IMG_URL+itemData.poster_path, itemData.first_air_date, itemData.name, itemData.vote_average)
                        dataMapping.add(dataMap)
                    }
                }
                views!!.onSetMoreMovieAdapter(dataMapping)
                Log.d("onDataPopularPerson", "onResponse: Page $startpersonpopular | ${response.body()?.results}")
            }

            override fun onFailure(call: Call<PersonPopularResponse>, t: Throwable) {
                Log.d("onDataPopularPerson", "onFailure: $t")
            }

        })
    }

    fun getTopRating(){
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<TopRatedResponse>? = apiservice?.getTopRated(ApiClient.API_KEY, 1)
        getData?.enqueue(object : Callback<TopRatedResponse> {
            override fun onResponse(
                call: Call<TopRatedResponse>,
                response: Response<TopRatedResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    var dataMap = MovieMappingModel(data.genre_ids, data.id, data.original_title, ApiClient.BASE_IMG_URL +data.poster_path, data.release_date, data.title, data.vote_average)
                    dataMapping.add(dataMap)
                }
                views!!.onSetMovieAdapter(dataMapping)
                Log.d("onDataTopRating", "onResponse: ${response.body()?.results}")
            }

            override fun onFailure(call: Call<TopRatedResponse>, t: Throwable) {
                Log.d("onDataTopRating", "onFailure: $t")
            }

        })
    }

    fun getMoreTopRating(){
        starttoprate += 1
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<TopRatedResponse>? = apiservice?.getTopRated(ApiClient.API_KEY, starttoprate)
        getData?.enqueue(object : Callback<TopRatedResponse> {
            override fun onResponse(
                call: Call<TopRatedResponse>,
                response: Response<TopRatedResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    var dataMap = MovieMappingModel(data.genre_ids, data.id, data.original_title, ApiClient.BASE_IMG_URL +data.poster_path, data.release_date, data.title, data.vote_average)
                    dataMapping.add(dataMap)
                }
                views!!.onSetMoreMovieAdapter(dataMapping)
                Log.d("onDataTopRating", "onResponse: Page $starttoprate | ${response.body()?.results}")
            }

            override fun onFailure(call: Call<TopRatedResponse>, t: Throwable) {
                Log.d("onDataTopRating", "onFailure: $t")
            }

        })
    }

    fun getOnPlaying(){
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<NowPlayingResponse>? = apiservice?.getNowPlaying(ApiClient.API_KEY, 1)
        getData?.enqueue(object : Callback<NowPlayingResponse> {
            override fun onResponse(
                call: Call<NowPlayingResponse>,
                response: Response<NowPlayingResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    var dataMap = MovieMappingModel(data.genre_ids, data.id, data.original_title, ApiClient.BASE_IMG_URL +data.poster_path, data.release_date, data.title, data.vote_average)
                    dataMapping.add(dataMap)
                }
                views!!.onSetMovieAdapter(dataMapping)
                Log.d("onDataNowPlaying", "onResponse: ${response.body()?.results}")
            }

            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                Log.d("onDataNowPlaying", "onFailure: $t")
            }

        })
    }

    fun getMoreOnPlaying(){
        startnowplaying += 1
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<NowPlayingResponse>? = apiservice?.getNowPlaying(ApiClient.API_KEY, startnowplaying)
        getData?.enqueue(object : Callback<NowPlayingResponse> {
            override fun onResponse(
                call: Call<NowPlayingResponse>,
                response: Response<NowPlayingResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    var dataMap = MovieMappingModel(data.genre_ids, data.id, data.original_title, ApiClient.BASE_IMG_URL +data.poster_path, data.release_date, data.title, data.vote_average)
                    dataMapping.add(dataMap)
                }
                views!!.onSetMoreMovieAdapter(dataMapping)
                Log.d("onDataNowPlaying", "onResponse: Page $startnowplaying | ${response.body()?.results}")
            }

            override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                Log.d("onDataNowPlaying", "onFailure: $t")
            }

        })
    }

    fun getUpComing(){
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<UpComingResponse>? = apiservice?.getUpComing(ApiClient.API_KEY, 1)
        getData?.enqueue(object : Callback<UpComingResponse> {
            override fun onResponse(
                call: Call<UpComingResponse>,
                response: Response<UpComingResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    var dataMap = MovieMappingModel(data.genre_ids, data.id, data.original_title, ApiClient.BASE_IMG_URL +data.poster_path, data.release_date, data.title, data.vote_average)
                    dataMapping.add(dataMap)
                }
                views!!.onSetMovieAdapter(dataMapping)
                Log.d("onDataUpComming", "onResponse: ${response.body()?.results}")
            }

            override fun onFailure(call: Call<UpComingResponse>, t: Throwable) {
                Log.d("onDataUpComming", "onFailure: $t")
            }

        })
    }

    fun getMoreUpComming(){
        startupcomming += 1
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<UpComingResponse>? = apiservice?.getUpComing(ApiClient.API_KEY, startupcomming)
        getData?.enqueue(object : Callback<UpComingResponse> {
            override fun onResponse(
                call: Call<UpComingResponse>,
                response: Response<UpComingResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    var dataMap = MovieMappingModel(data.genre_ids, data.id, data.original_title, ApiClient.BASE_IMG_URL +data.poster_path, data.release_date, data.title, data.vote_average)
                    dataMapping.add(dataMap)
                }
                views!!.onSetMoreMovieAdapter(dataMapping)
                Log.d("onDataUpComming", "onResponse: Page $startupcomming | ${response.body()?.results}")
            }

            override fun onFailure(call: Call<UpComingResponse>, t: Throwable) {
                Log.d("onDataUpComming", "onFailure: $t")
            }

        })
    }

    override fun onAttach(view: MoreMovieView) {
        views = view
    }

    override fun onDeAttach() {
        views = null
    }
}