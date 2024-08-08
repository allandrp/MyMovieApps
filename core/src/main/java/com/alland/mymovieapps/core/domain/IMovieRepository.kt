package com.alland.mymovieapps.core.domain

import com.alland.mymovieapps.core.domain.model.MovieDomainModel
import com.alland.mymovieapps.core.utils.Result
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getNowPlayingMovies(): Flow<Result<List<MovieDomainModel>>>
    fun getUpcomingMovies(): Flow<Result<List<MovieDomainModel>>>
//    fun setFavouriteMovie(movie: MovieDomainModel, state: Boolean)
}