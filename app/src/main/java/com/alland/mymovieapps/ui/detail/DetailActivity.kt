package com.alland.mymovieapps.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alland.mymovieapps.BuildConfig
import com.alland.mymovieapps.MyApplication
import com.alland.mymovieapps.R
import com.alland.mymovieapps.core.ui.model.MoviePresenterModel
import com.alland.mymovieapps.core.utils.Result
import com.alland.mymovieapps.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    @Inject
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieReceived = intent.getParcelableExtra("movie") as MoviePresenterModel?

        toolbarSetup()

        if (movieReceived != null) {
//            Toast.makeText(this, movieReceived.title, Toast.LENGTH_SHORT).show()
            viewModel.getDetailMovie(movieReceived.id).observe(this) { result ->

                when (result) {
                    is Result.Success -> {
                        val data = result.data
                        binding.titleMovieDetail.text = data.title
                        binding.ratingBar.rating = data.rating / 2
                        binding.yearMovieDetail.text = data.releaseDate.substring(0, 4)
                        binding.sinopsisDescriptionDetail.text = data.synopsis
                        Glide.with(this).load(BuildConfig.MOVIE_POSTER_PATH + data.posterPath)
                            .placeholder(
                                com.alland.mymovieapps.core.R.drawable.poster_placeholder
                            ).into(binding.posterMovieDetail)

                        binding.toolbar2.title = data.title
                    }

                    is Result.Empty -> {
                        Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
                    }

                    is Result.Error -> {
                        Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    }

                    is Result.Loading -> {
//                        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            viewModel.isMovieFavourite(movieReceived.id).observe(this) { isFavourite ->
                if (!isFavourite) {
                    binding.favouriteButton.setImageResource(com.alland.mymovieapps.core.R.drawable.baseline_favorite_border_24)
                } else {
                    binding.favouriteButton.setImageResource(com.alland.mymovieapps.core.R.drawable.baseline_favorite_24)
                }
            }

            binding.favouriteButton.setOnClickListener {
                movieReceived.isFavourite = !movieReceived.isFavourite
                viewModel.updateMovie(movieReceived)
                Log.d("update_presenter", movieReceived.toString())
            }
        }
    }


    private fun toolbarSetup() {
        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}