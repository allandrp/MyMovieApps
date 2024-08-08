package com.alland.mymovieapps.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alland.mymovieapps.core.domain.MovieUseCase
import com.alland.mymovieapps.core.domain.model.MovieDomainModel
import com.alland.mymovieapps.core.ui.model.MoviePresenterModel
import com.alland.mymovieapps.core.utils.Result
import com.alland.mymovieapps.core.utils.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val useCaseMovie: MovieUseCase) : ViewModel() {

    fun getNowShowingMovies(): LiveData<Result<List<MoviePresenterModel>>> {
       return useCaseMovie.getNowShowingMovies().map { result ->
            when (result) {
                is Result.Success -> {
                    Result.Success(result.data.map {
                        Utils.mapDomainModelToPresenterModel(it)
                    })
                }
                Result.Empty -> Result.Empty
                is Result.Error -> Result.Error(result.message)
                Result.Loading -> Result.Loading
            }
        }.asLiveData()
    }
}