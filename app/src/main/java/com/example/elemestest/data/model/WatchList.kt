package com.example.elemestest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WatchList (
            var genre_ids : String,
            @PrimaryKey(autoGenerate = false)
            var id: Int,
            var original_title: String,
            var poster_path: String,
            var release_date: String,
            var title: String,
            var vote_average : Double
        )