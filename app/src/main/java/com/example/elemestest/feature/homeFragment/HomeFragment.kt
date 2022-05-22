package com.example.elemestest.feature.homeFragment

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
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
import com.example.elemestest.databinding.FragmentHomeBinding
import com.example.elemestest.feature.moreMovie.MoreMovieActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), HomeFragmentView {
    val db by lazy { WatchListDB(activity as Activity) }

    lateinit var binding: FragmentHomeBinding
    lateinit var presenter : HomeFragmentPresenter
    var adapterMovie : MovieAdapter ?= null
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var loading: ProgressDialog ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HomeFragmentPresenter(this)
        loading = addLoading(activity as Activity, "Tunggu sebentar...")
        getData()
        binding.btnNowPlaying.setOnClickListener {
            var intent = Intent(activity, MoreMovieActivity::class.java)
            intent.putExtra("status", "now_playing")
            startActivity(intent)
        }
        binding.btnPopularPerson.setOnClickListener {
            var intent = Intent(activity, MoreMovieActivity::class.java)
            intent.putExtra("status", "popular_person")
            startActivity(intent)
        }
        binding.btnTopRated.setOnClickListener {
            var intent = Intent(activity, MoreMovieActivity::class.java)
            intent.putExtra("status", "top_rated")
            startActivity(intent)
        }
        binding.btnUpComming.setOnClickListener {
            var intent = Intent(activity, MoreMovieActivity::class.java)
            intent.putExtra("status", "up_comming")
            startActivity(intent)
        }
        binding.btnSearch.setOnClickListener {
            loading?.show()
            presenter.doSearchMovie(binding.edSearch.text.toString())
        }
    }

    fun getData(){
        loading?.show()
        presenter.getGenre()
        presenter.getPopularMoview()
    }

    override fun onSetMovieAdapter(list: MutableList<MovieMappingModel>) {
        adapterMovie = MovieAdapter(activity as Activity, list, false, object : MovieAdapter.MovieListener{
            override fun onMovieClicked(dataMovie: MovieMappingModel) {
                Toast.makeText(context, "${dataMovie.title} telah ditambahkan ke watchlist", Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.IO).launch {
                    val watchlist = db.watchListDao().getWatchlist()
                    Log.d("onDataWatchlist", "onMovieClicked: $watchlist")
                }
            }
        })
        binding.recMovie.apply {
            layoutManager = GridLayoutManager(activity as Activity, 2)
            adapter = adapterMovie
            addOnScrollListener(object : PaginationScrollListener(layoutManager as GridLayoutManager){
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    isLoading = true
                    binding.loadingPd.visibility = View.VISIBLE
                    presenter.getMoreMovie()
                }

            })
        }
        loading?.dismiss()
    }

    override fun onSetMoreMovieAdapter(list: MutableList<MovieMappingModel>) {
        isLoading = false
        binding.loadingPd.visibility = View.GONE
        adapterMovie?.addData(list)
    }

    override fun onSetSearchMovie(list: MutableList<MovieMappingModel>) {
        adapterMovie = MovieAdapter(activity as Activity, list, false, object : MovieAdapter.MovieListener{
            override fun onMovieClicked(dataMovie: MovieMappingModel) {

            }
        })
        binding.recMovie.apply {
            layoutManager = GridLayoutManager(activity as Activity, 2)
            adapter = adapterMovie
            addOnScrollListener(object : PaginationScrollListener(layoutManager as GridLayoutManager){
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    isLoading = true
                    binding.loadingPd.visibility = View.VISIBLE
                    presenter.doMoreSearchMovie(binding.edSearch.text.toString())
                }

            })
        }
        loading?.dismiss()
    }

    override fun onsetMoreSearchMovie(list: MutableList<MovieMappingModel>) {
        isLoading = false
        binding.loadingPd.visibility = View.GONE
        adapterMovie?.addData(list)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDeAttachView() {
        presenter.onDeAttach()
    }
}