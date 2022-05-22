package com.example.elemestest.feature.myWatchList

import android.app.Activity
import android.util.Log
import com.example.elemestest.MVPBase.BasePresenter
import com.example.elemestest.data.local.WatchListDB
import com.example.elemestest.data.model.MovieMappingModel
import com.example.elemestest.data.model.WatchList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WatchListPresenter : BasePresenter< WatchListView > {
    var views: WatchListView ?= null

    constructor(views: WatchListView?) {
        this.views = views
    }

    fun getDataWatchList(data: MutableList<WatchList>){
        var listMovie : MutableList<MovieMappingModel> = mutableListOf()
        var genre: List<Int> = listOf(0)
        data.forEach {
            listMovie.add(MovieMappingModel(null, it.id, it.original_title, it.poster_path, it.release_date, it.title, it.vote_average))
        }
        views?.onSetWatchlist(listMovie)
    }

    override fun onAttach(view: WatchListView) {
        views = view
    }

    override fun onDeAttach() {
        views = null
    }

}