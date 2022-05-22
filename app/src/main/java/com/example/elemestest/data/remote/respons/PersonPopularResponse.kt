package com.example.elemestest.data.remote.respons

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PersonPopularResponse (
        @field:SerializedName("page")
        var page: Int?= null,
        @field:SerializedName("results")
        var results: MutableList<ResultPopularPerson>?=null,
        @field:SerializedName("total_pages")
        var total_pages: Int?= null,
        @field:SerializedName("total_results")
        var total_results: Int?= null
        )

@Parcelize
data class ResultPopularPerson(
    @field:SerializedName("adult")
    val adult: Boolean,
    @field:SerializedName("gender")
    val gender: Int,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("known_for")
    val known_for: MutableList<ResultKnownFor>
):Parcelable


@Parcelize
data class ResultKnownFor(
    @field:SerializedName("adult")
    val adult: Boolean,
    @field:SerializedName("backdrop_path")
    val backdrop_path: String,
    @field:SerializedName("first_air_date")
    val first_air_date: String,
    @field:SerializedName("genre_ids")
    val genre_ids : List<Int>,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("media_type")
    val media_type: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("origin_country")
    val origin_country: List<String>,
    @field:SerializedName("original_language")
    val original_language: String,
    @field:SerializedName("original_name")
    val original_name: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("poster_path")
    val poster_path: String,
    @field:SerializedName("vote_average")
    val vote_average: Double,
    @field:SerializedName("vote_count")
    val vote_count: Int
): Parcelable