package com.example.elemestest.data.model

class MovieMappingModel {
    var genre_ids : List<Int> ? = null
    var id: Int ?= null
    var original_title: String ?= null
    var poster_path: String ?= null
    var release_date: String ?= null
    var title: String ?= null
    var vote_average : Double ?= null

    constructor(
        genre_ids: List<Int>?,
        id: Int?,
        original_title: String?,
        poster_path: String?,
        release_date: String?,
        title: String?,
        vote_average: Double?
    ) {
        this.genre_ids = genre_ids
        this.id = id
        this.original_title = original_title
        this.poster_path = poster_path
        this.release_date = release_date
        this.title = title
        this.vote_average = vote_average
    }
}
