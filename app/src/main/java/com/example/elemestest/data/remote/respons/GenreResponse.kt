package com.example.elemestest.data.remote.respons

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreResponse (
        @field:SerializedName("genres")
        var genres: MutableList<GenreResult>?= null
        ):Parcelable

@Parcelize
data class GenreResult(
    @field:SerializedName("id")
    var id: Int ?= null,
    @field:SerializedName("name")
    var name: String ?= null
):Parcelable