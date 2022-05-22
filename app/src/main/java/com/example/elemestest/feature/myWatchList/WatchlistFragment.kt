package com.example.elemestest.feature.myWatchList

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.elemestest.ExternalMethod.Companion.addLoading
import com.example.elemestest.R
import com.example.elemestest.adapter.MovieAdapter
import com.example.elemestest.adapter.PaginationScrollListener
import com.example.elemestest.data.local.WatchListDB
import com.example.elemestest.data.model.MovieMappingModel
import com.example.elemestest.data.model.WatchList
import com.example.elemestest.databinding.FragmentWatchlistBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WatchlistFragment : Fragment(), WatchListView {
    lateinit var binding: FragmentWatchlistBinding
    lateinit var presenter: WatchListPresenter
    var adapterMovie : MovieAdapter ?= null
    val db by lazy { WatchListDB(activity as Activity) }
    var loading : ProgressDialog ?= null
    var watchlist: MutableList<WatchList> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = WatchListPresenter(this)
        loading = addLoading(activity as Activity, "Tunggu sebentar...")
        CoroutineScope(Dispatchers.IO).launch {
            watchlist = db.watchListDao().getWatchlist()
            Log.d("onDataWatchlist", "MyWatchList: $watchlist")
        }
        getData()
    }

    fun getData(){
        loading?.show()
        presenter.getDataWatchList(watchlist)
    }

    override fun onSetWatchlist(list: MutableList<MovieMappingModel>) {
        adapterMovie = MovieAdapter(activity as Activity, list, true, object : MovieAdapter.MovieListener{
            override fun onMovieClicked(dataMovie: MovieMappingModel) {

            }
        })
        binding.recMovieWatchlist.apply {
            layoutManager = GridLayoutManager(activity as Activity, 2)
            adapter = adapterMovie
        }
        loading?.dismiss()
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDeAttachView() {
        presenter.onDeAttach()
    }
}