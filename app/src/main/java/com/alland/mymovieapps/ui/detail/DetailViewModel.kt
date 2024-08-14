package com.alland.mymovieapps.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.alland.mymovieapps.core.domain.MovieUseCase
import com.alland.mymovieapps.core.ui.model.MoviePresenterModel
import com.alland.mymovieapps.core.utils.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getDetailMovie(id: Int) = movieUseCase.getDetailMovie(id).asLiveData()
    fun isMovieFavourite(id: Int) = movieUseCase.isMovieFavourite(id).asLiveData()
    fun updateMovie(movie: MoviePresenterModel) {
        viewModelScope.launch {
            val movieDomainModel = Utils.mapPresenterModelToDomainModel(movie)
            movieUseCase.setFavouriteMovie(movieDomainModel)
            Log.d("update_domain", movieDomainModel.toString())
        }
    }
}
