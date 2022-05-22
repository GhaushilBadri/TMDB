package com.example.elemestest.feature.myWatchList

import com.example.elemestest.MVPBase.BaseView
import com.example.elemestest.data.model.MovieMappingModel

interface WatchListView: BaseView {
    fun onSetWatchlist(list: MutableList<MovieMappingModel>)
}