package com.example.elemestest.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.elemestest.R
import com.example.elemestest.data.local.WatchListDB
import com.example.elemestest.data.model.MovieMappingModel
import com.example.elemestest.data.model.WatchList
import com.example.elemestest.databinding.ItemMovieBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieAdapter(val context: Activity, var list: MutableList<MovieMappingModel>, var isWatchlist: Boolean, var listener: MovieListener): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    val db by lazy { WatchListDB(context) }

    inner class MovieViewHolder(val binding : ItemMovieBinding): RecyclerView.ViewHolder(binding.root)
    interface MovieListener{
        fun onMovieClicked(dataMovie: MovieMappingModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(context.layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var data = list[position]
        if (isWatchlist){
            holder.binding.btnAddWatchlist.visibility = View.GONE
        }else{
            holder.binding.btnAddWatchlist.visibility = View.VISIBLE
        }
        holder.binding.apply {
            val requestOption = RequestOptions().error(R.drawable.ic_action_movie)
            Glide.with(context)
                .load(data.poster_path)
                .apply(requestOption)
                .into(imMovieItem)
            tvTitleMovie.setText(data.title)
            tvReleaseDate.setText(data.release_date)
            tvRate.setText(data.vote_average.toString())
            btnAddWatchlist.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    db.watchListDao().addWatchList(
                        WatchList("", data.id!!, data.original_title.toString(), data.poster_path.toString(), data.release_date.toString(), data.title.toString(), data.vote_average!!)
                    )
                }
                listener.onMovieClicked(data)
            }
        }
    }

    fun addData(newList: MutableList<MovieMappingModel>){
        var size = this.list.size
        this.list.addAll(newList)
        var sizeNew = this.list.size
        notifyItemRangeChanged(size, sizeNew)
    }

    override fun getItemCount() = list.size

}