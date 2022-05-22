package com.example.elemestest.feature.homeFragment

import android.util.Log
import com.example.elemestest.MVPBase.BasePresenter
import com.example.elemestest.data.ApiClient
import com.example.elemestest.data.ApiClient.Companion.BASE_IMG_URL
import com.example.elemestest.data.model.MovieMappingModel
import com.example.elemestest.data.remote.ApiResource
import com.example.elemestest.data.remote.respons.GenreResponse
import com.example.elemestest.data.remote.respons.PopularMovieResponse
import com.example.elemestest.data.remote.respons.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentPresenter : BasePresenter<HomeFragmentView> {
    var views: HomeFragmentView ?= null
    var apiservice : ApiResource?= null
    var start = 1
    var startSearching = 1

    constructor(views: HomeFragmentView?) {
        this.views = views
    }

    fun getGenre(){
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<GenreResponse>? = apiservice?.getGenre(ApiClient.API_KEY)
        getData?.enqueue(object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                Log.d("onGetGenre", "onResponse: ${response.body()?.genres}")
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                Log.d("onGetGenre", "onFailure: $t")
            }

        })
    }

    fun getPopularMoview(){
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<PopularMovieResponse>? = apiservice?.getPopularMovie(ApiClient.API_KEY, 1)
        getData?.enqueue(object : Callback<PopularMovieResponse>{
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    var dataMap = MovieMappingModel(data.genre_ids, data.id, data.original_title, BASE_IMG_URL+data.poster_path, data.release_date, data.title, data.vote_average)
                    dataMapping.add(dataMap)
                }
                views!!.onSetMovieAdapter(dataMapping)
                Log.d("onGetDataPopularMovie", "onResponse: ${response.body()?.results}")
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.d("onGetDataPopularMovie", "onFailure: $t")
            }
        })

    }

    fun getMoreMovie(){
        start += 1
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<PopularMovieResponse>? = apiservice?.getPopularMovie(ApiClient.API_KEY, start)
        getData?.enqueue(object : Callback<PopularMovieResponse>{
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    var dataMap = MovieMappingModel(data.genre_ids, data.id, data.original_title, BASE_IMG_URL+data.poster_path, data.release_date, data.title, data.vote_average)
                    dataMapping.add(dataMap)
                }
                views!!.onSetMoreMovieAdapter(dataMapping)
                Log.d("onGetDataPopularMovie", "onResponse: page $start | ${response.body()?.results}")
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.d("onGetDataPopularMovie", "onFailure: $t")
            }
        })
    }

    fun doSearchMovie(query: String){
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<SearchResponse>? = apiservice?.getSearchMovie(ApiClient.API_KEY, query,1)
        getData?.enqueue(object : Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    var dataMap = MovieMappingModel(data.genre_ids, data.id, data.original_title, BASE_IMG_URL+data.poster_path, data.release_date, data.title, data.vote_average)
                    dataMapping.add(dataMap)
                }
                views!!.onSetSearchMovie(dataMapping)
                Log.d("onGetDataSearchMovie", "onResponse: page $start | ${response.body()?.results}")
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("onGetDataSearchMovie", "onFailure: $t")
            }
        })
    }

    fun doMoreSearchMovie(query: String){
        startSearching += 1
        apiservice = ApiClient.ApiConnector.client?.create(ApiResource::class.java)
        val getData: retrofit2.Call<SearchResponse>? = apiservice?.getSearchMovie(ApiClient.API_KEY, query, startSearching)
        getData?.enqueue(object : Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                var dataMapping : MutableList<MovieMappingModel> = mutableListOf()
                response.body()?.results?.forEach { data ->
                    var dataMap = MovieMappingModel(data.genre_ids, data.id, data.original_title, BASE_IMG_URL+data.poster_path, data.release_date, data.title, data.vote_average)
                    dataMapping.add(dataMap)
                }
                views!!.onsetMoreSearchMovie(dataMapping)
                Log.d("onGetDataSearchMovie", "onResponse: page $start | ${response.body()?.results}")
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("onGetDataSearchMovie", "onFailure: $t")
            }
        })
    }

    override fun onAttach(view: HomeFragmentView) {
        views = view
    }

    override fun onDeAttach() {
        views = null
    }

}