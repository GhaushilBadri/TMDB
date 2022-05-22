package com.example.elemestest.feature.homeFragment

import com.example.elemestest.MVPBase.BaseView
import com.example.elemestest.data.model.MovieMappingModel

interface HomeFragmentView: BaseView {
    fun onSetMovieAdapter(list: MutableList<MovieMappingModel>)
    fun onSetMoreMovieAdapter(list: MutableList<MovieMappingModel>)
    fun onSetSearchMovie(list: MutableList<MovieMappingModel>)
    fun onsetMoreSearchMovie(list: MutableList<MovieMappingModel>)
}