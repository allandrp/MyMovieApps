package com.alland.mymovieapps.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alland.mymovieapps.core.BuildConfig
import com.alland.mymovieapps.core.R
import com.alland.mymovieapps.core.databinding.MovieItemBinding
import com.alland.mymovieapps.core.ui.model.MoviePresenterModel
import com.alland.mymovieapps.core.utils.Utils
import com.bumptech.glide.Glide
import java.util.Locale

class MovieAdapter(
    private val listMovie: ArrayList<MoviePresenterModel>,
    private var onItemClick: ((MoviePresenterModel) -> Unit)) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(val binding: MovieItemBinding) : ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val binding = holder.binding
        val movieItem = listMovie[position]

        binding.movieTitleItem.text = movieItem.title
        Glide.with(holder.itemView.context).load(BuildConfig.MOVIE_POSTER_PATH + movieItem.imagePath)
            .placeholder(
                R.drawable.poster_placeholder
            )
            .into(binding.moviePosterItem)
        binding.movieReleaseItem.text = Utils.formatDate(movieItem.releaseDate)
        binding.movieRatingItem.text = String.format(Locale.getDefault(), "%.1f", movieItem.rating)
        binding.movieRatingBarItem.rating = movieItem.rating/2

        binding.root.setOnClickListener {
            onItemClick(movieItem)
        }
    }
}