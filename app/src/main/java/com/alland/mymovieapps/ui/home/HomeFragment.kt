package com.alland.mymovieapps.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alland.mymovieapps.MyApplication
import com.alland.mymovieapps.R
import com.alland.mymovieapps.core.ui.adapter.MovieAdapter
import com.alland.mymovieapps.core.utils.Result
import com.alland.mymovieapps.databinding.FragmentHomeBinding
import com.alland.mymovieapps.detail.DetailActivity
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MovieAdapter

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getNowShowingMovies().observe(viewLifecycleOwner){ result ->
            when(result){
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter = MovieAdapter(result.data as ArrayList){ data->
                        val intent = Intent(requireContext(), DetailActivity::class.java)
                        intent.putExtra("movie", data)
                        startActivity(intent)
                    }

                    val layoutManager = LinearLayoutManager(requireContext())
                    layoutManager.orientation = LinearLayoutManager.VERTICAL
                    binding.rvMovieNowShowing.adapter = adapter
                    binding.rvMovieNowShowing.layoutManager = layoutManager
                }

                Result.Empty -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
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
    }
}