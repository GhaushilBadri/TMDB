package com.example.elemestest.feature.moreMovie

import android.app.Activity
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.elemestest.ExternalMethod
import com.example.elemestest.R
import com.example.elemestest.adapter.MovieAdapter
import com.example.elemestest.adapter.PaginationScrollListener
import com.example.elemestest.data.model.MovieMappingModel
import com.example.elemestest.databinding.ActivityMoreMovieBinding

class MoreMovieActivity : AppCompatActivity(), MoreMovieView {
    lateinit var binding: ActivityMoreMovieBinding
    lateinit var presenter: MoreMoviePresenter
    var adapterMovie : MovieAdapter?= null
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var status = ""
    var loading: ProgressDialog?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MoreMoviePresenter(this)
        status = intent.getStringExtra("status").toString()
        loading = ExternalMethod.addLoading(this, "Tunggu sebentar...")
        getData()
    }

    fun getData(){
        when(status){
            "now_playing"->{
                binding.tvTitle.setText("Sedang Diputar")
                loading?.show()
                presenter.getOnPlaying()
            }
            "popular_person"->{
                binding.tvTitle.setText("Paling Banyak Dicari")
                loading?.show()
                presenter.getPersonPopular()
            }
            "top_rated"->{
                binding.tvTitle.setText("Rating Terbaik")
                loading?.show()
                presenter.getTopRating()
            }
            "up_comming"->{
                binding.tvTitle.setText("Segera Tayang")
                loading?.show()
                presenter.getUpComing()
            }
        }
    }

    override fun onSetMovieAdapter(list: MutableList<MovieMappingModel>) {
        adapterMovie = MovieAdapter(this, list, false ,object : MovieAdapter.MovieListener{
            override fun onMovieClicked(dataMovie: MovieMappingModel) {

            }
        })
        binding.recMovie.apply {
            layoutManager = GridLayoutManager(this@MoreMovieActivity, 2)
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
                    binding.loadingPdMore.visibility = View.VISIBLE
                    when(status){
                        "now_playing"->{
                            presenter.getMoreOnPlaying()
                        }
                        "popular_person"->{
                            presenter.getMorePersonPopular()
                        }
                        "top_rated"->{
                            presenter.getMoreTopRating()
                        }
                        "up_comming"->{
                            presenter.getMoreUpComming()
                        }
                    }
                }
            })
            loading?.dismiss()
        }
    }

    override fun onSetMoreMovieAdapter(list: MutableList<MovieMappingModel>) {
        isLoading = false
        binding.loadingPdMore.visibility = View.GONE
        adapterMovie?.addData(list)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDeAttachView() {
        presenter.onDeAttach()
    }
}