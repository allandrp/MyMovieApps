package com.alland.mymovieapps.core.domain

import android.util.Log
import com.alland.mymovieapps.core.domain.model.MovieDetailDomainModel
import com.alland.mymovieapps.core.domain.model.MovieDomainModel
import com.alland.mymovieapps.core.utils.Result
import com.alland.mymovieapps.core.utils.Utils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getNowShowingMovies(): Flow<Result<List<MovieDomainModel>>> {
        return movieRepository.getNowPlayingMovies()
    }

    override fun getDetailMovie(id: Int): Flow<Result<MovieDetailDomainModel>> {
        return movieRepository.getDetailMovie(id)
    }

    override fun isMovieFavourite(id: Int): Flow<Boolean> {
        return movieRepository.isMovieFavourite(id)
    }

    override suspend fun setFavouriteMovie(movie: MovieDomainModel) {
        val movieEntity = Utils.mapMovieDomainToEntity(movie)
        Log.d("update_interactor", movieEntity.toString())
        return movieRepository.updateMovieData(movieEntity)
    }

    override fun getFavouriteMovies(): Flow<List<MovieDomainModel>> {
        return movieRepository.getFavouriteMovies()
    }
}