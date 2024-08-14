package com.alland.mymovieapps.favourite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alland.mymovieapps.core.domain.MovieUseCase
import com.alland.mymovieapps.core.utils.Utils
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    fun getFavouriteMovies() = movieUseCase.getFavouriteMovies().map { listFavouriteMovies ->
        listFavouriteMovies.map {
            Utils.mapDomainModelToPresenterModel(it)
        }
    }.asLiveData()
}