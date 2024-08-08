package com.alland.mymovieapps.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alland.mymovieapps.BuildConfig
import com.alland.mymovieapps.R
import com.alland.mymovieapps.core.ui.model.MoviePresenterModel
import com.alland.mymovieapps.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import java.time.LocalDate

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieReceived = intent.getParcelableExtra("movie") as MoviePresenterModel?

        if (movieReceived != null) {
            binding.titleMovieDetail.text = movieReceived.title
            binding.ratingBar.rating = movieReceived.rating / 2
            binding.yearMovieDetail.text = movieReceived.releaseDate.substring(0, 4)
            binding.sinopsisDescriptionDetail.text = movieReceived.synopsis
            Glide.with(this).load(BuildConfig.MOVIE_POSTER_PATH + movieReceived.backdropPath)
                .placeholder(
                    com.alland.mymovieapps.core.R.drawable.poster_placeholder
                ).into(binding.posterMovieDetail)
        }
    }
}