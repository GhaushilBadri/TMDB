package com.example.elemestest.data.remote

import com.example.elemestest.data.remote.respons.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiResource {

    @GET("genre/movie/list")
    fun getGenre(
        @Query("api_key") api_key: String
    ):Call<GenreResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ):Call<TopRatedResponse>

    @GET("movie/upcoming")
    fun getUpComing(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ):Call<UpComingResponse>

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ):Call<NowPlayingResponse>

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<PopularMovieResponse>

    @GET("person/popular")
    fun getPopularPerson(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ):Call<PersonPopularResponse>

    @GET("search/movie")
    fun getSearchMovie(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page : Int
    ):Call<SearchResponse>
}