package com.example.elemestest.data.remote.respons

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopRatedResponse (
            @field:SerializedName("page")
            var page: Int?= null,
            @field:SerializedName("results")
            var results: MutableList<ResultTopRated>?=null,
            @field:SerializedName("total_pages")
            var total_pages: Int?= null,
            @field:SerializedName("total_results")
            var total_results: Int?= null
        ):Parcelable

@Parcelize
data class ResultTopRated(
    @field:SerializedName("adult")
    var adult: Boolean ?= null,
    @field:SerializedName("backdrop_path")
    var backdrop_path: String ?= null,
    @field:SerializedName("genre_ids")
    var genre_ids : List<Int> ?= null,
    @field:SerializedName("id")
    var id: Int?= null,
    @field:SerializedName("original_language")
    var original_language: String?= null,
    @field:SerializedName("original_title")
    var original_title: String?= null,
    @field:SerializedName("overview")
    var overview: String?= null,
    @field:SerializedName("popularity")
    var popularity: Double?= null,
    @field:SerializedName("poster_path")
    var poster_path: String?= null,
    @field:SerializedName("release_date")
    var release_date: String?= null,
    @field:SerializedName("title")
    var title: String?= null,
    @field:SerializedName("video")
    var video: Boolean?= null,
    @field:SerializedName("vote_average")
    var vote_average: Double?= null,
    @field:SerializedName("vote_count")
    var vote_count: Int?= null
): Parcelable