package com.alland.mymovieapps.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alland.mymovieapps.MyApplication
import com.alland.mymovieapps.core.ui.adapter.MovieAdapter
import com.alland.mymovieapps.core.utils.Result
import com.alland.mymovieapps.databinding.ActivityMainBinding
import com.alland.mymovieapps.ui.detail.DetailActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter

    @Inject
    lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getNowShowingMovies().observe(this){ result ->
            when(result){
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter = MovieAdapter(result.data as ArrayList){ data->
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra("movie", data)
                        startActivity(intent)
                    }

                    val layoutManager = LinearLayoutManager(this)
                    layoutManager.orientation = LinearLayoutManager.VERTICAL
                    binding.rvMovieNowShowing.adapter = adapter
                    binding.rvMovieNowShowing.layoutManager = layoutManager
                }

                Result.Empty -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
//                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    Log.d("okHttp", "Error: ${result.message}")
                }
                Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
//                    Toast.makeText(requireContext(), "Loading !", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.favouriteButtonHome.setOnClickListener{
            val intent = Intent(this, Class.forName("com.alland.mymovieapps.favourite.ui.FavouriteActivity"))
            startActivity(intent)
        }

    }
}