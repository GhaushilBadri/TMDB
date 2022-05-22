package com.example.elemestest.data.remote.respons

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NowPlayingResponse (
            @field:SerializedName("dates")
            var dates: DatesResult?= null,
            @field:SerializedName("page")
            val page: Int,
            @field:SerializedName("results")
            val results: MutableList<ResultNowPlaying>,
            @field:SerializedName("total_pages")
            val total_pages: Int,
            @field:SerializedName("total_results")
            val total_results: Int
        ):Parcelable

@Parcelize
data class ResultNowPlaying(
    @field:SerializedName("adult")
    val adult: Boolean,
    @field:SerializedName("backdrop_path")
    val backdrop_path: String,
    @field:SerializedName("genre_ids")
    val genre_ids : List<Int>,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("original_language")
    val original_language: String,
    @field:SerializedName("original_title")
    val original_title: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("popularity")
    val popularity: Double,
    @field:SerializedName("poster_path")
    val poster_path: String,
    @field:SerializedName("release_date")
    val release_date: String,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("video")
    val video: Boolean,
    @field:SerializedName("vote_average")
    val vote_average: Double,
    @field:SerializedName("vote_count")
    val vote_count: Int
): Parcelable