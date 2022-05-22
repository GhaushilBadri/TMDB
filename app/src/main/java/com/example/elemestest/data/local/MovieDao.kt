package com.example.elemestest.data.local

import androidx.room.*
import com.example.elemestest.data.model.MovieMappingModel
import com.example.elemestest.data.model.WatchList

@Dao
interface MovieDao {
    @Insert
    suspend fun addWatchList(movie: WatchList)

    @Delete
    suspend fun deleteWatchList(movie: WatchList)

    @Query("SELECT * FROM watchlist")
    suspend fun getWatchlist(): MutableList<WatchList>
}