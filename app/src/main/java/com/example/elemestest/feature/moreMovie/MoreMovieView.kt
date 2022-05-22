package com.example.elemestest.feature.moreMovie

import com.example.elemestest.MVPBase.BaseView
import com.example.elemestest.data.model.MovieMappingModel

interface MoreMovieView: BaseView {
    fun onSetMovieAdapter(list: MutableList<MovieMappingModel>)
    fun onSetMoreMovieAdapter(list: MutableList<MovieMappingModel>)
}