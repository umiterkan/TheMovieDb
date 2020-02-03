package com.erkan.themoviedb.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erkan.themoviedb.BR
import com.erkan.themoviedb.databinding.ItemMovieBinding
import com.erkan.themoviedb.service.model.Movie

class MovieListAdapter(val listener:MovieItemListenerInterface) :
    RecyclerView.Adapter<MovieListAdapter.MovieItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    var list: List<Movie>?=null

    override fun getItemCount(): Int= if (list!=null) list!!.size else 0

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {

        holder.bind(list?.get(position))
    }

    inner class MovieItemViewHolder(val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {
        fun bind(movie: Movie?) {
            movie?.let {
                itemMovieBinding.listener=listener
                itemMovieBinding.setVariable(BR.data,it)
               // itemMovieBinding.data = it
                itemMovieBinding.executePendingBindings()
            }
        }
    }


}
