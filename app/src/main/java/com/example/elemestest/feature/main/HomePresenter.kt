package com.example.elemestest.feature.main

import android.util.Log
import com.example.elemestest.MVPBase.BasePresenter
import com.example.elemestest.data.ApiClient.ApiConnector
import com.example.elemestest.data.ApiClient.Companion.API_KEY
import com.example.elemestest.data.remote.ApiResource
import com.example.elemestest.data.remote.respons.PopularMovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter : BasePresenter<HomeView>{
    var views: HomeView?= null
    var apiservice : ApiResource ?= null

    constructor(view: HomeView?){
        this.views = view
    }

    override fun onAttach(view: HomeView) {
        views = view
    }

    override fun onDeAttach() {
        views = null
    }
}