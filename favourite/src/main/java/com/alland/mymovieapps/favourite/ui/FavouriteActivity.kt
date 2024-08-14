package com.alland.mymovieapps.favourite.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alland.mymovieapps.MyApplication
import com.alland.mymovieapps.core.ui.adapter.MovieAdapter
import com.alland.mymovieapps.favourite.databinding.ActivityFavouriteBinding
import com.alland.mymovieapps.favourite.di.DaggerFavouriteComponent
import com.alland.mymovieapps.ui.detail.DetailActivity
import javax.inject.Inject

class FavouriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var adapter: MovieAdapter

    @Inject
    lateinit var viewModel: FavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent = (application as MyApplication).appComponent
        DaggerFavouriteComponent.factory().create(appComponent).inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getFavouriteMovies().observe(this) {
            adapter = MovieAdapter(it as ArrayList) { data ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("movie", data)
                startActivity(intent)
            }
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.rvFavourite.layoutManager = layoutManager
            binding.rvFavourite.adapter = adapter

        }
    }
}