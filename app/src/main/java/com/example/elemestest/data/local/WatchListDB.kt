package com.example.elemestest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elemestest.data.model.WatchList

@Database(
    entities = [WatchList::class],
    version = 1
)

abstract class WatchListDB : RoomDatabase() {
    abstract fun watchListDao() : MovieDao
    companion object{
        @Volatile private  var instance: WatchListDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            WatchListDB::class.java,
            "watchlist.db"
        ).build()
    }
}